package dev.joshlessard.generic.oauth;

public class OAuthAccessTokenService {

    private final OAuthAccessTokenRepository accessTokenRepository;

    public OAuthAccessTokenService( OAuthAccessTokenRepository accessTokenRepository ) {
        this.accessTokenRepository = accessTokenRepository;
    }

    public OAuthAccessToken getToken() {
        return accessTokenRepository.getToken()
            .orElseThrow( NoSuchAccessTokenException::new );
    }
}
