package SpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import Files.ResuableMethods;
import Files.payloads;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTestPractise {
	
	/////************** REQUEST REPONSE SPEC BUILDERS *****************////////////
	
	// USED TO AVOID WRITING REPETATIVE CODE AGAIN AND AGAIN IN REQUESTS WE MADE
	// GENERALIZING DETAILS THAT ARE COMMON FOR ALL THE TESTS
	// AT RUN TIME IT READS FROM SEPC BUILDER CLASS
	// CODE THAT IS UNIQUES FOR A PARTICULAR TEST CASE IS NOT INCLUDED IN THAT
	
	// BUILD - REQUEST SPEC BUILDER    .build() is included at end
	// BUILD - RESPONSE SPEC BUILDER   .build() is included at end
	
	public static void main(String[] args) {
		
		// OBJECT OF REQUESTSPECIFICATION CLASS IS MADE
		// USED WHEN SENDING JSON
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("Key", "qaclick123")
		.setContentType(ContentType.JSON)
		.build(); // This is there for both RequestSpecBuilder & ResponseSpecBilder
		
//		// USED WHEN RECIEVING JSON
		ResponseSpecification responseBuild= new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build(); // This is there for both RequestSpecBuilder & ResponseSpecBilder
				
		
		
		///////////// THIS IS A POST REQUEST  ////////////////////
		//Given : All Input Details
				//When : Submit The API / Resource
				//Then : Validate the Response
				
				// Given - Query Parameters + Header value( JSON / XML ) + Body ( Payload )
		
		// check instead of writing complete , we are using spec builder class
		
		// BREAKING REQUEST BODY AND WHEN THE REPONSE COMES FROM EACH OTHER IN DIFFRENT WAY 
		
				RequestSpecification   res=	given().log().all().spec(req)
				.body(payloads.AddPlace());
		
				Response response= res.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().spec(responseBuild).body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)").extract().response();
	
				//This class converts string to Json object so that we can extract particular value from the string that we
				// Got in response string variable
	
				String actualResponse= response.asString();
				
				JsonPath json=new JsonPath(actualResponse);
				String place= json.getString("place_id");
				
				System.out.println(place);
	
				
				//////////////////////////////////////////////////////
				
				///////////// THIS IS A PUT REQUEST  ////////////////////
				
				String newUpdatedAddress="95 Charlaois Blvd Apartment 410";
				
				RequestSpecification req1= given().log().all().spec(req).body("{\r\n"+ "\"place_id\":\""+ place +"\",\r\n"
						+ "    \"address\":\""+newUpdatedAddress+"\",\r\n"
						+ "    \"key\":\"qaclick123\"\r\n"
						+ "}");
						
				Response valueRecieved=req1.when().put("maps/api/place/update/json").then().log().all().assertThat().spec(responseBuild).body("msg", equalTo("Address successfully updated")).extract().response();
	
				
			    String actualResponse1= valueRecieved.asString();
				
				JsonPath json2=new JsonPath(actualResponse1);
				String place1= json2.getString("msg");
				
				System.out.println(place1);
	
				////////////////////c//////////////////////////////////
				
				///////////// THIS IS A GET REQUEST  ////////////////////
				
				// NO HEADER IS THERE- BEACAUSE YOU ARE SENDING GET REQUEST - SO NO CONTENT BODY IS THERE
	
				RestAssured.baseURI="https://rahulshettyacademy.com";

				 RequestSpecification getRequestDetails= given().log().all().spec(req).queryParam("key", "qaclick123").queryParam("place_id", ""+place+"");
					Response validResponse=	getRequestDetails.when().get("maps/api/place/get/json")
						.then().log().all().spec(responseBuild).extract().response();
					
					    String stringConvert=validResponse.asString();    
					
//						JsonPath json1=  ResuableMethods.rawToJson(getAddressDetails);
//						String newRetrievedActualAddress = json1.getString("address");
//						
						////////////////OR this can be straight away used as below code //////////////////
						
						String newRetrievedActualAddress= ResuableMethods.rawToJson(stringConvert).getString("address");
						System.out.println(newRetrievedActualAddress);
						Assert.assertEquals(newUpdatedAddress, newRetrievedActualAddress);

		
	}
		
}


