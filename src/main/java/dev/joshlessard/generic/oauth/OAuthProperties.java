package dev.joshlessard.generic.oauth;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties( prefix = "oauth" )
public class OAuthProperties {

    private final AccessToken accessToken = new AccessToken();

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public static class AccessToken {

        private final Refresh refresh = new Refresh();

        public Refresh getRefresh() {
            return refresh;
        }

        public static class Refresh {

            private final Uri uri = new Uri();

            public Uri getUri() {
                return uri;
            }

            private int lookaheadInSeconds;

            public int getLookaheadInSeconds() {
                return lookaheadInSeconds;
            }

            public void setLookaheadInSeconds( int lookaheadInSeconds ) {
                this.lookaheadInSeconds = lookaheadInSeconds;
            }

            public static class Uri {

                private URI questrade;

                public URI getQuestrade() {
                    return questrade;
                }

                public void setQuestrade( URI questrade ) {
                    this.questrade = questrade;
                }
            }
        }
    }
}
