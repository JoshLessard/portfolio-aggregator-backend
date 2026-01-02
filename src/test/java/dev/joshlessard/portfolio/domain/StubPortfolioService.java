package dev.joshlessard.portfolio.domain;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

public class StubPortfolioService implements PortfolioService {

    private final List<Portfolio> portfolios = new ArrayList<>();

    public void clearAllPortfolios() {
        portfolios.clear();
    }

    public void addPortfolio( Portfolio portfolio ) {
        portfolios.add( portfolio );
    }

    @Override
    public List<Portfolio> getPortfolios() {
        return portfolios;
    }
}
