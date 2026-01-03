package dev.joshlessard.portfolio.domain.questrade;

import java.util.List;

import dev.joshlessard.generic.oauth.OAuthAccessToken;
import dev.joshlessard.portfolio.domain.Portfolio;

// TODO Port package?
public interface QuestradePortfolioRetriever {

    List<Portfolio> retrievePortfolios( OAuthAccessToken accessToken );
}
