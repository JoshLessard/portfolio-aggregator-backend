package dev.joshlessard.portfolio.domain;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.generic.oauth.OAuthAccessTokenRepository;

// TODO Get rid of me
@Repository
public class InMemoryOAuthAccessTokenRepository implements OAuthAccessTokenRepository {

    private OAuthAccessToken accessToken;

    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }

    @Override
    public Optional<OAuthAccessToken> getToken() {
        return Optional.ofNullable( accessToken );
    }
}
