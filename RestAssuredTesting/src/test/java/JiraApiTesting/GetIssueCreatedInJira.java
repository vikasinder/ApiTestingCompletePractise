package JiraApiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import Files.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class GetIssueCreatedInJira {


	@Test
	public void getIssue()
	{
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
// YOU CANNOT SEND MULTIPLE HEADERS IN SINGLE HEADER VALUE
	// YOU HAVE TO USE MULTIPLE TAGS FOR THAT
	
String response1=given().log().all().pathParam("id","10101").queryParam("fields", "comment").filter(session).when().get("/rest/api/2/issue/{id}").then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(response1);
	
	}
	
	
}
