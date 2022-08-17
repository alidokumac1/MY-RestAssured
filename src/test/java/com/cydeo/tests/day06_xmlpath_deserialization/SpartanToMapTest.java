package com.cydeo.tests.day06_xmlpath_deserialization;
import  com.cydeo.utils.SpartanTestBase;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanToMapTest extends SpartanTestBase{

    /**
     * Given accept type is application/json
     * And Path param id = 10
     * When i send GET request to /api/spartans
     * Then status code is 200
     * And content type is json
     * And spartan data matching:
     * id > 10
     * name>Lorenza
     * gender >Female
     * phone >3312820936
     */

    @Test
    public void spartanToMapTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "10")
                .when().get("/spartans/{id}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json", response.contentType());

        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println("spartanMap = " + spartanMap);
        System.out.println("Keys = " + spartanMap.keySet());

        assertEquals(10 , spartanMap.get("id"));
        assertEquals("Lorenza", spartanMap.get("name"));
        assertEquals("Female", spartanMap.get("gender"));
        assertEquals(3312820936l, spartanMap.get("phone"));


    }
}

