package Serialization;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import Files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import okhttp3.Response;


public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// AS THE END POINT IS SAME FOR ALL THE REQUEST , SO YOU CAN GIVE IT LIKE THIS
		// RestAssured.baseURI="http://localhost:8080/";
		// OTHERWISE GIVE IT SEPREATELY LIKE GIVEN BELOW 
		// when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().as(GetCourses.class);
			
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		addNewPlace place=new addNewPlace();
		
		place.setAccuracy(50);
		place.setAddress("95 charolais blvd Brampton");
		place.setLanguage("French");
		place.setWebsite("www.astrovikas.com");
		place.setPhone_number("647 832 2008");
		place.setName("vikas sharma");
		
		// AS SETTYPE EXPECT LIST OF VALUES SO WE HAVE TO USE ARRAYLIST FOR THAT
		
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("pink");
		place.setTypes(list);
		
		
		// LONLATTITDE CLASS OBJECT IS PASSED SO WE HAVE TO DO IT LIKE THIS
		// FOR PASSING SUB JSON - YOU HAVE TO CREATE AN OBJECT OF SUB JSON FILE AND PASS IT TO MAIN JSON FILE
		// PARENT JSON
		lonLatitude lonlat=new lonLatitude();
		lonlat.setLat(23.000);
		lonlat.setLng(345.09);
		place.setLocation(lonlat);
		
		
		System.out.println(place.getAddress());
		
	
		// Given - Query Parameters + Header value( JSON / XML ) + Body ( Payload )
		
		
		
		io.restassured.response.Response res =	given().log().all().queryParam("key", "qaclick123").header("Content-type","application/json")
		.body(place)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("status", equalTo("OK")).header("server","Apache/2.4.41 (Ubuntu)").extract().response();
	
		String validResponse=res.asString();
		
		
		System.out.println("Result Of Response Body :"+validResponse);

	}

}
