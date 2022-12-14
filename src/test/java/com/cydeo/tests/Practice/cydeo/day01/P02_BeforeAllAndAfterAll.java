package com.cydeo.tests.Practice.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P02_BeforeAllAndAfterAll {


    @BeforeAll
    public static void init(){
    baseURI="http://54.172.62.193:1000";
    basePath="/ords/hr";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

    /**
     * 1. Send request to HR url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *     - Headers
     *     - Content-Type
     *     - Status Code
     *     - Response
     *     - Date
     *     - Verify response body has "Europe"
     *     - Verify response has Date
     */
    @DisplayName("GET /regions")
    @Test
    public void simpleGetRequest() {

        Response response = RestAssured.get("/regions");

        response.prettyPrint();

        System.out.println("==================");
        System.out.println(response.headers());


        System.out.println("==================");
        System.out.println(response.contentType());

        System.out.println("==================");
        System.out.println(response.statusCode());

        System.out.println("==================");
        System.out.println(response.header("Date"));

        System.out.println("==================");
        System.out.println(response.asString().contains("Europe"));


        // Verify Europe
        assertTrue(response.asString().contains("Europe"));

        // Verify Date is exist

        System.out.println(response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));


    }

    /**
     * 1. Send request to HR url and save the response
     * 2. GET /employees/100
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *     - First Name
     *     - Last Name
     *     - Verify status code is 200
     *     - Verify First Name is "Steven"
     *     - Verify content-Type is application/json
     */

    @DisplayName("GET /employees/100")
    @Test
    public void getOneEmployees() {


        Response response = RestAssured.get("/employees/100").prettyPeek();


        System.out.println(response.path("first_name").toString());
        System.out.println(response.path("last_name").toString());

        Assertions.assertEquals(200, response.statusCode()  );

        Assertions.assertEquals("Steven", response.path("first_name"));

        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());


    }

}
