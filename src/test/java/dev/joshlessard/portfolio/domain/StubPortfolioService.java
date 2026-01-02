package dev.joshlessard.portfolio.domain;

import java.util.ArrayList;
import java.util.List;

public class StubPortfolioService implements PortfolioService {

    private final List<?> portfolios = new ArrayList<>();

    public void clearAllPortfolios() {
        portfolios.clear();
    }
}
