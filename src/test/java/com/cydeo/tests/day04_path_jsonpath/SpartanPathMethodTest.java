package com.cydeo.tests.day04_path_jsonpath;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.destroy;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpartanPathMethodTest extends SpartanTestBase { //Inheritance extends


    @DisplayName("GET/spartan/{id} and path()")
    @Test
    public void readSpartanJsonUsingPathTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "13")
                .when().get("/spartans/{id}");

        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals("application/json",response.contentType());

        int spartanId = response.path("id");
        assertEquals(13,spartanId);

        assertEquals("Jaimie",response.path("name"));
        assertEquals("Female",response.path("gender"));
        assertEquals(7842554879L,(long)response.path("phone"));
      //  assertEquals(7842554879L,Long.valueOf(""+response.path("phone")));


        }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */


@DisplayName("GET/spartans and path()")
    @Test
    public void readSpartanJsonArrayUsingPathTest(){

    Response response = given().accept(ContentType.JSON)
            .when().get("/spartans");

    assertEquals(200,response.statusCode());

    assertEquals("application/json", response.contentType());

    System.out.println("First spartan id = " + response.path("id[0]"));//response.path("[0].id")
    System.out.println("First person name  = " + response.path("name[0]"));

    System.out.println("last spartan id = " + response.path("id[-1]"));
    System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));


    // get all ids into a list

    List<Integer> allIds = response.path("id");
    System.out.println("allIds size =" + allIds.size());
    System.out.println("allIds = " + allIds);

   assertTrue(allIds.contains(22) && allIds.size() == 101);


   //get all names and say hi

    List<String> names = response.path("name");
    names.forEach(name -> System.out.println("Hi " + name));

    allIds.forEach(Id -> System.out.println("Hello" + Id));

    for (String name :names) {
        System.out.println("Bye " + name);

        destroy.destroy();

    }




        }
    }






