package dev.joshlessard.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.joshlessard.portfolio.domain.InMemoryPortfolioService;
import dev.joshlessard.portfolio.domain.PortfolioService;

@Configuration
public class PortfolioAggregatorBackendConfiguration {

    @Bean
    public PortfolioService portfolioService() {
        return new InMemoryPortfolioService();
    }
}
