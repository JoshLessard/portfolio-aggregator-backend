package dev.joshlessard.portfolio.domain;

import java.util.List;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;
import dev.joshlessard.generic.oauth.domain.OAuthAccessTokenService;

public class DefaultPortfolioService implements PortfolioService {

    private final OAuthAccessTokenService accessTokenService;
    private final PortfolioRetriever portfolioRetriever;

    public DefaultPortfolioService( OAuthAccessTokenService accessTokenService, PortfolioRetriever portfolioRetriever ) {
        this.accessTokenService = accessTokenService;
        this.portfolioRetriever = portfolioRetriever;
    }

    @Override
    public List<Portfolio> getPortfolios() {
        OAuthAccessToken accessToken = accessTokenService.getToken();
        try {
            return portfolioRetriever.retrievePortfolios( accessToken );
        } catch ( PortfolioAccessException e ) {
            accessToken = accessTokenService.refreshToken();
            return portfolioRetriever.retrievePortfolios( accessToken );
            // TODO What if this fails?
        }
    }
}
