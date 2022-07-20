package DynamicDataPayloadsLibrary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ResuableMethods;
import Files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dynamicjsonarrayexample {
	
	@DataProvider(name="credentials")
	public Object[][] getData()
	{
		return new Object[][] { {"Jacky12","wdhd8112","12321112","inder sharma112"}, {"JackDaniel2 whiskey1","dwhd82112","223212112","paru sharma112"} };
	}
	@DataProvider(name="credentialsdelete")
	public Object[][] getDatadelete()
	{
		return new Object[][] { {"wdhd811212321112"}, {"dwhd82112223212112"} };
	}
	@Test(dataProvider = "credentials")
	public void addBook(String bookname,String isbn,String aisle,String author)
	{
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

	@Test(dataProvider = "credentialsdelete")
	public void deleteBook(String value)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		// DONT HAVE QUERY PARAMETERS OR PATH PARAMETERS FOR THIS ONE 
		// SO STRAIGHT AWAY HEADER IS USED
		String returnValue=	given().log().all().header("Content-type","application/json").
		
		// ParaMeterizing the Payload
				
		body(payloads.deleteBook(value)).when().delete("/Library/DeleteBook.php")
	
		// THIS BODY CATCHES THE REPONSE AFTER POSTING VALUES ON TO API
		// SO BODY VALUES ARE CHECKED HERE
		// OR YOU CAN GET THE RESPONSE IN STRING FORMAT AS SHOWN BELOW AND THEN CONVERT TO JSON OBJECT AND TRAVERSE THE 
		// VALUES AND MATCH THE RESULT
		
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
		
		
		// THIS METHOD CONVERTS STRING TO OBJECT AND
		// RETURNS BACK SO THAT WE CAN EXTRACT VALUE FROM THE REURNED STRING 
		// AS WE CANNOT TRAVERSE THROUG STRING 
		JsonPath json=	ResuableMethods.rawToJson(returnValue);	
		String retrievedvalue=json.get("msg");
		System.out.println(retrievedvalue);
		
			
	}

	
	

	
	
}
