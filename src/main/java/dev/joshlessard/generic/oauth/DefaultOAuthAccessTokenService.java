package dev.joshlessard.generic.oauth;

import java.time.Clock;

class DefaultOAuthAccessTokenService implements OAuthAccessTokenService {

    private final Clock clock;
    private final OAuthAccessTokenRepository accessTokenRepository;
    private final OAuthTokenRefresher tokenRefresher;

    DefaultOAuthAccessTokenService( Clock clock, OAuthAccessTokenRepository accessTokenRepository, OAuthTokenRefresher tokenRefresher ) {
        this.clock = clock;
        this.accessTokenRepository = accessTokenRepository;
        this.tokenRefresher = tokenRefresher;
    }

    @Override
    public OAuthAccessToken getToken() {
        OAuthAccessToken accessToken = accessTokenRepository.getToken()
            .orElseThrow( NoSuchAccessTokenException::new );
        if ( accessToken.isExpired( clock.instant() ) ) {
            // TODO This refresh can fail...test
            return tokenRefresher.refresh( accessToken );
        }
        return accessToken;
    }
}
