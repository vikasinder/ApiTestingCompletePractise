package SerializationDeserialization;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class DeseraizationTest {


	public static void main(String[] args) throws InterruptedException
	{
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
	 
	 
	 
	 // .as(GetCourses.class) and insted of asString use return type should be object of GetCourses class
	GetCourses getCourse=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).
	when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().as(GetCourses.class);
	
	
	//  AS API RETURNS LIST OF OBJECTS
	// WE ARE LOOKING FOR ITEMS AT FIRST INDEX SO, WE GIVE INDEX 1.
	// 1 IS HARDCODED HERE , BUT IF YOU WANT TO ACCESS VALUES DYNAMICALLY 
	// THEN YOU HAVE TO ITERATE THROUGH THE LIST OF ITEMS
	System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle().toString());
	
	// YOU HAVE TO USE FOR LOOP OR ITERATOR - TO ITERATE THROUGH THE LIST
	
	// USING FOR LOOP
	
	List<api> apiValues=getCourse.getCourses().getApi();
	
	for(int i=0; i<apiValues.size(); i++)
	{
		if(apiValues.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
		{
			System.out.println(apiValues.get(i).getPrice());
		}
	}
	
	}

}
