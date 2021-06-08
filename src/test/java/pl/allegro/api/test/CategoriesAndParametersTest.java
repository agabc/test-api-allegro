package pl.allegro.api.test;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pl.allegro.api.authentication.Authentication;
import pl.allegro.api.config.ConfigurationManager;
import pl.allegro.api.csv.CategoryCsvFile;
import pl.allegro.api.csv.ReadCsvFile;
import pl.allegro.api.util.ApiHeadersBuilder;
import pl.allegro.api.util.ProjectUtils;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CategoriesAndParametersTest {
    private static Authentication authenticationToken;
    private static List<CategoryCsvFile> categoryCsvFileList;
    private static CategoryCsvFile categoryCsvFile;

    @BeforeAll
    public static void setup() throws Exception {
        authenticationToken =
                new Authentication(ConfigurationManager.getConfiguration().clientId(),
                        ConfigurationManager.getConfiguration().clientSecret());

        ReadCsvFile readCsvFile = new ReadCsvFile();
        categoryCsvFileList = readCsvFile.readCategoryCsvFile(ProjectUtils.getFilePathTestResources(
                "categories_input_file.csv"));
        Random rand = new Random();
        final int index = rand.nextInt(categoryCsvFileList.size());
        categoryCsvFile = categoryCsvFileList.get(index);
    }

    @DisplayName("when GET all categories then return 200")
    @Test
    public void whenGetCategoriesThenReturn200() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken()))
                .when()
                .get("/sale/categories")
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("categories_resp.json"))
                .assertThat().body("categories.size()", Matchers.is(categoryCsvFileList.size()));
    }

    @DisplayName("when GET all categories with invalid access token then return 401")
    @Test
    public void whenGetCategoriesWithInvalidTokenThenReturn401() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken().concat("0")))
                .when()
                .get("/sale/categories")
                .then()
                .log().all()
                .statusCode(401);
    }

    @DisplayName("when GET existing categoryId then return 200")
    @ParameterizedTest
    @CsvFileSource(resources = "/categories_input_file.csv", numLinesToSkip = 1)
    public void whenGetExistingCategoryByIdThenReturn200(String id, String categoryName) {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken()))
                .when()
                .get("/sale/categories/" + id)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.notNullValue())
                .body("name", Matchers.equalToIgnoringCase(categoryName))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("category_id_resp.json"));
    }

    @DisplayName("when GET non existing category then return 404")
    @Test
    public void whenGetNonExistingCategoryByIdThenReturn200() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken()))
                .when()
                .get("/sale/categories/" + "0000000000")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);
    }

    @DisplayName("when DELETE category then return 405")
    @Test
    public void whenDeleteCategoryThenReturn405() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken()))
                .when()
                .delete("/sale/categories/" + categoryCsvFile.getId())
                .then()
                .log().all()
                .statusCode(405);
    }

    @DisplayName("when GET category for invalid access token then return 401")
    @Test
    public void whenGetExistingCategoryWithInvalidTokenThenReturn401() {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken().concat("0")))
                .when()
                .get("/sale/categories/" + categoryCsvFile.getId())
                .then()
                .log().all()
                .assertThat()
                .statusCode(401);
    }

    @DisplayName("when GET parameters for category then return 200")
    @ParameterizedTest
    @CsvFileSource(resources = "/categories_input_file.csv", numLinesToSkip = 1)
    public void whenGetParametersForExistingCategoryThenReturn200(String id, String categoryName) {
        given()
                .baseUri(ConfigurationManager.getConfiguration().apiUri())
                .log().everything()
                .headers(ApiHeadersBuilder.builder().header(authenticationToken.getAccessToken()))
                .when()
                .get("/sale/categories/" + id + "/parameters")
                .then()
                .log().all()
                .statusCode(200)
                .body("$", not(hasValue(nullValue())));
    }

}
