package dev.joshlessard.generic.oauth.domain;

import java.util.Optional;

public class StubOAuthAccessTokenRepository implements OAuthAccessTokenRepository {

    private OAuthAccessToken accessToken;

    @Override
    public Optional<OAuthAccessToken> getToken() {
        return Optional.ofNullable( accessToken );
    }

    @Override
    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }

    public void clearTokens() {
        accessToken = null;
    }
}
