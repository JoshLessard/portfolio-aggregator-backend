package dev.joshlessard.generic.oauth;

import java.util.Optional;

// TODO Should be package-private
public interface OAuthAccessTokenRepository {

    Optional<OAuthAccessToken> getToken();
}
