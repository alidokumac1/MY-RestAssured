package com.cydeo.tests.Practice.cydeo.day03;

import com.cydeo.tests.day04_path_jsonpath.HRApiGetTest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class P03_DeserilizationWithMap extends HRApiGetTest {

    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */



    @Test
    public void getAllLocations() {

        JsonPath jp = given().log().uri().
                when().get("/locations").jsonPath();

        // items[0]
        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String, Object> firstRowMap = jp.getMap("items[0]");
        System.out.println(firstRowMap);

        System.out.println("====== GET FIRST LOCATION FIRST LINK  ======");
        Map<String, Object> firstLinks = jp.getMap("items[0].links[0]");
        System.out.println(firstLinks.get("rel"));
        System.out.println(firstLinks.get("href"));


        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String,Object>> allLocations = jp.getList("items");

        System.out.println("====== FIRST LOCATION ======");
        System.out.println(allLocations.get(0));


        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println(allLocations.get(0).get("location_id"));

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println(allLocations.get(0).get("country_id"));


        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println(allLocations.get(0).get("links"));

        List<Map<String,Object>>  links = (List<Map<String, Object>>) allLocations.get(0).get("links");
        System.out.println(links.get(0).get("rel"));


        System.out.println("====== LAST LOCATION ID ======");
        System.out.println(allLocations.get(allLocations.size() - 1).get("location_id"));


    }
}
