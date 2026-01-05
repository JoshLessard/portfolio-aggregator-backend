package dev.joshlessard.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import dev.joshlessard.generic.oauth.OAuthAccessTokenRepository;
import dev.joshlessard.generic.oauth.OAuthAccessTokenService;
import dev.joshlessard.portfolio.domain.PortfolioService;
import dev.joshlessard.portfolio.domain.PortfolioRetriever;
import dev.joshlessard.portfolio.domain.DefaultPortfolioService;

@Configuration
public class PortfolioAggregatorBackendConfiguration {

    @Bean
    public OAuthAccessTokenService accessTokenService( OAuthAccessTokenRepository accessTokenRepository ) {
        return new OAuthAccessTokenService( accessTokenRepository );
    }

    @Bean
    public PortfolioService portfolioService( OAuthAccessTokenService accessTokenService, PortfolioRetriever portfolioRetriever ) {
        return new DefaultPortfolioService( accessTokenService, portfolioRetriever );
    }

    @Bean
    public RestClient defaultRestClient() {
        return RestClient.create();
    }
}
