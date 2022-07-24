package JiraApiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import Deserialization.GetCourses;
import Files.ResuableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.cookie.Cookie;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraCookiesPractise {

	@Test
	public void createIssue()
	{
		
		// AS THE END POINT IS SAME FOR ALL THE REQUEST , SO YOU CAN GIVE IT LIKE THIS
		// RestAssured.baseURI="http://localhost:8080/";
		// OTHERWISE GIVE IT SEPREATELY LIKE GIVEN BELOW 
		// when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().as(GetCourses.class);
	
		RestAssured.baseURI="http://localhost:8080/";
	
	// CREATING SESSION BEFORE YOU CAN DO ANYTHING WITH JIRA API

	SessionFilter session=new SessionFilter();
	
	String response=given().log().all().header("Content-type","application/json").
	body("{\"username\":\"vikasinder72\",\"password\":\"Inder@1057\"}").filter(session).
	when().post("rest/auth/1/session").then().log().all().assertThat().statusCode(200).body("session.name", equalTo("JSESSIONID")).extract().response().asString();
	
	String newRetrievedsessionid= ResuableMethods.rawToJson(response).getString("session.value");
	System.out.println(newRetrievedsessionid);
	//Assert.assertEquals(newUpdatedAddress, newRetrievedActualAddress);

	
	
	
	
//	/// POSTING COMMENTS TO ALREADY LOGGED IN SESSION USER
//	/// USER STORY IS ALREADY THERE , YOU ARE JUST ADDING COMMENTS TO THAT
	
	given().log().all().pathParam("id","10101").header("Content-type","application/json").
	body("{\r\n"
			+ "    \"body\": \"This  Comment Genertaed BY SESSION FILTER From REST ASSURED JAVA .\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201);
	
	
	// THIS IS DONE WITHOUT USING SESSION FILTER
	// WITH PARSING STRING TO OBJECT AND THEN RETREVING THE VALUE
	// you have to use cookie method in rest assured for that after given keyword
//	
	given().cookie(newRetrievedsessionid).log().all().pathParam("id","10101").header("Content-type","application/json").
	
	body("{\r\n"
			+ "    \"body\": \"This Comment Genertaed BY PARSING STRING TO OBJECT From REST ASSURED JAVA .\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201);
	
	
	}
}
