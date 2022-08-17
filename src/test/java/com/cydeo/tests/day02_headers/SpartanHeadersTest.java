package com.cydeo.tests.day02_headers;


import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 • When I send a GET request to
 • spartan_base_url/api/spartans
 • Then Response STATUS CODE must be 200
 • And I Should see all Spartans data in JSON format
 AND I should see all spartans data in xml format
 */

public class SpartanHeadersTest {

    String url = "http://54.172.62.193:8000/api/spartans";


    @DisplayName("Get/api/spartans and expect Json response")
    @Test
    public void getAllSpartanHeaderTest(){

        when().get(url).then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
//                and().contentType("application/json");



    }

    @DisplayName("Get / api/spartans with req header")
    @Test
    public void acceptTypeHeaderTest(){
        given().accept(ContentType.XML)
                .when().get(url).then().assertThat().statusCode(200).and().contentType(ContentType.XML);


    }

    @Test
    public void readResponseHeaders(){

     Response response =  given().accept(ContentType.XML)
                .when().get(url);


        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));
        System.out.println("response.getHeader(\"Content-type\") = " + response.getHeader("Content-type"));
        System.out.println("response.getHeader(\"Connection\") = " + response.getHeader("Connection"));
        System.out.println();
       Headers headers= response.getHeaders();

        System.out.println("headers = " + headers);

        //ensure header Keep-Alive is present

        assertTrue(response.getHeader("Keep-Alive") != null);



    }



}
