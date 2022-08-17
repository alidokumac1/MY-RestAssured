package com.cydeo.tests.day06_xmlpath_deserialization;



import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Given accept type is application/xml
 * When I send GET request to /api/spartans
 * Then status code is 200
 * And content type is XML
 * And spartan at index 0 is matching:
 * id > 107
 * name>Ezio Auditore
 * gender > Male
 * phone > 7224120202
 */


public class SpartanXmlPathTest extends SpartanTestBase {

    @DisplayName("GET / spartans -> xml path")
    @Test
    public void spartanXMLPathTest() {
        Response response = given().accept(ContentType.XML)
                .when().get("/spartans");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/xml", response.contentType());

        XmlPath xmlPath = response.xmlPath();

        System.out.println("id = " + xmlPath.getInt("List.item[0].id"));

        String name = xmlPath.getString("List.item[0].name");
        System.out.println(name);

        String gender = xmlPath.getString("List.item[0].gender");
        System.out.println(gender);

        long phone = xmlPath.getLong("List.item[0].phone");
        System.out.println(phone);

        List<String> allNames = xmlPath.getList("List.item.name");

        System.out.println(allNames.size());
        System.out.println("allNames = " + allNames);

        assertEquals(105, allNames.size());

        Assertions.assertTrue(allNames.contains("Ali"));


    }

    @Test
    public void spartanToMapTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "13")
                .when().get("/spartans/{id}");
    }
}