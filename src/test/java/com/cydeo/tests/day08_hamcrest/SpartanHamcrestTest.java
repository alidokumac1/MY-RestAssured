package com.cydeo.tests.day08_hamcrest;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("GET/ spartans/{id} -> hamcrest assertions")
    @Test
    public void singleSpartanTest() {

        given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get("/spartans/{id}")
                .then().statusCode(is(200))
                .and().contentType("application/json")
                .and().assertThat().body("id", is(24),
                        "name", equalTo("Julio"),
                        "gender", is("Male"),
                        "phone", is(9393139934L));


//        given().accept(ContentType.JSON)
//                .log().all()
//                .pathParam("id" , "24")
//                .when().get("/spartans/{id}")
//                .then().assertThat()
//                .statusCode(HttpStatus.SC_OK)
//                .and().contentType(ContentType.JSON)
//                .and().body("name" , is("Julio"))
//                .and().body("gender" , is("Male"))
//                .and().body("phone" , hasToString("9393139934"));


    }

    /**
     * Given accept type is json
     * And query param nameContains value is "e"
     * And query param gender value is "Female"
     * When I send get request to /spartans/search
     * Then status code is 200
     * And content type is Json
     * And json response data is as expected
     * totalElement is 34
     * has ids: 94, 98,91, 81
     * has names: Jocelin, Georgianne, Catie, Marylee,Elita
     * every gender is Female
     * every name contains e
     */
    @DisplayName("Get/ spartans/search - > hamcrest assertion")
    @Test
    public void searchTest() {

        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then().assertThat().statusCode(200)
                .and().contentType((ContentType.JSON))
                .and().header("Date", containsString("2022"))
                .and().header("Date", containsString(LocalDate.now().getYear() + ""))
                .and().body("totalElement", is(equalTo(34)),
                "content.id",hasItems(94,98,91,81) ,
                "content.name", hasItems("Blythe", "Florrie", "Lorenza","Correy"),
                "content.gender",everyItem(is("Female")),
                "content.name",everyItem((containsStringIgnoringCase("e") ))).log().all();

    }


}
