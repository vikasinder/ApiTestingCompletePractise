package ApiTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Day2 {

	// ( POST REQUEST ) Add a new place ****( PUT REQUEST ) then update the place with the place_id you received in response when you added a new place
	//( GET REQUEST )Then Get The Place back again to see whether it has been successfully updated or Not
	
	public static void main(String[] args) {

		//Given : All Input Details
				//When : Submit The API / Resource
				//Then : Validate the Response
				
				// Given - Query Parameters + Header value( JSON / XML ) + Body ( Payload )
				RestAssured.baseURI="https://rahulshettyacademy.com";
				String response=	given().log().all().queryParam("Key", "qaclick123").header("Content-type","application/json")
				.body(payloads.AddPlace()).when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
				//This class converts string to Json object so that we can extract particular value from the string that we
				// Got in response string variable
				
				
				JsonPath json=new JsonPath(response);
				String place= json.getString("place_id");
				
				System.out.println(place);
				
				
	
	
	}


}
