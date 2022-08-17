package com.cydeo.tests.day05_path_jsonpath;

import com.cydeo.tests.day04_path_jsonpath.HRApiGetTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJsonPathTest extends HRApiGetTest {


    @DisplayName("GET/employees?limit=200 => jsonPath filters")
    @Test
    public void jsonPathEmployeesTest(){


        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("limit",200)
                .log().uri().
                when()
                .get("/employees").prettyPeek();

assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("items[0].first_name");
        String firstNameJobId = jsonPath.getString("items[0].job_id");

        System.out.println(firstName);
        System.out.println(firstNameJobId);


        List<String> list = jsonPath.getList("items.findAll {it.salary>5000}.first_name");
        System.out.println(list);

        List<String> AllEmails = jsonPath.getList("items.email");

        System.out.println(AllEmails);

        assertTrue(AllEmails.contains("TGATES"));



        List<String> namesAtDept90 = jsonPath.getList("items.findAll {it.department_id ==90}.first_name");
        System.out.println(namesAtDept90);
        System.out.println("namesAtDept90.size() = " + namesAtDept90.size());


//        //get all employee first names who work as IT_PROG
//        items.findAll{it.job_id=='IT_PROG'}.first_name

        List<String> itProgs = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");

        System.out.println(itProgs);




//        //get all employee first names whose salary is more than or equal 5000
//
//        items.findAll{it.salary >= 5000}.first_name


        List<String> salaryMore5k = jsonPath.getList("items.findAll{it.salary>=5000}.first_name");

        System.out.println(salaryMore5k);



////get emp first name who has max salary
//        items.max{it.salary}.first_name

       String maxSalary = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(maxSalary);

////get emp first name who has min salary
//
//        items.min{it.salary}.first_name

        String minSalary = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println(minSalary);



    }
}
