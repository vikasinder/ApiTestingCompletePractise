package DynamicDataPayloadsLibrary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ResuableMethods;
import Files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReadFromExternalFileJsonData {
	
	@Test
	public void addBook() throws IOException
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		// DONT HAVE QUERY PARAMETERS OR PATH PARAMETERS FOR THIS ONE 
		// SO STRAIGHT AWAY HEADER IS USED
		String returnValue=	given().log().all().header("Content-type","application/json").
		
		// ParaMeterizing the Payload
		// READING FROM EXTERNAL FILE JSON DATA
				//DATA CAN BE READ IN FORM OF STRING-- AS BODY ACCPECTS STRING VALUE
				// CONTENT OF FILE FIRTS HAVE TO BE CONVERTED INTO BYTE BY USING READALLBYTES METHOD IN FILES CLASS
				// AND THEN FROM BYTE IS IS CONVERTED USING new String Object 
		body(new String(Files.readAllBytes(Paths.get("c:\\Users\\vikas\\check.json")))).when().post("/Library/Addbook.php")
						
		//body(payloads.addBook(bookname,isbn,aisle,author)).when().post("/Library/Addbook.php")
	
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

}
