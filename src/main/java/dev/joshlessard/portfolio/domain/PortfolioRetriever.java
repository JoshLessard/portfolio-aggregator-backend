package dev.joshlessard.portfolio.domain;

import java.util.List;

import dev.joshlessard.generic.oauth.OAuthAccessToken;

// TODO Port package?
public interface PortfolioRetriever {

    List<Portfolio> retrievePortfolios( OAuthAccessToken accessToken );
}
