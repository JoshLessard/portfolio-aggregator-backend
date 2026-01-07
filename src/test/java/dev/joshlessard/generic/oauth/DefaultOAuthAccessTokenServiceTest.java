package dev.joshlessard.generic.oauth;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.junit.jupiter.api.Test;

public class DefaultOAuthAccessTokenServiceTest {

    private final Clock clock = Clock.fixed( Instant.now(), ZoneId.systemDefault() );
    private final StubOAuthAccessTokenRepository accessTokenRepository = new StubOAuthAccessTokenRepository();
    private final StubOAuthTokenRefresher tokenRefresher = new StubOAuthTokenRefresher();
    private final DefaultOAuthAccessTokenService service = new DefaultOAuthAccessTokenService( clock, accessTokenRepository, tokenRefresher, 0 );

    @Test
    public void retrievingNonexistentTokenThrowsException() {
        accessTokenRepository.clearTokens();

        assertThatThrownBy( service::getToken )
            .isInstanceOf( NoSuchAccessTokenException.class );
    }

    @Test
    public void whenTokenExpiresInTheFutureThenTokenIsReturned() {
        OAuthAccessToken expectedAccessToken = accessTokenExpiringIn( 1, ChronoUnit.NANOS );
        accessTokenRepository.setToken( expectedAccessToken );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( expectedAccessToken );
    }

    @Test
    public void whenTokenExpiresNowThenItIsRefreshed() {
        OAuthAccessToken expiredToken = expiredAccessToken( 0, ChronoUnit.NANOS );
        accessTokenRepository.setToken( expiredToken );
        OAuthAccessToken refreshedToken = nonexpiredAccessToken();
        tokenRefresher
            .setRefreshToken( refreshedToken )
            .forExpiredToken( expiredToken );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( refreshedToken );
    }

    @Test
    public void whenTokenExpiredInThePastThenItIsRefreshed() {
        OAuthAccessToken expiredToken = expiredAccessToken( 1, ChronoUnit.NANOS );
        accessTokenRepository.setToken( expiredToken );
        OAuthAccessToken refreshedToken = nonexpiredAccessToken();
        tokenRefresher
            .setRefreshToken( refreshedToken )
            .forExpiredToken( expiredToken );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( refreshedToken );
    }

    @Test
    public void whenTokenExpiresAfterRefreshLookaheadThenItIsReturned() {
        OAuthAccessToken tokenExpiringAfterLookahead = accessTokenExpiringIn( 10, ChronoUnit.SECONDS );
        accessTokenRepository.setToken( tokenExpiringAfterLookahead );
        DefaultOAuthAccessTokenService service = new DefaultOAuthAccessTokenService(
            clock, accessTokenRepository, tokenRefresher,
            9
        );
    }

    @Test
    public void whenTokenExpiresAtRefreshLookaheadThenItIsRefreshed() {
        OAuthAccessToken tokenExpiringAtLookahead = accessTokenExpiringIn( 5, ChronoUnit.SECONDS );
        accessTokenRepository.setToken( tokenExpiringAtLookahead );
        OAuthAccessToken refreshedToken = nonexpiredAccessToken();
        tokenRefresher
            .setRefreshToken( refreshedToken )
            .forExpiredToken( tokenExpiringAtLookahead );
        DefaultOAuthAccessTokenService service = new DefaultOAuthAccessTokenService(
            clock, accessTokenRepository, tokenRefresher,
            5
        );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( refreshedToken );
    }

    @Test
    public void whenTokenExpiresBeforeRefreshLookaheadThenItIsRefreshed() {
        OAuthAccessToken tokenExpiringBeforeLookahead = accessTokenExpiringIn( 7, ChronoUnit.SECONDS );
        accessTokenRepository.setToken( tokenExpiringBeforeLookahead );
        OAuthAccessToken refreshedToken = nonexpiredAccessToken();
        tokenRefresher
            .setRefreshToken( refreshedToken )
            .forExpiredToken( tokenExpiringBeforeLookahead );
        DefaultOAuthAccessTokenService service = new DefaultOAuthAccessTokenService(
            clock, accessTokenRepository, tokenRefresher,
            8
        );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( refreshedToken );
    }

    private OAuthAccessToken nonexpiredAccessToken() {
        return accessTokenExpiringIn( 3600, ChronoUnit.SECONDS );
    }

    private OAuthAccessToken accessTokenExpiringIn( long amountInTheFuture, TemporalUnit unit ) {
        return new OAuthAccessToken(
            "nonexpiredAccessToken",
            "nonexpired",
            clock.instant().plus( amountInTheFuture, unit ),
            "excellentRefreshToken",
            emptyMap()
        );
    }

    private OAuthAccessToken expiredAccessToken( long amountInThePast, TemporalUnit unit ) {
        return new OAuthAccessToken(
            "expiredAccessToken",
            "expired",
            clock.instant().minus( amountInThePast, unit ),
            "refreshToken",
            emptyMap()
        );
    }
}
