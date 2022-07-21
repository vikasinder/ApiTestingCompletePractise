package JiraApiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import Files.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class CheckingTheResponseRecievedJira {
	

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
	
	String comment= "Vikas Hello How R U";
String commentResponse=	given().log().all().pathParam("id","10101").header("Content-type","application/json").
	body("{\r\n"
			+ "    \"body\": \""+comment+"\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201).extract()
	.response().asString();
	
	
	JsonPath json=new JsonPath(commentResponse);
	String commentId=	json.get("id");
			

//	/// POSTING COMMENTS TO ALREADY LOGGED IN SESSION USER
//	/// USER STORY IS ALREADY THERE , YOU ARE JUST ADDING COMMENTS TO THAT
// YOU CANNOT SEND MULTIPLE HEADERS IN SINGLE HEADER VALUE
	
	
	given().log().all().pathParam("id","10101").header("Content-Type","multipart/form-data").header("X-Atlassian-Token","no-check")
	.multiPart("file",new File("C:\\Users\\vikas\\git\\RestAssuredApiTesting\\RestAssuredTesting\\src\\test\\java\\JiraApiTesting\\TextFile\\demo.txt"))
	.filter(session).when().post("/rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
	
	
	
	
//	/// POSTING COMMENTS TO ALREADY LOGGED IN SESSION USER
//	/// USER STORY IS ALREADY THERE , YOU ARE JUST ADDING COMMENTS TO THAT
// YOU CANNOT SEND MULTIPLE HEADERS IN SINGLE HEADER VALUE
	// YOU HAVE TO USE MULTIPLE TAGS FOR THAT
	
	String issueDetails=given().log().all().pathParam("id","10101").queryParam("fields", "comment").filter(session).when().get("/rest/api/2/issue/{id}").then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(issueDetails);
	
//	JsonPath json1=new JsonPath(issueDetails);
// int commentCount=	json1.get("fields.comment.comments.size()");
// 	for(int i=0;i<=commentCount;i++)
// 	{
// 		String commentIdIsuue=	json1.get("fields.comment.comments["+ i +"].id").toString();
// 		if(commentIdIsuue.equalsIgnoreCase(commentId))
// 		{
// 	String issueBody=	json1.get("fields.comment.comments["+ i +"].body").toString();
// 	Assert.assertEquals(issueBody,comment);
// 		}
 		
 	
 	}
	

}
