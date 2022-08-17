package com.cydeo.tests.Practice.cydeo.day05;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P04_JsonSchemaValidation extends SpartanTestBase {
    // Send request to GET /spartans
    // and verify the response json match the schema document
    // under resources folder
    // with the name of AllSpartansSchema.json


    @Test
    public void allSpartanSchemaValidaton() {


        given().log().uri().
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AllSpartanSchema.json"));
    }





}

