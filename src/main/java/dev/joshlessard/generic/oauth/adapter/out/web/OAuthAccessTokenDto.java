package dev.joshlessard.generic.oauth.adapter.out.web;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;

// TODO Does this need genericizing for different providers?
// TODO I think there needs to be a refresher per provider (see all other TODOs about genericizing)
public record OAuthAccessTokenDto(
    String access_token,
    String token_type,
    int expires_in,
    String refresh_token,
    String api_server
) {

    public OAuthAccessToken toDomainObject( Instant currentInstant ) {
        return new OAuthAccessToken(
            access_token,
            token_type,
            currentInstant.plus( expires_in, ChronoUnit.SECONDS ),
            refresh_token,
            Map.of( "api_server", api_server )
        );
    }
}
