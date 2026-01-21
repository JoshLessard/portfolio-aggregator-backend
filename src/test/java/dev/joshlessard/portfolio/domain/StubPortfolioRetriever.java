package dev.joshlessard.portfolio.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;

public class StubPortfolioRetriever implements PortfolioRetriever {

    private final Map<OAuthAccessToken, List<Portfolio>> portfoliosByAccessToken = new HashMap<>();
    private final Set<OAuthAccessToken> invalidAccessTokens = new HashSet<>();

    public void setPortfolios( OAuthAccessToken accessToken, List<Portfolio> portfolios ) {
        portfoliosByAccessToken.put( accessToken, portfolios );
    }

    @Override
    public List<Portfolio> retrievePortfolios( OAuthAccessToken accessToken ) {
        if ( invalidAccessTokens.contains( accessToken ) ) {
            throw new PortfolioAccessException();
        }
        return Optional.ofNullable( portfoliosByAccessToken.get( accessToken ) )
            .orElseThrow( () -> new IllegalStateException( "portfolios not configured for access token: " + accessToken ) );
    }

    public void throwAccessExceptionFor( OAuthAccessToken invalidToken ) {
        invalidAccessTokens.add( invalidToken );
    }
}
