package com.cydeo.tests.Practice.cydeo.day02;

import com.cydeo.tests.day04_path_jsonpath.HRApiGetTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P02_ResponsePath extends HRApiGetTest {
     /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                region_name is Americas
                region_id is 2
                print out all the links

     */

    @Test
    public void getOneRegion() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 2)
                .log().uri().
                when()
                .get("/regions/{id}").prettyPeek();


        List<Map<String,String>> allLinks = response.path("links" );

        for (Map<String, String> eachLink : allLinks) {
            System.out.println("=================");
            System.out.println(eachLink.get("rel"));
            System.out.println(eachLink.get("href"));
            System.out.println("=================");

        }





    }
}
