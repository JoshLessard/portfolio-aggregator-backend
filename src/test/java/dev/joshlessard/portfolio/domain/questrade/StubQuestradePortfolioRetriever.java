package dev.joshlessard.portfolio.domain.questrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.portfolio.domain.Portfolio;

public class StubQuestradePortfolioRetriever implements QuestradePortfolioRetriever {

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
