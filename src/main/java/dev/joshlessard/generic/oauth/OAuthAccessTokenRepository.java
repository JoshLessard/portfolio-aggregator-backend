package dev.joshlessard.generic.oauth;

import java.util.Optional;

public interface OAuthAccessTokenRepository {

    Optional<OAuthAccessToken> getToken();
}
