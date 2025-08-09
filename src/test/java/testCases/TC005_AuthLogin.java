package testCases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;
import payload.Payloads;
import pojo.AuthLogin;
import routes.Routes;

public class TC005_AuthLogin extends BaseClass {
    //with Valid credentials
    @Test
    public void testValidLoginCredentials()
    {
        String username=configReader.getProperty("username");
        String password=configReader.getProperty("password");
        AuthLogin login =new AuthLogin(username, password);

        given()
                .contentType("application/json")
                .body(login)
        .when()
                .post(Routes.LOGIN_AUTH)
        .then()
                .statusCode(201)
                .body("token", notNullValue())
                .log().body();
    }

    @Test
    public void testInvalidLoginCredentials()
    {
        AuthLogin login= Payloads.authLoginPayload();

        given()
                .contentType("application/json")
                .body(login)
        .when()
                .post(Routes.LOGIN_AUTH)
        .then()
                .statusCode(401)
                .body(equalTo("username or password is incorrect"))
                .log().body();
    }
}
