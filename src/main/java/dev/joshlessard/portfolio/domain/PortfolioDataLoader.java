package dev.joshlessard.portfolio.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// TODO Get rid of me (and disable "testdata" profile)
@Component
@Profile( "testdata" )
public class PortfolioDataLoader {

    private final InMemoryPortfolioService portfolioService;

    @Autowired
    public PortfolioDataLoader( PortfolioService portfolioService ) {
        this.portfolioService = (InMemoryPortfolioService) portfolioService;
    }

    @EventListener( ApplicationReadyEvent.class )
    public void loadPortfolioData() {
        portfolioService.addPortfolios(
            new Portfolio(
                PortfolioType.TFSA,
                List.of(
                    new Position( "MCD", 21.5392 ),
                    new Position( "VCN.TO", 1210 ),
                    new Position( "XAW.TO", 2729 )
                )
            ),
            new Portfolio(
                PortfolioType.RRSP,
                List.of(
                    new Position( "VCN.TO", 2896 ),
                    new Position( "XAW.TO", 7042 ),
                    new Position( "ZAG.TO", 3325 )
                )
            )
        );
    }
}
