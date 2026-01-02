package dev.joshlessard.portfolio.adapter.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joshlessard.portfolio.domain.PortfolioService;

@RestController
@RequestMapping( "/portfolios" )
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController( PortfolioService portfolioService ) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public GetPortfoliosResponse getPortfolios() {
        List<PortfolioDto> portfolios = portfolioService.getPortfolios()
            .stream()
            .map( PortfolioDto::from )
            .toList();
        return new GetPortfoliosResponse( portfolios );
    }
}
