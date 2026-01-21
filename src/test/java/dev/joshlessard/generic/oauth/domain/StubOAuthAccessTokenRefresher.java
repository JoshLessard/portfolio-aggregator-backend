package dev.joshlessard.generic.oauth.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StubOAuthAccessTokenRefresher implements OAuthAccessTokenRefresher {

    private final Map<OAuthAccessToken, OAuthAccessToken> refreshTokensByExpiredToken = new HashMap<>();

    public SetRefreshTokenFluent setRefreshToken( OAuthAccessToken refreshToken ) {
        return expiredToken -> refreshTokensByExpiredToken.put( expiredToken, refreshToken );
    }

    @Override
    public OAuthAccessToken refresh( OAuthAccessToken accessToken ) {
        return Optional.ofNullable( refreshTokensByExpiredToken.get( accessToken ) )
            .orElseThrow( () -> new IllegalStateException( "No refresh token registered for expired token " + accessToken ) );
    }

    public interface SetRefreshTokenFluent {

        void forGivenToken( OAuthAccessToken expiredToken );
    }
}
