package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.utils.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.reset;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;



public class ZipCodeApiJsonTest {

    /**
     * Given accept type is json
     * and country path param value is "us"
     * and postal code path param value is 22102
     * When I send get request to http://api.zippopotam.us/{country}/{postal-code}
     * Then status code is 200
     * Then "post code" is "22102"
     * And  "country" is "United States"
     * And "place name" is "Mc Lean"
     * And  "state" is "Virginia"
     */


    @BeforeAll
    public static void setUp() {
        System.out.println("Setting up base Url ... ");
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");

    }




//    @AfterAll
//    public static void destroy(){
//        reset();
//    }







    @DisplayName("GET us/zipCode - jsonPath")
    @Test
    public void zipCodeJsonPathTest() {
//        Response response = given().accept(ContentType.JSON)
//                .and().pathParam("country","us")
//                .and().pathParam("postal-code", "22102")
//                .when().get("/{country}/{postal-code}");
//        response.prettyPrint();

        Map<String,String> PathMap=new HashMap<>();
        PathMap.put("country","us");
        PathMap.put("postal-code", "22102");


        Response response= given()
                .accept(ContentType.JSON)
                .log().uri()
                .pathParams(PathMap)
                .when().get("{country}/{postal-code}").prettyPeek();



        assertEquals(200, response.statusCode());

        //assign response json payload/body to Jsonpath
        JsonPath jsonPath = response.jsonPath();
        verifyZipCode(jsonPath, "22102");

        //navigate the json and print/assert country value
        System.out.println("country name = " + jsonPath.getString("country"));
        assertEquals("United States" , jsonPath.getString("country"));

        //navigate the json and print/assert post code value
        System.out.println("post code = " + jsonPath.getString("'post code'"));
        String zipCode = jsonPath.getString("'post code'");
        assertEquals("22102", zipCode);

        System.out.println("Place name = " + jsonPath.getString("places[0]. 'place name'"));

        String placeName = jsonPath.getString("places[0]. 'place name'");
        assertEquals("Mc Lean", placeName );


        String state = jsonPath.getString("places[0].state");

        assertEquals("Virginia",state);
    }

    public void verifyZipCode(JsonPath jsonPath,String expZipCode){
        assertEquals(expZipCode, jsonPath.getString("'post code'"));
    }
}















