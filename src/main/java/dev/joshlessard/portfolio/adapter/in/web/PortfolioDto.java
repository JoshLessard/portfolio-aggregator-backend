package dev.joshlessard.portfolio.adapter.in.web;

import dev.joshlessard.portfolio.domain.Portfolio;

public record PortfolioDto() {

    public static PortfolioDto from( Portfolio portfolio ) {
        return new PortfolioDto();
    }
}
