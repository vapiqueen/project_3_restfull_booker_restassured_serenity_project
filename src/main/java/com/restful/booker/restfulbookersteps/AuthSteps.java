package com.restful.booker.restfulbookersteps;

import com.restful.booker.model.AuthPojo;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;

public class AuthSteps {
    public String auth() {
        AuthPojo authpojo = new AuthPojo();
        authpojo.setUsername("admin");
        authpojo.setPassword("password123");
        Response response = given().log().all()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .when()
                .body(authpojo)
                .post("/auth");
        response.then().log().all().statusCode(200);
        String token = response.jsonPath().getString("token");
        return token;
    }


}



