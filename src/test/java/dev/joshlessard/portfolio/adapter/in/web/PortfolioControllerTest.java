package dev.joshlessard.portfolio.adapter.in.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.PortfolioType;
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
    public void whenOneEmptyRrspPortfolioThenGetPortfoliosResponseContainsDtoRepresentingThatPortfolio() {
        portfolioService.addPortfolios( new Portfolio( PortfolioType.RRSP ) );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto( "rrsp" )
            );
    }

    @Test
    public void whenOneEmptyTfsaPortfolioThenGetPortfoliosResponseContainsDtoRepresentingThatPortfolio() {
        portfolioService.addPortfolios( new Portfolio( PortfolioType.TFSA ) );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto( "tfsa" )
            );
    }

    @Test
    public void whenOneEmptyRrspAndOneEmptyTfsaPortfolioThenGetPortfoliosResponseContainsDtoRepresentingBothPortfolios() {
        portfolioService.addPortfolios(
            new Portfolio( PortfolioType.RRSP ),
            new Portfolio( PortfolioType.TFSA )
        );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactlyInAnyOrder(
                new PortfolioDto( "rrsp" ),
                new PortfolioDto( "tfsa" )
            );
    }
}
