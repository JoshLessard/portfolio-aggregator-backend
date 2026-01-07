package dev.joshlessard.portfolio.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;
import dev.joshlessard.generic.oauth.domain.OAuthAccessTokenRepository;

// TODO Move me to oauth package
// TODO Get rid of me (and disable "testdata" profile)
@Component
@Profile( "testdata" )
public class DataLoader {

    private final InMemoryOAuthAccessTokenRepository accessTokenRepository;

    @Autowired
    public DataLoader( OAuthAccessTokenRepository accessTokenRepository ) {
        this.accessTokenRepository = (InMemoryOAuthAccessTokenRepository) accessTokenRepository;
    }

    @EventListener( ApplicationReadyEvent.class )
    public void loadData() {
        accessTokenRepository.setToken(
            new OAuthAccessToken(
                "iN70kIYYB1pDDFrRxAhdAgUf3I4mvWZm0",
                "Bearer",
                Instant.now().plus( 10, ChronoUnit.SECONDS ),
                "_cy6kpsoiMGPAi-ZyG5pWjIvGAjIq4FE0",
                Map.of( "api_server", "https://api01.iq.questrade.com/" )
            )
        );
    }
}
