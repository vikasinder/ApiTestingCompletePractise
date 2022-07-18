package DynamicDataPayloadsLibrary;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ResuableMethods;
import Files.payloads;
import DynamicDataPayloadsLibrary.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


// ADDING DATA TO API POST REQUEST THROUGH ARRAY LIKE WE DO WITH EXCEL

public class DynamicJson {
	
	@DataProvider(name="credentials")
	public Object[][] getData()
	{
		return new Object[][] { {"Jack","wdhd8","12321","inder sharma"}, {"JackDaniel","dwhd82","223212","paru sharma"} };
	}
	
	@Test(dataProvider = "credentials")
	public void addBook(String bookname,String isbn,String aisle,String author)
	{
//		String	bookname="Jack and Jones";
//		String	isbn="23ed4";
//		String	aisle="84d";
//		String	author="vikas sharma";
//		
		RestAssured.baseURI="http://216.10.245.166";
		
		// DONT HAVE QUERY PARAMETERS OR PATH PARAMETERS FOR THIS ONE 
		// SO STRAIGHT AWAY HEADER IS USED
		String returnValue=	given().log().all().header("Content-type","application/json").
		
		// ParaMeterizing the Payload
				
		body(payloads.addBook(bookname,isbn,aisle,author)).when().post("/Library/Addbook.php")
	
		// THIS BODY CATCHES THE REPONSE AFTER POSTING VALUES ON TO API
		// SO BODY VALUES ARE CHECKED HERE
		// OR YOU CAN GET THE RESPONSE IN STRING FORMAT AS SHOWN BELOW AND THEN CONVERT TO JSON OBJECT AND TRAVERSE THE 
		// VALUES AND MATCH THE RESULT
		
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();
		
		
		// THIS METHOD CONVERTS STRING TO OBJECT AND
		// RETURNS BACK SO THAT WE CAN EXTRACT VALUE FROM THE REURNED STRING 
		// AS WE CANNOT TRAVERSE THROUG STRING 
		JsonPath json=	ResuableMethods.rawToJson(returnValue);	
		String retrievedvalue=json.get("ID");
		System.out.println(retrievedvalue);
		
			
	}

	
	
	
	
//	DataProviders d=new DataProviders();
//	
//	@Test(dataProvider = "Credentials", dataProviderClass = DynamicDataPayloadsLibrary.DataProviders.class)
//	
//
//	public void addBook(String bookname,String isbn,String aisle,String author) throws Throwable {
//	{
//		
//		RestAssured.baseURI="http://216.10.245.166";
//		
//		// DONT HAVE QUERY PARAMETERS OR PATH PARAMETERS FOR THIS ONE 
//		// SO STRAIGHT AWAY HEADER IS USED
//		String returnValue=	given().log().all().header("Content-type","application/json").
//		
//		// ParaMeterizing the Payload
//				
//		body(payloads.addBook(bookname,isbn,aisle, author)).when().post("/Library/Addbook.php")
//	
//		// THIS BODY CATCHES THE REPONSE AFTER POSTING VALUES ON TO API
//		// SO BODY VALUES ARE CHECKED HERE
//		// OR YOU CAN GET THE RESPONSE IN STRING FORMAT AS SHOWN BELOW AND THEN CONVERT TO JSON OBJECT AND TRAVERSE THE 
//		// VALUES AND MATCH THE RESULT
//		
//		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();
//		
//		
//		// THIS METHOD CONVERTS STRING TO OBJECT AND
//		// RETURNS BACK SO THAT WE CAN EXTRACT VALUE FROM THE REURNED STRING 
//		// AS WE CANNOT TRAVERSE THROUG STRING 
//		JsonPath json=	ResuableMethods.rawToJson(returnValue);	
//		String retrievedvalue=json.get("ID");
//		System.out.println(retrievedvalue);
//	}
			
	//}

	
}
