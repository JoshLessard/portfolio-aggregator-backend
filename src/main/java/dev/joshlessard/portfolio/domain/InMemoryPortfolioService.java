package dev.joshlessard.portfolio.domain;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

// TODO Get rid of me
public class InMemoryPortfolioService implements PortfolioService {

    private final List<Portfolio> portfolios = new ArrayList<>();

    public void addPortfolios( Portfolio... portfolios ) {
        this.portfolios.addAll( asList( portfolios ) );
    }

    @Override
    public List<Portfolio> getPortfolios() {
        return portfolios;
    }
}
