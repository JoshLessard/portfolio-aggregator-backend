package dev.joshlessard.portfolio.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
        // Inject real token here for testing
    }
}
