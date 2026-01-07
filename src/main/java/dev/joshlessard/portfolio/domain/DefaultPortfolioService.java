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
        return portfolioRetriever.retrievePortfolios( accessToken );
    }
}
