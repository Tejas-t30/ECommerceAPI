package testCases;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.Payloads;
import pojo.User;
import routes.Routes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class TC003_UserAPI extends BaseClass{
    //1) Get All Users
    @Test
    public void testGetAllUsers()
    {
        given()

        .when()
                .get(Routes.GET_ALL_USERS)
        .then()
                .statusCode(200)
                .contentType("application/json")
                .body("size()",greaterThan(0));
    }

    //2) Get User by specifing user id
    @Test
    public void testGetUserById()
    {
        int userid=configReader.getIntProperty("userId");
        int id=given()
                .pathParam("id",userid)
        .when()
                .get(Routes.GET_USER_BY_ID)
        .then()
                .statusCode(200)
                .contentType("application/json")
                .log().body()
                .extract().jsonPath().getInt("id");
        assertThat(id, equalTo(userid));
    }

    //3) get users with limit applied
    @Test
    public void testGetUsersWithLimit()
    {
        int limit=configReader.getIntProperty("limit");
        given()
                .pathParam("limit",limit)
        .when()
                .get(Routes.GET_USERS_WITH_LIMIT)
        .then()
                .statusCode(200)
                .body("size()",equalTo(limit))
                .log().body();
    }

    //4) get users in ascending order
    @Test
    public void testGetUsersInAscOrder()
    {
        Response response=given()
                .pathParam("order","asc")
        .when()
                .get(Routes.GET_USERS_SORTED)
        .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        List<Integer> userIds=response.jsonPath().getList("id",Integer.class);
        assertThat(isSortedAsceding(userIds),is(true));
    }

    //5)get users in descending order
    @Test
    public void testGetUsersInDscOrder()
    {
        Response response=given()
                .pathParam("order","desc")
                .when()
                .get(Routes.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        List<Integer> userIds=response.jsonPath().getList("id",Integer.class);
        assertThat(isSortedDesceding(userIds),is(true));
    }

    //6) create user
    @Test
    public void testCreateUser()
    {
        User newUser= Payloads.userPayload();
        int id=given()
                .contentType("application/json")
                .body(newUser)
        .when()
                .post(Routes.CREATE_USER)
        .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .extract().response().jsonPath().getInt("id");
        System.out.println("Newly Created user Id: "+id);
    }

    //7) update user
    @Test
    public void testUpdateUser()
    {
        int userId= configReader.getIntProperty("userId");
        User newUser=Payloads.userPayload();
        given()
                .contentType("application/json")
                .body(newUser)
                .pathParam("id",userId)
        .when()
                .put(Routes.UPDATE_USER)
        .then()
                .statusCode(200)
                .log().body()
                .body("username",equalTo(newUser.getUsername()));
    }

    //8) Delete user
    @Test
    public void testDeleteUser()
    {
        int userId= configReader.getIntProperty("userId");
        given()
                .pathParam("id",userId)
        .when()
                .delete(Routes.DELETE_USER)
        .then()
                .statusCode(200)
                .body("id",equalTo(userId));
    }
}
