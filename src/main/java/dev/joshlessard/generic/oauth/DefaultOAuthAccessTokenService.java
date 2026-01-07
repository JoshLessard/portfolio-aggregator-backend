package dev.joshlessard.generic.oauth;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

class DefaultOAuthAccessTokenService implements OAuthAccessTokenService {

    private final Clock clock;
    private final OAuthAccessTokenRepository accessTokenRepository;
    private final OAuthTokenRefresher tokenRefresher;
    private final int refreshLookaheadInSeconds;

    DefaultOAuthAccessTokenService(
        Clock clock,
        OAuthAccessTokenRepository accessTokenRepository,
        OAuthTokenRefresher tokenRefresher,
        int refreshLookaheadInSeconds
    ) {
        this.clock = clock;
        this.accessTokenRepository = accessTokenRepository;
        this.tokenRefresher = tokenRefresher;
        this.refreshLookaheadInSeconds = refreshLookaheadInSeconds;
    }

    @Override
    public OAuthAccessToken getToken() {
        OAuthAccessToken accessToken = accessTokenRepository.getToken()
            .orElseThrow( NoSuchAccessTokenException::new );
        Instant expiryCutoff = clock.instant().plus(refreshLookaheadInSeconds, ChronoUnit.SECONDS );
        if ( accessToken.isExpired( expiryCutoff ) ) {
            // TODO This refresh can fail...test
            return tokenRefresher.refresh( accessToken );
        }
        return accessToken;
    }
}
