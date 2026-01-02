package dev.joshlessard.portfolio.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joshlessard.portfolio.domain.PortfolioService;

@RestController
@RequestMapping( "/portfolios" )
public class PortfolioController {

    public PortfolioController( PortfolioService portfolioService ) {
    }

    @GetMapping
    public GetPortfoliosResponse getPortfolios() {
        return new GetPortfoliosResponse();
    }
}
