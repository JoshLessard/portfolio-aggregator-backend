package dev.joshlessard.portfolio.domain.questrade;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.generic.oauth.StubOAuthAccessTokenRepository;
import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.PortfolioType;
import dev.joshlessard.portfolio.domain.Position;

public class QuestradePortfolioServiceTest {

    @Test
    public void accessTokenIsRetrievedFromRepositoryAndUsedToGetPortfoliosFromRetriever() {
        OAuthAccessToken accessToken = new OAuthAccessToken( "some_access_token", "some_token_type", 300, "some_refresh_token", emptyMap() );
        StubOAuthAccessTokenRepository accessTokenRepository = new StubOAuthAccessTokenRepository();
        accessTokenRepository.setToken( accessToken );
        List<Portfolio> expectedPortfolios = List.of(
            new Portfolio( PortfolioType.TFSA, List.of( new Position( "TSLA", 14.396 ) ) )
        );
        StubQuestradePortfolioRetriever portfolioRetriever = new StubQuestradePortfolioRetriever();
        portfolioRetriever.setPortfolios( accessToken, expectedPortfolios );
        QuestradePortfolioService service = new QuestradePortfolioService( accessTokenRepository, portfolioRetriever );

        List<Portfolio> actualPortfolios = service.getPortfolios();

        assertThat( actualPortfolios )
            .containsExactlyInAnyOrderElementsOf( expectedPortfolios );
    }
}
