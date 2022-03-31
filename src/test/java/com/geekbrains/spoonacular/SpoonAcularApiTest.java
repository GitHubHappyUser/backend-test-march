package com.geekbrains.spoonacular;

import com.geekbrains.BaseTest;
import com.geekbrains.spoonacular.model.RecipesSearchResponse;
import com.geekbrains.spoonacular.model.RecipesSearchResponseItem;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.javacrumbs.jsonunit.JsonAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class SpoonAcularApiTest extends BaseTest {

    private static final String API_KEY = "77f47619dd984e85b6d018d0189ef9fc";
    private static final String BASE_URL = "https://api.spoonacular.com";


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

        for (RecipesSearchResponseItem item : actually.getResults()){
            Assertions.assertNotNull(item.getId());
            Assertions.assertTrue(item.getTitle().toLowerCase(Locale.ROOT).contains("pasta"));
            Image image = ImageIO.read(new URL(item.getImage()));
            Assertions.assertNotNull(image);

//            System.out.println(image);

            /*JFrame frame = new JFrame();
            frame.setIconImage(image);
            frame.setSize(100,100);
            frame.show();*/
        }

      /*  String expected = getResourceAsString("expected.json");


        // assertion transferring the Java format to assertion format //
        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );*/

    }

}
