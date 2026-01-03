package dev.joshlessard.portfolio.domain.questrade;

import java.util.List;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.generic.oauth.OAuthAccessTokenRepository;
import dev.joshlessard.portfolio.domain.Portfolio;

public class QuestradePortfolioService {

    private final OAuthAccessTokenRepository accessTokenRepository;
    private final QuestradePortfolioRetriever portfolioRetriever;

    public QuestradePortfolioService( OAuthAccessTokenRepository accessTokenRepository, QuestradePortfolioRetriever portfolioRetriever ) {
        this.accessTokenRepository = accessTokenRepository;
        this.portfolioRetriever = portfolioRetriever;
    }

    public List<Portfolio> getPortfolios() {
        OAuthAccessToken accessToken = accessTokenRepository.getToken();
        return portfolioRetriever.retrievePortfolios( accessToken );
    }
}
