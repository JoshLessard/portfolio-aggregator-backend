package dev.joshlessard.portfolio.domain;

import java.util.List;

import dev.joshlessard.generic.oauth.domain.OAuthAccessToken;

// TODO Port package?
public interface PortfolioRetriever {

    // TODO Maybe I should be using a more generic "credentials" object
    List<Portfolio> retrievePortfolios( OAuthAccessToken accessToken );
}
