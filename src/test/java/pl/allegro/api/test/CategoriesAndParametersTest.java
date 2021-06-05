package pl.allegro.api.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.allegro.api.authentication.Authentication;
import pl.allegro.api.config.ConfigurationManager;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CategoriesAndParametersTest {
    private static Authentication authentication;

    @BeforeAll
    public static void setup() {
        authentication =
                new Authentication(ConfigurationManager.getConfiguration().clientId(),
                        ConfigurationManager.getConfiguration().clientSecret());
    }

    @Test
    public void whenGetCategoriesThenReturn200() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(Map.of("Authorization", authentication.getAccessToken(),
                        "accept", "application/vnd.allegro.public.v1+json"))
                .when()
                .get("/sale/categories")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void whenGetHomeAndGardenCategoryThenReturn200() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(Map.of("Authorization", authentication.getAccessToken(),
                        "accept", "application/vnd.allegro.public.v1+json"))
                .when()
                .get("/sale/categories/5")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void whenDeleteHomeAndGardenCategoryThenReturn200() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(Map.of("Authorization", authentication.getAccessToken(),
                        "accept", "application/vnd.allegro.public.v1+json"))
                .when()
                .delete("/sale/categories/5")
                .then()
                .log().all()
                .statusCode(415);
    }

    @Test
    public void whenGetParametersForHomeAndGardenCategoryThenReturn200() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(Map.of("Authorization", authentication.getAccessToken(),
                        "accept", "application/vnd.allegro.public.v1+json"))
                .when()
                .get("/sale/categories/5/parameters")
                .then()
                .log().all()
                .statusCode(200);
    }
}
