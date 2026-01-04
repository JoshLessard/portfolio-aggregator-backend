package dev.joshlessard.portfolio.domain.questrade;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.generic.oauth.OAuthAccessTokenService;
import dev.joshlessard.generic.oauth.StubOAuthAccessTokenRepository;
import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.PortfolioType;
import dev.joshlessard.portfolio.domain.Position;

public class QuestradePortfolioServiceTest {

    private final StubOAuthAccessTokenRepository accessTokenRepository = new StubOAuthAccessTokenRepository();
    private final OAuthAccessTokenService accessTokenService = new OAuthAccessTokenService( accessTokenRepository );
    private final StubQuestradePortfolioRetriever portfolioRetriever = new StubQuestradePortfolioRetriever();
    private final QuestradePortfolioService portfolioService = new QuestradePortfolioService( accessTokenService, portfolioRetriever );

    @Test
    public void accessTokenIsRetrievedFromOAuthServiceAndUsedToGetPortfoliosFromRetriever() {
        OAuthAccessToken accessToken = new OAuthAccessToken( "some_access_token", "some_token_type", 300, "some_refresh_token", emptyMap() );
        accessTokenRepository.setToken( accessToken );
        List<Portfolio> expectedPortfolios = List.of(
            new Portfolio( PortfolioType.TFSA, List.of( new Position( "TSLA", 14.396 ) ) )
        );
        portfolioRetriever.setPortfolios( accessToken, expectedPortfolios );

        List<Portfolio> actualPortfolios = portfolioService.getPortfolios();

        assertThat( actualPortfolios )
            .containsExactlyInAnyOrderElementsOf( expectedPortfolios );
    }
}
