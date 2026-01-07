package dev.joshlessard.generic.oauth;

import java.time.Instant;
import java.util.Map;

public record OAuthAccessToken(
    String accessToken,
    String tokenType,
    Instant expiry,
    String refreshToken,
    Map<String, String> additionalParameters
) {

    public boolean isExpired( Instant now ) {
        return expiry.equals( now ) || expiry.isBefore( now );
    }
}
