package dev.joshlessard.generic.oauth.domain;

import java.util.Optional;

// TODO Should this be with portfolio, since that's the only code that uses it?
public class StubOAuthAccessTokenService implements OAuthAccessTokenService {

    private OAuthAccessToken accessToken;

    @Override
    public OAuthAccessToken getToken() {
        return Optional.ofNullable( accessToken )
            .orElseThrow( NoSuchAccessTokenException::new );
    }

    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }
}
