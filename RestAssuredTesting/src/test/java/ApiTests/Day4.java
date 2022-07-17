package ApiTests;

import Files.payloads;
import io.restassured.path.json.JsonPath;

public class Day4 {

	public static void main(String[] args) {

	String checkDummyPayload=payloads.coursePrice();
	
	JsonPath json=new JsonPath(checkDummyPayload);
	
	//PRINT NO OF COURSES RETURNED BY API 
	
	int size=json.getInt("courses.size()");
	System.out.println(size);
	
	//PRINT PURCHASE AMOUNT
	
	int purchaseAmount=json.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseAmount);
	 
	//PRINT PURCHASE AMOUNT
	
	String titleOfFirstCourse=json.getString("courses[0].title");
	System.out.println(titleOfFirstCourse);
		
	//PRINT TITLE AND PRICE OF EACH COURSE AMOUNT
	
	for(int i=0;i<json.getInt("courses.size()");i++)
		
		//OR THIS CAN BE USED
	//for(int i=0;i<size;i++)
		
		{
		
		// Variable is inserted between string , so remember this point 
		
		String eachCourse=json.getString("courses["+i+"].title");
		int eachTitle=json.getInt("courses["+i+"].price");
		System.out.println("Course Name Is "+ eachCourse + " And Price Is " + eachTitle);
		}	
	
		
	
	//PRINT PRICE OF RPA COURSE
	
		for(int J=0;J<json.getInt("courses.size()");J++)
			
			//OR THIS CAN BE USED
		//for(int i=0;i<size;i++)
			
		{
			
			// Variable is inserted between string , so remember this point 
			
			String eachCourse=json.getString("courses["+J+"].title");
			int eachTitle=json.getInt("courses["+J+"].price");
			if(eachCourse.equalsIgnoreCase("Cypress"))
			{
				System.out.println( eachCourse + " And Price Is " +eachTitle);
				break;
			}
			
		}	
		
		}	
		

	
}
