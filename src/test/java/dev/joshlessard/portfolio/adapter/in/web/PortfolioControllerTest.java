package dev.joshlessard.portfolio.adapter.in.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.StubPortfolioService;

public class PortfolioControllerTest {

    private final StubPortfolioService portfolioService = new StubPortfolioService();
    private final PortfolioController controller = new PortfolioController( portfolioService );

    @Test
    public void whenNoPortfoliosThenGetPortfoliosResponseHasNoPortfolios() {
        portfolioService.clearAllPortfolios();

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .isEmpty();
    }

    @Test
    public void whenOneEmptyPortfolioThenGetPortfoliosResponseContainsDtoRepresentingThatPortfolio() {
        portfolioService.addPortfolio( new Portfolio() );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto()
            );
    }
}
