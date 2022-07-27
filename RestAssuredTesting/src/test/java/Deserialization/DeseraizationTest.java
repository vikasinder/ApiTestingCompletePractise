package Deserialization;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class DeseraizationTest {


	public static void main(String[] args) throws InterruptedException
	{

		// AS THE END POINT IS SAME FOR ALL THE REQUEST , SO YOU CAN GIVE IT LIKE THIS
		// RestAssured.baseURI="http://localhost:8080/";
		// OTHERWISE GIVE IT SEPREATELY LIKE GIVEN BELOW 
		// when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().as(GetCourses.class);
	
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qgWa_8zSTIA-X9VIRjc_kNnomfT6u7iG3hVjAusYazOe90WzgjbdSUQpFXoEHKtiA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialCode=url.split("code=")[1];
		String code=partialCode.split("&scope")[0];
		
		
	
		
		
	String access_Token_Response= given().urlEncodingEnabled(false)
	.queryParam("code", code)
	.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
	.queryParam("grant_type", "authorization_code")
	
	.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").then().extract().response().asString();
		
	 JsonPath json=new JsonPath(access_Token_Response);
	 String accessToken= json.getString("access_token");
		
		//expect().defaultParser(Parser.JSON).
	 //This additional line is added , as you have to tell wjhat lkind of response you
	 // are expecting from the received file 
	 // you can go and check the response sepecifically what is content-type in that
	 // if it is json than no need to write (optional)
	 
	 
	 
	 // .as(GetCourses.class) and instead of asString use return type should be object of GetCourses class
	 // HERE IT IS WHERE FROM JSON RESPONSE TO JAVA OBJECT CONVERSION TAKES PLACE
	 
	GetCourses getCourse=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).
	when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().as(GetCourses.class); // AS GETCOURSES CLASS IS USED 
	// BEACUSE THIS IS THE PARENT ROOT OF ALL THE OTHER CLASSES
	
	
	//  AS API RETURNS LIST OF OBJECTS
	// WE ARE LOOKING FOR ITEMS AT FIRST INDEX SO, WE GIVE INDEX 1.
	// 1 IS HARDCODED HERE , BUT IF YOU WANT TO ACCESS VALUES DYNAMICALLY 
	// THEN YOU HAVE TO ITERATE THROUGH THE LIST OF ITEMS
	System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle().toString());
	
	// YOU HAVE TO USE FOR LOOP OR ITERATOR - TO ITERATE THROUGH THE LIST
	
	// USING FOR LOOP
	
	// getCourse is object of GetCourses Class created above
	// getCourses is getter for courses in GetCourse Class [ api/ mobile/ web automation]
	// HERE WE ARE GETTING VALUES OF API COURSES , SO NO NEED TO CREATE OBJECT OF COURSES CLASS
	// OTHERWISE IF YOU HAVE TO PASS VALUES TO COURSES CLASS , YOU HAVE TO MAKE OBJECT OF THAT CLASS AND THEN PASS THE VALUES AS IN CASE OF ECOMMERCE CLASS EXAMPLE
	List<api> apiValues=getCourse.getCourses().getApi();
	List<webAutomation> webAutomationValues=getCourse.getCourses().getWebAutomation();
	List<mobile> mobiles=getCourse.getCourses().getMobile();
	
	
	for(int i=0; i<apiValues.size(); i++)
	{
		if(apiValues.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
		{
			System.out.println(apiValues.get(i).getPrice());
		}
		
	}
	
	// loop for printing all values in webautomation list field
	
	for(int i=0; i<webAutomationValues.size(); i++)
	{
			System.out.println(webAutomationValues.get(i).getCourseTitle());
	}
	
	
	
	}

}
