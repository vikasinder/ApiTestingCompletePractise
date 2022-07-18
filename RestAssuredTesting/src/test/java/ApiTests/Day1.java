package ApiTests;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.payloads;
public class Day1 {

public static void main(String[] args) {

	//Given : All Input Details
			//When : Submit The API / Resource
			//Then : Validate the Response
			
			// Given - Query Parameters + Header value( JSON / XML ) + Body ( Payload )
			RestAssured.baseURI="https://rahulshettyacademy.com";
			
			given().log().all().queryParam("Key", "qaclick123").header("Content-type","application/json")
			.body(payloads.AddPlace()).when().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)");
		}

}
