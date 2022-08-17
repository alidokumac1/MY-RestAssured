package com.cydeo.tests.day04_path_jsonpath;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HRApiPathMethodTest extends HRApiGetTest{

    @Test
    public void readCountriesUsingPathTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("countries");
        assertEquals(HttpStatus.SC_OK, response.statusCode());


        System.out.println("First country id = " + response.path("items[0].country_id"));
        System.out.println("First country name = " + response.path("items[0].country_name"));

        assertEquals("AR",response.path("items[0].country_id"));
        assertEquals("Argentina",response.path("items[0].country_name"));

        //store all country names into a list

        List<String> countries = response.path("items.country_name");
        System.out.println("countries = " + countries);


    }

}
