package dev.joshlessard.portfolio.domain;

import java.util.List;

public record Portfolio( PortfolioType type, List<Position> positions ) {
}
