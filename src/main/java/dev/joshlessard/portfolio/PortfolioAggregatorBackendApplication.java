package dev.joshlessard.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import dev.joshlessard.generic.oauth.OAuthProperties;

@SpringBootApplication
@EnableConfigurationProperties( OAuthProperties.class )
public class PortfolioAggregatorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioAggregatorBackendApplication.class, args);
	}

}
