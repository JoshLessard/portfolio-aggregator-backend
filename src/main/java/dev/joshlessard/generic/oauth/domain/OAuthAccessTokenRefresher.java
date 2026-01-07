package dev.joshlessard.generic.oauth.domain;

public interface OAuthAccessTokenRefresher {

    OAuthAccessToken refresh( OAuthAccessToken accessToken );
}
