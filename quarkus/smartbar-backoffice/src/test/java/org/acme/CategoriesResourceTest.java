package org.acme;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.acme.model.Category;
import org.acme.service.CategoriesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;


@QuarkusTest
class CategoriesResourceTest {

    @InjectMock
    CategoriesService categoriesServiceMock;

    @BeforeEach
    void setUp() {
        Mockito.when(categoriesServiceMock.get()).thenReturn(new Category().name("drinks"));
    }

    @Test
    void getListOfCategories() {
        final Response response = given()
          .when().get("/categories")
          .then()
             .statusCode(200)
                .extract().response();

        final JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("drinks", jsonPath.getString("[0].name"));
    }
}