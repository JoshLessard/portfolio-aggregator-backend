package dev.joshlessard.generic.oauth.adapter.out.web;

import java.net.URI;
import java.time.Clock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import dev.joshlessard.generic.oauth.OAuthProperties;
import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;
import dev.joshlessard.generic.oauth.domain.OAuthAccessTokenRefresher;

@Component
public class RestOAuthAccessTokenRefresher implements OAuthAccessTokenRefresher {

    private final OAuthProperties properties;
    private final RestClient restClient;
    private final Clock clock;

    @Autowired
    public RestOAuthAccessTokenRefresher( OAuthProperties properties, RestClient restClient, Clock clock ) {
        this.properties = properties;
        this.restClient = restClient;
        this.clock = clock;
    }

    @Override
    public OAuthAccessToken refresh( OAuthAccessToken accessToken ) {
        // TODO I hate the way this URL is built.  Should be using a POST.
        URI refreshUri = getRefreshUri();
        return restClient
            .get()
            .uri( uriBuilder -> uriBuilder
                .scheme( refreshUri.getScheme() )
                .host( refreshUri.getHost() )
                .path( refreshUri.getPath() )
                .queryParam( "grant_type", "refresh_token" )
                .queryParam( "refresh_token", accessToken.refreshToken() )
                .build()
            )
            .retrieve()
            .body( OAuthAccessTokenDto.class )
            .toDomainObject( clock.instant() );
    }

    private URI getRefreshUri() {
        // TODO Genericize this for different token types
        return properties.getAccessToken().getRefresh().getUri().getQuestrade();
    }
}
