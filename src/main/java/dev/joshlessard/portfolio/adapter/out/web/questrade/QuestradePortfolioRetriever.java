package dev.joshlessard.portfolio.adapter.out.web.questrade;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.PortfolioType;
import dev.joshlessard.portfolio.domain.Position;
import dev.joshlessard.portfolio.domain.PortfolioRetriever;

// TODO Handle invalid token (e.g., if it is rejected by any APIs)
@Component
public class QuestradePortfolioRetriever implements PortfolioRetriever {

    private final RestClient restClient;

    @Autowired
    public QuestradePortfolioRetriever( RestClient restClient ) {
        this.restClient = restClient;
    }

    @Override
    public List<Portfolio> retrievePortfolios( OAuthAccessToken accessToken ) {
        List<AccountDto> accounts = retrieveAccounts( accessToken );
        Map<AccountDto, List<PositionDto>> positionsByAccount = accounts
            .stream()
            .collect( toMap( identity(), a -> retrievePositions( accessToken, a.number() ) ) );
        return toDomainObjects( positionsByAccount );
    }

    private List<AccountDto> retrieveAccounts( OAuthAccessToken accessToken ) {
        GetAccountsResponse response = get( accessToken, "v1/accounts", GetAccountsResponse.class );
        return response.accounts();
    }

    private List<PositionDto> retrievePositions( OAuthAccessToken accessToken, int accountId ) {
        GetPositionsResponse response = get( accessToken, String.format( "v1/accounts/%d/positions", accountId ), GetPositionsResponse.class );
        return response.positions();
    }

    private List<Portfolio> toDomainObjects( Map<AccountDto, List<PositionDto>> positionsByAccount ) {
        List<Portfolio> portfolios = new ArrayList<>();
        positionsByAccount.forEach( (accountDto, positionDtos) -> {
            PortfolioType type = toPortfolioType( accountDto.type() );
            List<Position> positions = positionDtos
                .stream()
                .map( p -> new Position( p.symbol(), p.openQuantity() ) )
                .toList();
            portfolios.add( new Portfolio( type, positions ) );
        } );
        return portfolios;
    }

    private PortfolioType toPortfolioType( String type ) {
        return switch( type.toLowerCase() ) {
            case "rrsp" -> PortfolioType.RRSP;
            case "tfsa" -> PortfolioType.TFSA;
            default -> PortfolioType.UNKNOWN;
        };
    }

    private <T> T get( OAuthAccessToken accessToken, String uri, Class<T> responseType ) {
        String apiServer = accessToken.additionalParameters().get( "api_server" );
        String authorization = accessToken.tokenType() + " " + accessToken.accessToken();
        return restClient
            .get()
            .uri( apiServer + uri )
            .header( "Authorization", authorization )
            .retrieve()
            .body( responseType );
    }
}
