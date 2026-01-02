package dev.joshlessard.portfolio.adapter.in.web;

import java.util.List;

public record GetPortfoliosResponse( List<PortfolioDto> portfolios ) {
}
