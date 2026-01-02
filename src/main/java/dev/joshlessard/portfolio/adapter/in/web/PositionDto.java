package dev.joshlessard.portfolio.adapter.in.web;

import dev.joshlessard.portfolio.domain.Position;

public record PositionDto( String ticker, double quantity ) {

    public static PositionDto from( Position position ) {
        return new PositionDto( position.ticker(), position.quantity() );
    }
}
