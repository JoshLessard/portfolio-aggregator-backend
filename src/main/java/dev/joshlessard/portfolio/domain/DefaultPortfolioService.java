package dev.joshlessard.portfolio.domain;

import java.util.List;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.generic.oauth.OAuthAccessTokenService;
import dev.joshlessard.portfolio.domain.questrade.QuestradePortfolioRetriever;

public class DefaultPortfolioService implements PortfolioService {

    private final OAuthAccessTokenService accessTokenService;
    private final QuestradePortfolioRetriever portfolioRetriever;

    public DefaultPortfolioService( OAuthAccessTokenService accessTokenService, QuestradePortfolioRetriever portfolioRetriever ) {
        this.accessTokenService = accessTokenService;
        this.portfolioRetriever = portfolioRetriever;
    }

    @Override
    public List<Portfolio> getPortfolios() {
        OAuthAccessToken accessToken = accessTokenService.getToken();
        return portfolioRetriever.retrievePortfolios( accessToken );
    }
}
