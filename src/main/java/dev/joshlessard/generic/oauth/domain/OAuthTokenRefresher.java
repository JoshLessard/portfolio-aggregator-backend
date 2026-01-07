package dev.joshlessard.generic.oauth.domain;

interface OAuthTokenRefresher {

    OAuthAccessToken refresh( OAuthAccessToken accessToken );
}
