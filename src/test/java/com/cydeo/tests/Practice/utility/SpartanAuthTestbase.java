package com.cydeo.tests.Practice.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanAuthTestbase {

    @BeforeAll
    public static void init(){
        baseURI="http://54.172.62.193:7000";
        basePath="/api";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }
}
