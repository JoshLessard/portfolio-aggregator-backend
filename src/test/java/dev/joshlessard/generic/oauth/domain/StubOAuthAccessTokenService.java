package dev.joshlessard.generic.oauth.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO Should this be with portfolio, since that's the only code that uses it?
public class StubOAuthAccessTokenService implements OAuthAccessTokenService {

    private OAuthAccessToken accessToken;
    private Map<OAuthAccessToken, OAuthAccessToken> refreshTokens = new HashMap<>();

    @Override
    public OAuthAccessToken getToken() {
        return Optional.ofNullable( accessToken )
            .orElseThrow( NoSuchAccessTokenException::new );
    }

    @Override
    public OAuthAccessToken refreshToken() {
        return Optional.ofNullable( refreshTokens.get( accessToken ) )
            .orElseThrow( () -> new IllegalStateException( "No refresh token configured for " + accessToken ) );
    }

    public void setToken( OAuthAccessToken accessToken ) {
        this.accessToken = accessToken;
    }

    public SetRefreshTokenFluent setRefreshToken( OAuthAccessToken validRefreshedToken ) {
        return invalidToken -> refreshTokens.put( invalidToken, validRefreshedToken );
    }

    public interface SetRefreshTokenFluent {
        void forGivenToken( OAuthAccessToken token );
    }
}
