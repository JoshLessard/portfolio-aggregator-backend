package dev.joshlessard.portfolio.adapter.in.web;

import dev.joshlessard.portfolio.domain.Portfolio;

public record PortfolioDto(String type) {

    public static PortfolioDto from( Portfolio portfolio ) {
        return new PortfolioDto( getType( portfolio ) );
    }

    private static String getType( Portfolio portfolio ) {
        return switch( portfolio.type() ) {
            case RRSP -> "rrsp";
            case TFSA -> "tfsa";
        };
    }
}
