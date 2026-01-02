package dev.joshlessard.portfolio.domain;

import static java.util.Collections.emptyList;

import java.util.List;

// TODO Get rid of me
public class InMemoryPortfolioService implements PortfolioService {

    @Override
    public List<Portfolio> getPortfolios() {
        return emptyList();
    }
}
