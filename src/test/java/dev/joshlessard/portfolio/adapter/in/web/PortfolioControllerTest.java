package dev.joshlessard.portfolio.adapter.in.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import dev.joshlessard.portfolio.domain.StubPortfolioService;

public class PortfolioControllerTest {

    @Test
    public void whenNoPortfoliosThenGetPortfoliosResponseHasNoPortfolios() {
        StubPortfolioService portfolioService = new StubPortfolioService();
        portfolioService.clearAllPortfolios();
        PortfolioController controller = new PortfolioController( portfolioService );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .isEmpty();
    }
}
