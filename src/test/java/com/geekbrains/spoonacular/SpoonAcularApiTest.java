package com.geekbrains.spoonacular;

import com.geekbrains.BaseTest;
import com.geekbrains.clients.SpoonAcularClient;
import com.geekbrains.spoonacular.model.*;
import com.geekbrains.spoonacular.model.RecipesSearchResponse;
import com.geekbrains.spoonacular.model.RecipesSearchResponseItem;
import com.geekbrains.spoonacular.model.SearchGroceryProductRequest;
import com.geekbrains.spoonacular.model.SearchGroceryProductResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.lessThan;

public class SpoonAcularApiTest extends BaseTest {

    private static final String API_KEY = "77f47619dd984e85b6d018d0189ef9fc";
    private static final String BASE_URL = "https://api.spoonacular.com";

    private static SpoonAcularClient client;


    // specification made by Builders (request/response) through the base URL //
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = BASE_URL;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", API_KEY)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        client = new SpoonAcularClient();
    }

    @Test
    void testGetComplexSearch() throws IOException {

        String actually = RestAssured.given()
                .queryParam("query", "pasta")
                .queryParam("cuisine", "italian")
                .log()
                .uri()
                .expect()
                .log()
                .body()
                /*.body("results[0].id", Matchers.notNullValue())
                .body("offset", is(0))
                .body("number", is(10))
                .body("totalResults", is(127))*/
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }

    @Test
    void testGetComplexSearchPojo() throws IOException {

        // !! response is .class wide search response !! (compare previous and current last line and the head) //
        RecipesSearchResponse actually = RestAssured.given()
                .queryParam("query", "pasta")
                .queryParam("cuisine", "italian")
                .log()
                .uri()
                .expect()
                .log()
                .body()
                .when()
                .get("recipes/complexSearch")
                .body()
                .as(RecipesSearchResponse.class);

        System.out.println(actually);

        for (RecipesSearchResponseItem item : actually.getResults()) {
            Assertions.assertNotNull(item.getId());
            Assertions.assertTrue(item.getTitle().toLowerCase(Locale.ROOT).contains("pasta"));
            Image image = ImageIO.read(new URL(item.getImage()));
            Assertions.assertNotNull(image);

        }


    }

    @Test
    void testReceiptAutoComplete() throws IOException {

        List<AutoCompleteRecipeItem> actually = RestAssured.given()
                .queryParam("query", "pizza")
                .queryParam("number", 10)
                .log()
                .uri()
                .expect()
                .log()
                .body()
                .when()
                .get("recipes/autocomplete")
                .body()
                .as(new TypeRef<>() {});

        String expected = getResourceAsString("autocomplete/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }


    // retrofit //
    @Test
    void testProductSearchGrocery() throws IOException {
        List<AutoCompleteRecipeItem> actually = RestAssured.given()
                .queryParam("query", "pizza")
                .queryParam("number", 10)
                .log()
                .uri()
                .expect()
                .log()
                .body()
                .when()
                .get("recipes/autocomplete")
                .body()
                .as(new TypeRef<>() {});

        String expected = getResourceAsString("products.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }





}
