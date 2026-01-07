package dev.joshlessard.generic.oauth.domain;

interface OAuthAccessTokenRefresher {

    OAuthAccessToken refresh( OAuthAccessToken accessToken );
}
