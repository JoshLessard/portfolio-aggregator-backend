package dev.joshlessard.portfolio.adapter.in.web;

import dev.joshlessard.portfolio.domain.Position;

record PositionDto( String ticker, double quantity ) {

    static PositionDto from( Position position ) {
        return new PositionDto( position.ticker(), position.quantity() );
    }
}
