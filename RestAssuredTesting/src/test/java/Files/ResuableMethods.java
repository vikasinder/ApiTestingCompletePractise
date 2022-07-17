package Files;

import io.restassured.path.json.JsonPath;

public class ResuableMethods {
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath jsonResponse=new JsonPath(response);
		return jsonResponse;
		
	}

}
