package dev.joshlessard.portfolio.adapter.in.web;

import java.util.List;

import dev.joshlessard.portfolio.domain.Portfolio;
import dev.joshlessard.portfolio.domain.PortfolioType;

public record PortfolioDto( String type, List<PositionDto> positions ) {

    public static PortfolioDto from( Portfolio portfolio ) {
        String type = serialize( portfolio.type() );
        List<PositionDto> positions = portfolio.positions()
            .stream()
            .map( PositionDto::from )
            .toList();
        return new PortfolioDto( type, positions );
    }

    private static String serialize( PortfolioType type ) {
        return switch( type ) {
            case RRSP -> "rrsp";
            case TFSA -> "tfsa";
        };
    }
}
