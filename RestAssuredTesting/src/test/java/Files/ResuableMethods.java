package Files;

import io.restassured.path.json.JsonPath;

public class ResuableMethods {
	
	// THIS METHOD CONVERTS STRING TO OBJECT AND
	// RETURNS BACK SO THAT WE CAN EXTRACT VALUE FROM THE REURNED STRING 
	// AS WE CANNOT TRAVERSE THROUG STRING 
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath jsonResponse=new JsonPath(response);
		return jsonResponse;
		
	}

}
