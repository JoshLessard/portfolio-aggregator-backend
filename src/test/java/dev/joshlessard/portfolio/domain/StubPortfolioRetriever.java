package dev.joshlessard.portfolio.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;

public class StubPortfolioRetriever implements PortfolioRetriever {

    private final Map<OAuthAccessToken, List<Portfolio>> portfoliosByAccessToken = new HashMap<>();

    public void setPortfolios( OAuthAccessToken accessToken, List<Portfolio> portfolios ) {
        portfoliosByAccessToken.put( accessToken, portfolios );
    }

    @Override
    public List<Portfolio> retrievePortfolios( OAuthAccessToken accessToken ) {
        return Optional.ofNullable( portfoliosByAccessToken.get( accessToken ) )
            .orElseThrow( () -> new IllegalStateException( "portfolios not configured for access token: " + accessToken ) );
    }
}
