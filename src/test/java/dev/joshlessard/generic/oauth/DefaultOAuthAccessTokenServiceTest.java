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
    private final DefaultOAuthAccessTokenService service = new DefaultOAuthAccessTokenService( clock, accessTokenRepository, tokenRefresher );

    @Test
    public void retrievingTokenDelegatesToRepository() {
        OAuthAccessToken expectedAccessToken = new OAuthAccessToken(
            "wickedAccessToken",
            "coolTokenType",
            Instant.now(),
            "excellentRefreshToken",
            emptyMap()
        );
        accessTokenRepository.setToken( expectedAccessToken );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( expectedAccessToken );
    }

    @Test
    public void retrievingNonexistentTokenThrowsException() {
        accessTokenRepository.clearTokens();

        assertThatThrownBy( service::getToken )
            .isInstanceOf( NoSuchAccessTokenException.class );
    }

    @Test
    public void retrievingTokenThatExpiredInThePastRefreshesTokenBeforeReturningIt() {
        OAuthAccessToken expiredToken = expiredAccessToken( 1, ChronoUnit.SECONDS );
        accessTokenRepository.setToken( expiredToken );
        OAuthAccessToken refreshedToken = new OAuthAccessToken(
            "refreshedAccessToken",
            "refreshed",
            clock.instant().plus( 3600, ChronoUnit.SECONDS ),
            "anotherRefreshToken",
            emptyMap()
        );
        tokenRefresher
            .setRefreshToken( refreshedToken )
            .forExpiredToken( expiredToken );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( refreshedToken );
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

    @Test
    public void retrievingTokenThatExpiresNowRefreshesTokenBeforeReturningIt() {
        OAuthAccessToken expiredToken = expiredAccessToken( 0, ChronoUnit.MILLIS );
        accessTokenRepository.setToken( expiredToken );
        OAuthAccessToken refreshedToken = new OAuthAccessToken(
            "refreshedAccessToken",
            "refreshed",
            clock.instant().plus( 3600, ChronoUnit.SECONDS ),
            "anotherRefreshToken",
            emptyMap()
        );
        tokenRefresher
            .setRefreshToken( refreshedToken )
            .forExpiredToken( expiredToken );

        OAuthAccessToken actualAccessToken = service.getToken();

        assertThat( actualAccessToken )
            .isSameAs( refreshedToken );
    }
}
