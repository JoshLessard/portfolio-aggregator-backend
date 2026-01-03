package dev.joshlessard.generic.oauth;

import java.util.Map;

// TODO Watch primitives...will fail to deserialize if null
public record OAuthAccessToken(
    String accessToken,
    String tokenType,
    int expiresInSeconds,
    String refreshToken,
    Map<String, String> additionalParameters
) {
}
