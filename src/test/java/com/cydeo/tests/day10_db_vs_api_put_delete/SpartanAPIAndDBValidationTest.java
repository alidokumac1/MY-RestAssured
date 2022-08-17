package com.cydeo.tests.day10_db_vs_api_put_delete;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DBUtils;
import com.cydeo.utils.SpartanTestBase;
import com.cydeo.utils.destroy;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;



import java.util.HashMap;
import java.util.Map;

public class SpartanAPIAndDBValidationTest extends SpartanTestBase {


    @DisplayName("POST/api/spartan -> then validate in DB")
    @Test
    public void postNewSpartanThenValidateInDBTest(){

        Map<String, Object> postRequestMap = new HashMap<>();
        postRequestMap.put("gender","Male");
        postRequestMap.put("name","PostVSDatabase");
        postRequestMap.put("phone","12345678910");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                .when().post("/spartans");

        response.prettyPrint();

        assertThat(response.statusCode(),equalTo(201));

        assertThat(response.contentType(),equalTo("application/json"));

        JsonPath jsonPath = response.jsonPath();
      //  assertThat(response.jsonPath().getString("success"),equalTo("A Spartan is Born!"));
      //  assertThat(response.path("success"),equalTo("A Spartan is Born!"));
        assertThat(jsonPath.getString("success"),equalTo("A Spartan is Born!"));


        int newSpartanID = jsonPath.getInt("data.id");
        System.out.println("newSpartanID = " + newSpartanID);

        String query = "SELECT name, gender, phone FROM spartans WHERE spartan_id =" +newSpartanID;

        String dbUrl = ConfigurationReader.getProperty("spartan.db.url");
        String dbUser =ConfigurationReader.getProperty("spartan.db.userName");
        String dbPwd = ConfigurationReader.getProperty("spartan.db.password");

        DBUtils.createConnection(dbUrl,dbUser,dbPwd);
        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println("dbMap = " + dbMap);
        //assert/validate data from database Matches data from post request

        assertThat(dbMap.get("NAME"),equalTo(postRequestMap.get("name")));
        assertThat(dbMap.get("GENDER"),equalTo(postRequestMap.get("gender")));
        assertThat(dbMap.get("PHONE"),equalTo(postRequestMap.get("phone")));


        DBUtils.destroy();
    }
}
