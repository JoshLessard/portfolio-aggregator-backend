package dev.joshlessard.portfolio.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/portfolios" )
public class PortfolioController {

    @GetMapping
    public void getPortfolios() {
    }
}
