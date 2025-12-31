package dev.joshlessard.portfolio.adapter.in.web;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.client.RestTestClient;

@Tag( "integration" )
@WebMvcTest( PortfolioController.class )
@AutoConfigureRestTestClient
public class PortfolioControllerIntegrationTest {

    @Autowired
    private RestTestClient client;

    @Test
    public void getPortfoliosReturns200() {
        client
            .get().uri( "/portfolios" )
            .exchange()
            .expectStatus().isEqualTo( HttpStatusCode.valueOf( 200 ) );
    }
}
