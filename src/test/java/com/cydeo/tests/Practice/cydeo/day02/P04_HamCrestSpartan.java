package com.cydeo.tests.Practice.cydeo.day02;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class P04_HamCrestSpartan extends SpartanTestBase {
    /**
     * - Send a request to GET /spartans/search
     * - Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> ea
     * - Log Everything
     * - Verify followings
     *       - Status Code is 200
     *       - ContentType is application/json
     *       - Total Element 2
     *       - jsonArray size hasSize 2
     *       - All Names hasItem "Meade1"
     *       - Every gender is Female
     */

    @Test
    public void searchSpartans() {

        given()
                .queryParam("nameContains", "ea")
                .queryParam("gender","Female")
                .log().uri().
        when()
                .get("/spartans/search")  .prettyPeek().
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("totalElement", is(1))
                .body("content", hasSize(1))
                .body("content.name",hasItem("Jeanelle"))
                .body("content.gender",everyItem(is("Female")));




    }
}
