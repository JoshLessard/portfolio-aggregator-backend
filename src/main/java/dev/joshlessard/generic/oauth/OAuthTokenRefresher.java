package dev.joshlessard.generic.oauth;

interface OAuthTokenRefresher {

    OAuthAccessToken refresh( OAuthAccessToken accessToken );
}
