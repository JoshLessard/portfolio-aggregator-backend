package dev.joshlessard.generic.oauth;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.joshlessard.generic.oauth.domain.DefaultOAuthAccessTokenService;
import dev.joshlessard.generic.oauth.domain.OAuthAccessTokenRepository;
import dev.joshlessard.generic.oauth.domain.OAuthAccessTokenService;

@Configuration
public class OAuthConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public OAuthAccessTokenService accessTokenService( Clock clock, OAuthAccessTokenRepository accessTokenRepository, OAuthProperties properties ) {
        return new DefaultOAuthAccessTokenService(
            clock,
            accessTokenRepository,
            null, // TODO Fix me
            properties.getAccessToken().getRefresh().getLookaheadInSeconds()
        );
    }
}
