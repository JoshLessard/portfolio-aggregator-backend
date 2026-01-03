package dev.joshlessard.generic.oauth;

import java.util.Optional;

public class StubOAuthAccessTokenRepository implements OAuthAccessTokenRepository {

    private OAuthAccessToken accessToken;

    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }

    @Override
    public OAuthAccessToken getToken() {
        return Optional.ofNullable( accessToken )
            .orElseThrow( () -> new IllegalStateException( "accessToken has not been configured" ) );
    }
}
