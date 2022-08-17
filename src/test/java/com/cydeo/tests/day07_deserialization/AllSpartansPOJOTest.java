package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanTestBase;
import groovy.transform.stc.POJO;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.HTML;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllSpartansPOJOTest extends SpartanTestBase {

    /**
     * Given accpet type is json
     * when I send GET request to/ spartans
     * Then status code is 200
     * And contect type is json
     * And I can convert json to list of spartan pojo
     */


    @Test
    public void allSpartansToPojoTest() {

        Response response = given().log().all().accept(ContentType.JSON)
                .when().get("/spartans").prettyPeek();

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        assertEquals("application/json",response.contentType());

        //convert response to jsonpath

        JsonPath jsonPath = response.jsonPath();

        //using jasonPath extract List of spartans/ do deserialization

        List<Spartan> allSpartans = jsonPath.getList("",Spartan.class);

        System.out.println(allSpartans.size());

        System.out.println("allSpartans.get(0) = " + allSpartans.get(0));

        //using streams : Filter and store female spartans into a different list

        List<Spartan> femaleSpartans = allSpartans.stream().filter(spartan -> spartan.getGender().equals("Female")).collect(Collectors.toList());

        System.out.println("femaleSpartans = " + femaleSpartans);
        System.out.println("femaleSpartans.size() = " + femaleSpartans.size());


        //using streams : Filter and store male spartans into a different list
        //source.stream.filter(condition in lambda).collect(type)
        long count =allSpartans.stream().filter(sp -> sp.getGender().equals("Male")).count();
        System.out.println("male spartan count = " + count);
        List<Spartan> maleSpartan = allSpartans.stream().filter(sp -> sp.getGender().equals("Male"))
                .collect(Collectors.toList());

        System.out.println(maleSpartan);

        List<Spartan> maleSpartanID50And60 = allSpartans.stream().filter(sp -> sp.getId()>50 && sp.getId()< 60).collect(Collectors.toList());

        System.out.println(maleSpartanID50And60);
    }
}
