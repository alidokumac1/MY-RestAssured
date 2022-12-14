package com.cydeo.tests.Practice.cydeo.day01;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P04_MethodChaining extends SpartanTestBase {

    // CREATE a SpartanTestBase instead of keep typing BeforeAll and AfterAll

    @Test
   public void methodChaining() {
        String word="apple";
        System.out.println(word.toUpperCase().toLowerCase().substring(2).replace("e", "t"));

    }


    /**
     *1- Given accept type is Json
     *2- Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> e
     *3- When user sends GET request to /spartans/search
     *4- Print out Followings
     *     - Total Element Number
     *     - Get me first spartan name
     *     - Get me second spartan id
     *     - Get me last spartan name
     *     - Get me all Spartan Names

     * 5- Verify followings
     *     - Status code should be 200
     */
    @Test
    public void queryParam() {


        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");


        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .queryParams(queryMap).
                //.queryParam("nameContains","e")
                //.queryParam("gender", "Female").
        when()
                .get("/spartans/search").prettyPeek();

        //get me totalElement Number
        System.out.println(response.path("totalElement").toString());

        //get me first SpartanName
        System.out.println(response.path("content.name[0]").toString());

        System.out.println(response.path("content[0].name").toString());

        //get me second SpartanName
        System.out.println(response.path("content.name[1]").toString());

        System.out.println(response.path("content[1].name").toString());

       // Get me last spartan name
        System.out.println(response.path("content.name[-1]").toString());

        System.out.println(response.path("content[-1].name").toString());

        System.out.println("PRINT OUT LAST 4   ");

        System.out.println(response.path("content.name[-4]").toString());

        System.out.println(response.path("content[-4].name").toString());

        // Get me all Spartan Names
        List<String> allNames = response.path("content.name");
        System.out.println("allNames = " + allNames);


    }

    /**
     *1- Given accept type is Json
     *2- Path Parameters values are
     *     - id —> 5
     *3- When user sends GET request to /spartans/{id}
     *5- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - ID is 5
     *     - Name is "Blythe"
     *     - gender is "Female"
     *     - phone is 3677539542
     *
     */


    @Test
    public void pathParams() {

        Response response =
          given()
                .accept(ContentType.JSON)
                .pathParam("id", 5)
                .log().uri().
          when()
                .get("/spartans/{id}").prettyPeek();



        //status Code
        System.out.println(response.statusCode());

        //content Type json
        System.out.println(response.contentType());

        //ID is 5
        int id=response.path("id");
        Assertions.assertEquals(5, id);

        // names is Blythe
        String name=response.path("name");
        Assertions.assertEquals("Blythe", name);

        // gender is Female
        String gender =response.path("gender");
        Assertions.assertEquals("Female", gender);

        // phone number is 3677539542
        long phone=response.path("phone");
        Assertions.assertEquals(3677539542l, phone);




    }
}
