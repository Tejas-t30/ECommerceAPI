package testCases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.Payloads;
import pojo.Products;
import routes.Routes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TC001_ProductAPI extends BaseClass{

    //1) Test to retrieve all products
    @Test
    public void testGetAllProducts()
    {
        given()

        .when()
                .get(Routes.GET_ALL_PRODUCTS)
        .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .log().body();
    }

    //2) Test to retrieve a single product by ID
    @Test
    public void testGetSingleProductById()
    {
        int productId=configReader.getIntProperty("productId");
        given()
                .pathParams("id",productId)
        .when()
                .get(Routes.GET_PRODUCT_BY_ID)
        .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("id",equalTo(productId))
                .log().body();
    }

    //3) Test to retrive a limited number of products
    @Test
    public void testGetProductsWithLimit()
    {
        int limit=configReader.getIntProperty("limit");
        given()
                .pathParams("limit", limit)
        .when()
                .get(Routes.GET_PRODUCTS_WITH_LIMIT)
        .then()
                .statusCode(200)
                .body("size()",equalTo(limit))
                .log().body();
    }

    //4) Test to retreive products sorted in descending order
    @Test
    public void testGetProductsInDesOrder()
    {
        Response response=given()
                .pathParams("order","desc")
        .when()
                .get(Routes.GET_PRODUCTS_SORTED)
        .then()
                .statusCode(200)
                .extract().response();

        List<Integer> productIds=response.jsonPath().getList("id",Integer.class);
        assertThat(isSortedDesceding(productIds),is(true));
    }

    //5) Test to retreive products sorted in Ascending order
    @Test
    public void testGetProductsInAscOrder()
    {
        Response response=given()
                .pathParams("order","asc")
        .when()
                .get(Routes.GET_PRODUCTS_SORTED)
        .then()
                .statusCode(200)
                .extract().response();
        List<Integer> productIds=response.jsonPath().getList("id",Integer.class);
        assertThat(isSortedAsceding(productIds),is(true));
    }

    //6) Test to get all product categories
    @Test
    public void testGetAllCategories()
    {
        given()

        .when()
                .get(Routes.GET_ALL_CATEGORIES)
        .then()
                .statusCode(200)
                .body("size()",greaterThan(0));

    }


    //7) Test to get products by category

    @Test
    public void testGetProductsByCategory()
    {
        String category=configReader.getProperty("category");
        given()
                .pathParam("category", category)
        .when()
                .get(Routes.GET_PRODUCTS_BY_CATEGORY)
        .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("category", everyItem(notNullValue()))
                .body("category", everyItem(equalTo(category)))
                .log().body();

    }

    //8) Test to add a new product
    @Test
    public void testAddNewProduct()
    {
        Products newProduct= Payloads.productPayload();
       int productId= given()
                .contentType("application/json")
                .body(newProduct)
        .when()
                .post(Routes.CREATE_PRODUCT)
        .then()
                .statusCode(200)
                .body("id",notNullValue())
                .body("title", equalTo(newProduct.getTitle()))
               .log().body()
                .extract().response().jsonPath().getInt("id");
        System.out.println("Newly Created Product Id: "+productId);

    }

    //9) Test to update an existing product
    @Test
    public void testUpdateProduct()
    {
        int productId=configReader.getIntProperty("productId");

        Products updatedPayload=Payloads.productPayload();

        given()
                .contentType("application/json")
                .body(updatedPayload)
                .pathParam("id", productId)

                .when()
                .put(Routes.UPDATE_PRODUCT)
                .then()
                .log().body()
                .statusCode(200)
                .body("title", equalTo(updatedPayload.getTitle()));

    }

    //10) test to delete a product
    @Test
    public void testDeleteProduct()
    {
        int productId=configReader.getIntProperty("productId");

        given()
                .pathParam("id",productId)
                .when()
                .delete(Routes.DELETE_PRODUCT)
                .then()
                .statusCode(200);
    }

}
