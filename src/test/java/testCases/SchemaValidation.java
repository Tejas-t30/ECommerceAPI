package testCases;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import routes.Routes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchemaValidation extends BaseClass{

    @Test
    public void testProductSchemaValidation()
    {
        int productId=configReader.getIntProperty("productId");
        given()
                .pathParams("id",productId)
        .when()
                .get(Routes.GET_PRODUCT_BY_ID)
        .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productSchema.json"));

    }

    @Test
    public void testUserSchemaValidation()
    {
        int userid=configReader.getIntProperty("userId");
        given()
                .pathParam("id",userid)
                .when()
                .get(Routes.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userSchema.json"));
    }

    @Test
    public void testCartSchemaValidation()
    {
        int id= configReader.getIntProperty("cartId");
        given()
                .pathParam("id",id)
                .when()
                .get(Routes.GET_SPECIFIC_CART_BY_ID)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("cartSchema.json"));
    }

}
