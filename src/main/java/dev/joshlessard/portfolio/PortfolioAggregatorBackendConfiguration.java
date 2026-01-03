package dev.joshlessard.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import dev.joshlessard.generic.oauth.OAuthAccessTokenRepository;
import dev.joshlessard.portfolio.domain.PortfolioService;
import dev.joshlessard.portfolio.domain.questrade.QuestradePortfolioRetriever;
import dev.joshlessard.portfolio.domain.questrade.QuestradePortfolioService;

@Configuration
public class PortfolioAggregatorBackendConfiguration {

    @Bean
    public PortfolioService portfolioService( OAuthAccessTokenRepository accessTokenRepository, QuestradePortfolioRetriever portfolioRetriever ) {
        return new QuestradePortfolioService( accessTokenRepository, portfolioRetriever );
    }

    @Bean
    public RestClient defaultRestClient() {
        return RestClient.create();
    }
}
