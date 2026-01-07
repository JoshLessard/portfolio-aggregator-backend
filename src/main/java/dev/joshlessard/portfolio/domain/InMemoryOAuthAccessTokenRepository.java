package dev.joshlessard.portfolio.domain;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;
import dev.joshlessard.generic.oauth.domain.OAuthAccessTokenRepository;

// TODO Move me to oauth package
// TODO Get rid of me
@Repository
public class InMemoryOAuthAccessTokenRepository implements OAuthAccessTokenRepository {

    private OAuthAccessToken accessToken;

    @Override
    public Optional<OAuthAccessToken> getToken() {
        return Optional.ofNullable( accessToken );
    }

    @Override
    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }
}
