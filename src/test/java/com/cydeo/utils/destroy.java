package com.cydeo.utils;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;


public class destroy {

    @AfterAll
    public static void destroy(){
      reset();
    }

}
