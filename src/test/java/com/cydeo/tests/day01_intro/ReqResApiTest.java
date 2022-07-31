package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReqResApiTest {

    /**
     * BDD
     * When User sends GET Request to
     * https://reqres.in/api/users
     * <p>
     * Then Response status code should be 200
     * And Response body should contain "George"
     * And Header Content type should be json
     */

    String url = "https://reqres.in/api/users";

    @DisplayName("Get all users")
    @Test
    public void usersGetTest() {
        Response response = when().get(url);
        System.out.println("response.statusCode() = " + response.statusCode());

        assertEquals(200, response.statusCode());

        //BDD syntax
        response.then().statusCode(200);
        response.then().assertThat().statusCode(200);


        assertTrue(response.asString().contains("George"));

        response.then().assertThat().contentType(ContentType.JSON);






//
//        response.prettyPrint();
//        response.then().assertThat().statusCode(200);
//        response.then().assertThat().contentType(ContentType.JSON);
//        response.then().assertThat().body("data.first_name" is"George"));

    }


    String url1 = "https://reqres.in/api/users/5";

    @Test
    public void getSingleApiTest(){
        /**
         * When User Sends get request to API Endpoint:
         *     "https://reqres.in/api/users/5"
         * Then Response status code should be 200
         * And Response body should contain user info "Charles"
         *
         * @Test
         * public void getSingleUserApiTest() {
         *
         * }

         */

        //Response response = when().get(url1);
        Response response = when().get(url + "/5");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        assertTrue(response.asString().contains("Charles"));
        response.then().assertThat().contentType(ContentType.JSON);

    }
}
