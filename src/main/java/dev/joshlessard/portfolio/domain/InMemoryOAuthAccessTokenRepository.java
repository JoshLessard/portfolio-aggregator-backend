package dev.joshlessard.portfolio.domain;

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
    public OAuthAccessToken getToken() {
        return accessToken;
    }
}
