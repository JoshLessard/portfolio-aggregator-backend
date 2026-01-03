package dev.joshlessard.generic.oauth;

import java.util.Map;

public record OAuthAccessToken( String accessToken, String tokenType, int expiresInSeconds, String refreshToken, Map<Object, Object> additionalParameters ) {
}
