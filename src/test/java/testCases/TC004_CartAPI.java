package testCases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.Payloads;
import pojo.Cart;
import routes.Routes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TC004_CartAPI extends BaseClass {

    //1)get all carts
    @Test
    public void testGetAllCarts()
    {
        given()
        .when()
                .get(Routes.GET_ALL_CART)
        .then()
                .statusCode(200)
                .log().body()
                .body("size()",greaterThan(0));

    }

    //2) Get specific cart by id
    @Test
    public void testGetSpecificCartById()
    {
        int id= configReader.getIntProperty("cartId");
        given()
                .pathParam("id",id)
        .when()
                .get(Routes.GET_SPECIFIC_CART_BY_ID)
        .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(id));
    }

    //3) Get a user cart
    @Test
    public void testGetAUserCart()
    {
        int userid=configReader.getIntProperty("userId");
        given()
                .pathParam("userid", userid)
        .when()
                .get(Routes.GET_CART_USER)
        .then()
                .statusCode(200)
                .body("userId", everyItem(equalTo(userid)))
                .log().body();
    }

    //4) limit return results
    @Test
    public void testGetCartResultsWithLimit()
    {
        int limit=configReader.getIntProperty("limit");
        given()
                .pathParam("limit", limit)
        .when()
                .get(Routes.GET_CART_WITH_LIMIT)
        .then()
                .statusCode(200)
                .log().body()
                .body("size()",lessThanOrEqualTo(limit));
    }

    //5) Get cart in ascending order
    @Test
    public void testGetCartInAscOrder()
    {
        List<Integer> cartId=given()
                .pathParam("order","asc")
        .when()
                .get(Routes.GET_CART_SORTED)
        .then()
                .statusCode(200)
                .log().body()
                .extract().response().jsonPath().getList("id",Integer.class);
        assertThat(isSortedAsceding(cartId),is(true));

    }

    //6) Get cart in descending order
    @Test
    public void testGetCartInDescOrder()
    {
        List<Integer> cartId=given()
                .pathParam("order","desc")
                .when()
                .get(Routes.GET_CART_SORTED)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response().jsonPath().getList("id",Integer.class);
        assertThat(isSortedDesceding(cartId),is(true));

    }

    //7)get carts in date range
    @Test
    public void getCartsInDateRange()
    {
        String startdate=configReader.getProperty("startdate");
        String enddate=configReader.getProperty("enddate");

        List<String> cartDates=given()
                .pathParam("startdate",startdate)
                .pathParam("enddate", enddate)
        .when()
                .get(Routes.GET_CART_IN_DATE_RANGE)
        .then()
                .statusCode(200)
                .log().body()
                .extract().response().jsonPath().getList("date");

        assertThat(validateCartDatesWithinRange(cartDates, startdate, enddate),is(true));

    }

    //8)get user carts in date range
    @Test
    public void testGetUserCartInDateRange()
    {
        String startdate=configReader.getProperty("startdate");
        String enddate=configReader.getProperty("enddate");
        int userId=configReader.getIntProperty("userId");
       List<String> cartDate= given()
                .pathParam("userId",userId)
                .pathParam("startdate", startdate)
                .pathParam("enddate", enddate)
        .when()
                .get(Routes.GET_CART_USER_IN_DATE_RANGE)
        .then()
                .statusCode(200)
                .log().body()
                .body("userId", everyItem(equalTo(userId)))
                .extract().response().jsonPath().getList("date");
       assertThat(validateCartDatesWithinRange(cartDate,startdate,enddate),is(true));
    }

    //9) Create new Cart
    @Test
    public void testCreateNewCart()
    {
        //create data to pass
        Cart cartdata= Payloads.cartPayload(2);
        given()
                .contentType("application/json")
                .body(cartdata)
        .when()
                .post(Routes.CREATE_CART)
        .then()
                .statusCode(200)
                .log().body()
                //.body("userId", equalTo(2))
                .body("id", notNullValue()) // Validate that the cart ID in response is not null
                //.body("userId", notNullValue()) // Validate that the user ID in response is not null
                .body("products.size()", greaterThan(0));
    }

    //10) update cart
    @Test
    public void testUpdateCart() {

        int userId = configReader.getIntProperty("userId");
        int cartId = configReader.getIntProperty("cartId");

        Cart updateCart=Payloads.cartPayload(userId); //userId passing
        given()
                .pathParam("id", cartId)
                .contentType("application/json")
                .body(updateCart)
                .when()
                .put(Routes.UPDATE_CART)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(cartId)) // Validate that the response contains the correct cart ID
                //.body("userId", notNullValue())
                .body("products.size()", equalTo(1));
    }

    //11)Delete Cart
    @Test
    public void testDeleteCart() {
        int cartId = configReader.getIntProperty("cartId");
        given()
                .pathParam("id", cartId)
                .when()
                .delete(Routes.DELETE_CART)
                .then()
                .statusCode(200); // Validate that the response status code is 204 (No Content)
    }
}
