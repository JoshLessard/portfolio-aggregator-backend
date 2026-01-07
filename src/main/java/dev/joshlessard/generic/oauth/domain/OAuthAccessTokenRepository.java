package dev.joshlessard.generic.oauth.domain;

import java.util.Optional;

// TODO Should be package-private
public interface OAuthAccessTokenRepository {

    Optional<OAuthAccessToken> getToken();
}
