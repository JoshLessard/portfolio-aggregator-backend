package dev.joshlessard.portfolio.domain;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.generic.oauth.StubOAuthAccessTokenService;

public class DefaultPortfolioServiceTest {

    private final StubOAuthAccessTokenService accessTokenService = new StubOAuthAccessTokenService();
    private final StubPortfolioRetriever portfolioRetriever = new StubPortfolioRetriever();
    private final DefaultPortfolioService portfolioService = new DefaultPortfolioService( accessTokenService, portfolioRetriever );

    @Test
    public void accessTokenIsRetrievedFromOAuthServiceAndUsedToGetPortfoliosFromRetriever() {
        OAuthAccessToken accessToken = new OAuthAccessToken( "some_access_token", "some_token_type", Instant.now(), "some_refresh_token", emptyMap() );
        accessTokenService.setToken( accessToken );
        List<Portfolio> expectedPortfolios = List.of(
            new Portfolio( PortfolioType.TFSA, List.of( new Position( "TSLA", 14.396 ) ) )
        );
        portfolioRetriever.setPortfolios( accessToken, expectedPortfolios );

        List<Portfolio> actualPortfolios = portfolioService.getPortfolios();

        assertThat( actualPortfolios )
            .containsExactlyInAnyOrderElementsOf( expectedPortfolios );
    }
}
