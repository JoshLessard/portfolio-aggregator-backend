package dev.joshlessard.generic.oauth.domain;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DefaultOAuthAccessTokenService implements OAuthAccessTokenService {

    private final Clock clock;
    private final OAuthAccessTokenRepository accessTokenRepository;
    private final OAuthAccessTokenRefresher tokenRefresher;
    private final int refreshLookaheadInSeconds;

    public DefaultOAuthAccessTokenService(
        Clock clock,
        OAuthAccessTokenRepository accessTokenRepository,
        OAuthAccessTokenRefresher tokenRefresher,
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
        Instant expiryCutoff = clock.instant().plus( refreshLookaheadInSeconds, ChronoUnit.SECONDS );
        if ( accessToken.isExpired( expiryCutoff ) ) {
            accessToken = refreshAndReplace( accessToken );
        }
        return accessToken;
    }

    @Override
    public OAuthAccessToken refreshToken() {
        OAuthAccessToken accessToken = accessTokenRepository.getToken()
            .orElseThrow( NoSuchAccessTokenException::new );
        return refreshAndReplace( accessToken );
    }

    private OAuthAccessToken refreshAndReplace( OAuthAccessToken accessToken ) {
        // TODO This refresh can fail...test and handle
        accessToken = tokenRefresher.refresh( accessToken );
        accessTokenRepository.setToken( accessToken );
        return accessToken;
    }
}
