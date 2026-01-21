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

    @Override
    public OAuthAccessToken refreshToken() {
        throw new UnsupportedOperationException();
    }

    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }
}
