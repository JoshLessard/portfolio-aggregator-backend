package dev.joshlessard.generic.oauth;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO Move non-Spring classes to "domain" subpackage
@Configuration
public class OAuthConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public OAuthAccessTokenService accessTokenService( Clock clock, OAuthAccessTokenRepository accessTokenRepository ) {
        return new DefaultOAuthAccessTokenService( clock, accessTokenRepository, null ); // TODO Fix me
    }
}
