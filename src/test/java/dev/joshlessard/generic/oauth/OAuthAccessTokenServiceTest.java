package dev.joshlessard.generic.oauth;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class OAuthAccessTokenServiceTest {

    private final StubOAuthAccessTokenRepository accessTokenRepository = new StubOAuthAccessTokenRepository();
    private final OAuthAccessTokenService service = new OAuthAccessTokenService( accessTokenRepository );

    @Test
    public void retrievingTokenDelegatesToRepository() {
        OAuthAccessToken expectedAccessToken = new OAuthAccessToken(
            "wickedAccessToken",
            "coolTokenType",
            123,
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
}
