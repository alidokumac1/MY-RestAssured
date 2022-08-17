package com.cydeo.tests.day08_hamcrest;

import com.cydeo.tests.day04_path_jsonpath.HRApiGetTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ORDSHamcrestTest extends HRApiGetTest {
    /**
     *
     */


    @DisplayName("GET/ countries -> hamcrest assertions")
    @Test
    public void countriesTest(){
      String countryId=  given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("count",is(25)
                ,"items.country_id",hasItems("AR","AU","BE","BR","CA"),
                "items.country_name",hasItems("Argentina","Canada","Australia","Belgium","Brazil"),
                "items[0].country_id", is(equalTo("AR")))
                .and().extract().body().path("items[0].country_id");
      //                .log().all();

        System.out.println("countryId = " + countryId);




        given().accept(ContentType.JSON)
                .and().pathParam("country_id",countryId)
                .when().get("/countries/{country_id}")
                .then().assertThat().statusCode(200)
                .and().body("country_name",equalTo("Argentina")
                , "country_id",equalTo("AR"),
                "region_id",equalTo(2))
                .and().extract().jsonPath();
    }
}
