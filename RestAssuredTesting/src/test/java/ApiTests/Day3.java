package ApiTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import Files.ResuableMethods;
import Files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Day3 {

	// ( POST REQUEST ) Add a new place ****
		// Then From Response You get , extract the value of place_id , so that it can be used when you want to do a PUT request
		//	( PUT REQUEST ) then update the place with the place_id you received in response when you added a new place
		//( GET REQUEST )Then Get The Place back again to see whether it has been successfully updated or Not
		
		public static void main(String[] args) {

			///////////// THIS IS A POST REQUEST  ////////////////////
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
		
					
					//////////////////////////////////////////////////////
					
					///////////// THIS IS A PUT REQUEST  ////////////////////
					
					String newUpdatedAddress="95 Charlaois Blvd Apartment 410";
					
					given().log().all().queryParam("key", "qaclick123").header("Content-type","application/json").body("{\r\n"+ "\"place_id\":\""+ place +"\",\r\n"
							+ "    \"address\":\""+newUpdatedAddress+"\",\r\n"
							+ "    \"key\":\"qaclick123\"\r\n"
							+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
					
					//////////////////////////////////////////////////////
					
					///////////// THIS IS A GET REQUEST  ////////////////////
					
					// NO HEADER IS THERE- BEACAUSE YOU ARE SENDING GET REQUEST - SO NO CONTENT BODY IS THERE
					
					String getAddressDetails=	given().log().all().queryParam("key", "qaclick123").queryParam("place_id", ""+place+"")
					.when().get("maps/api/place/get/json")
					.then().log().all().statusCode(200).extract().response().asString();
				
//					JsonPath json1=  ResuableMethods.rawToJson(getAddressDetails);
//					String newRetrievedActualAddress = json1.getString("address");
//					
					////////////////OR this can be straight away used as below code //////////////////
					
					String newRetrievedActualAddress= ResuableMethods.rawToJson(getAddressDetails).getString("address");
					System.out.println(newRetrievedActualAddress);
					Assert.assertEquals(newUpdatedAddress, newRetrievedActualAddress);
}
}	