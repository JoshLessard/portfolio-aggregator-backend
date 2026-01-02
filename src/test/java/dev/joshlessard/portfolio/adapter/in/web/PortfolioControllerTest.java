package dev.joshlessard.portfolio.adapter.in.web;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.PortfolioType;
import dev.joshlessard.portfolio.domain.Position;
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
        portfolioService.addPortfolios( new Portfolio( PortfolioType.RRSP, emptyList() ) );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto( "rrsp", emptyList() )
            );
    }

    @Test
    public void whenOneEmptyTfsaPortfolioThenGetPortfoliosResponseContainsDtoRepresentingThatPortfolio() {
        portfolioService.addPortfolios( new Portfolio( PortfolioType.TFSA, emptyList() ) );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto( "tfsa", emptyList() )
            );
    }

    @Test
    public void whenOneEmptyRrspAndOneEmptyTfsaPortfolioThenGetPortfoliosResponseContainsDtoRepresentingBothPortfolios() {
        portfolioService.addPortfolios(
            new Portfolio( PortfolioType.RRSP, emptyList() ),
            new Portfolio( PortfolioType.TFSA, emptyList() )
        );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactlyInAnyOrder(
                new PortfolioDto( "rrsp", emptyList() ),
                new PortfolioDto( "tfsa", emptyList() )
            );
    }

    @Test
    public void whenOneRrspPortfolioWithOnePositionThenGetPortfoliosResponseContainsDtoRepresentingThatPortfolio() {
        portfolioService.addPortfolios(
            new Portfolio(
                PortfolioType.RRSP,
                List.of(
                    new Position( "MCD", 21 )
                )
            )
        );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto(
                    "rrsp",
                    List.of(
                        new PositionDto( "MCD", 21 )
                    )
                )
            );
    }

    @Test
    public void whenOneRrspPortfolioWithTwoPositionsThenGetPortfoliosResponseContainsDtoRepresentingThatPortfolio() {
        portfolioService.addPortfolios(
            new Portfolio(
                PortfolioType.RRSP,
                List.of(
                    new Position( "VCN", 1234.7 ),
                    new Position( "XAW", 739 )
                )
            )
        );

        GetPortfoliosResponse response = controller.getPortfolios();

        assertThat( response.portfolios() )
            .containsExactly(
                new PortfolioDto(
                    "rrsp",
                    List.of(
                        new PositionDto( "VCN", 1234.7 ),
                        new PositionDto( "XAW", 739 )
                    )
                )
            );
    }
}
