package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanSearchPOJOTest extends SpartanTestBase {

    @Test
    public void spartanSearchToPOJOTest() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");


        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");

        response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //deserialize json to SpartanSearch pojo class
        SpartanSearch spartanSearch = response.body().as(SpartanSearch.class);

        System.out.println("spartanSearch.getTotalElement() = " + spartanSearch.getTotalElement());
        System.out.println("spartanSearch.getContent() = " + spartanSearch.getContent());
        System.out.println("spartanSearch.getContent().get(0) = " + spartanSearch.getContent().get(0));

        Spartan secondSpartan = spartanSearch.getContent().get(1);
        System.out.println("secondSpartan = " + secondSpartan);
        System.out.println("secondSpartan.getName() = " + secondSpartan.getName());

        List<Spartan> spartanData = spartanSearch.getContent();
        System.out.println("spartanData.get(1) = " + spartanData.get(1));

        List<String> names = new ArrayList<>();
        for (Spartan sp : spartanData){
            names.add(sp.getName());
        }

        System.out.println(names);

        System.out.println("names.get(0) = " + names.get(0));

        //using functional programing.stream

        List<String> allNames = spartanData.stream().map(sp -> sp.getName()).collect(Collectors.toList());

        System.out.println(allNames);


    }
}

