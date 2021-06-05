package pl.allegro.api.authentication;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.entity.ContentType;
import pl.allegro.api.config.ConfigurationManager;
import pl.allegro.api.util.StringUtil;

import java.time.Instant;
import java.util.Map;

public class Authentication {

    private String clientId;
    private String clientSecret;
    private String accessToken = null;
    private String refreshToken = null;
    private Instant accessTokenExpiration;

    public Authentication(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getAccessToken() {
        if (isAccessTokenValid()) {
            return accessToken;
        } else {
            if (isRefreshAccessTokenValid()) {
                return generateRefreshAccessToken();
            } else {
                return generateAccessToken();
            }
        }
    }

    private String generateAccessToken() {
        AuthTokenResponse tokenResponse = RestAssured
                .given()
                .log().everything()
                .baseUri(ConfigurationManager.getConfiguration().authUri())
                .contentType(String.valueOf(ContentType.APPLICATION_FORM_URLENCODED))
                .headers(Map.of("Authorization", getAuthorizationHeader()))
                .param("grant_type", "client_credentials")
                .when()
                .post()
                .then()
                .extract()
                .body().as(AuthTokenResponse.class, ObjectMapperType.GSON);
        accessTokenExpiration = Instant.now().plusSeconds(tokenResponse.getExpiresIn());
        accessToken = "Bearer " + tokenResponse.getAccessToken();
        return accessToken;
    }

    private String generateRefreshAccessToken() {
        AuthTokenResponse authTokenResponse;
        authTokenResponse = RestAssured.given()
                .baseUri(ConfigurationManager.getConfiguration().authUri())
                .contentType(String.valueOf(ContentType.APPLICATION_FORM_URLENCODED))
                .headers(Map.of("Authorization", getAuthorizationHeader()))
                .param("grant_type", "refresh_token")
                .param("refresh_token", refreshToken)
                .post()
                .as(AuthTokenResponse.class, ObjectMapperType.GSON);
        accessTokenExpiration = Instant.now().plusSeconds(authTokenResponse.getExpiresIn());
        accessToken = authTokenResponse.getAccessToken();
        return accessToken;
    }

    private String getAuthorizationHeader() {
        String authorization =
                StringUtil.stringToBase64(clientId + ":" + clientSecret);
        return "Basic " + authorization;
    }

//    private String refreshToken() {
//        RestAssured.given()
//                .baseUri(ConfigurationManager.getConfiguration().authUrl())
//                .contentType(String.valueOf(ContentType.APPLICATION_FORM_URLENCODED))
//                .headers(Map.of("Authorization", getAuthorizationHeader()))
//                .param("grant_type", "client_credentials")
//                .post()
//                .as(AuthTokenResponse.class);
//        return "";
//    }

    private boolean isAccessTokenValid() {
        if (accessToken == null || accessTokenExpiration == null) {
            return false;
        } else {
            //minus 30 sec to make sure that the last call will finish with success
            return !Instant.now().isAfter(accessTokenExpiration.minusSeconds(30));
        }
    }

    private boolean isRefreshAccessTokenValid() {
        if (refreshToken == null || accessTokenExpiration == null) {
            return false;
        } else {
            return Instant.now().isAfter(accessTokenExpiration);
        }
    }
}
