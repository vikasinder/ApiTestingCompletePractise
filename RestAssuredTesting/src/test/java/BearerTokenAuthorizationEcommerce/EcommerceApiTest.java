package BearerTokenAuthorizationEcommerce;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import groovyjarjarpicocli.CommandLine.Spec;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EcommerceApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	//	RestAssured.baseURI="https://rahulshettyacademy.com";

		//  	OR YOU CAN USE THIS IN SPECBUILDER CLASS AS WELL AS THIS IS COMMON URL FOR ALL THE REQUESTS    //
		
		
				RequestSpecification intialRequest=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")/////////THIS SPEC BUILDER DOES NOT HAVE TOKEN IN IT AND JSON CONTENT TYPE
				.setContentType(ContentType.JSON).build();
		
				// DATA THAT NEEDS TO SENT AS PAYLOAD IN BODY- THAT VALUES  NEEDS TO BE SERAILIZED
				
				LoginClass login=new LoginClass();
				login.setUserEmail("vikasinder72@gmail.com");
				login.setUserPassword("Inder@1057");
				
				RequestSpecification recievedReq=given().log().all().spec(intialRequest)
				.body(login); // object is passed here // seriallization takes place here
			
						
				//Three ways to perform same thing
				//1. in the query itself you can check the response recieved
				//2. using json path 
				//3. using pojo classes - desiralization
				
				// Desialization using POJO CLASSES IS IMPLEMENTED HERE
				
				
				// DATA THAT YOU WILL RECIEVE AS RESPONSE BODY AFTER THE REQUEST HAS BEEN MADE EXAMPLE:- RAHULSHETTY COURSES LIST 
				
				DesializationresponseBody response= recievedReq.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(DesializationresponseBody.class);
				// VALUES THAT COMES IN RESPONSE BODY WILL GO INTO THE PRIVATE VARABLE DECLARED WITH GETTER AND SETTERS IN dESIALIZATIONrESPONSEbODY CLASS
				// values are not converted to asstring() but to class object
				String token=	response.getToken();
				String userId=	response.getUserId();
				
				System.out.println(token);
				System.out.println(userId);
				
				//    ADD PRODUCT TO SITE    //
				
				
				RequestSpecification intialAddPrdoct=new RequestSpecBuilder()
						.setBaseUri("https://rahulshettyacademy.com")
						.addHeader("authorization", token) /////////THIS SPEC BUILDER HAVE TOKEN IN IT  BUT NOT CONTENT TYPE JSON AS FORM DATA IS SENT
						.build();
				
				
				RequestSpecification intialAddReq=given().log().all()
						.spec(intialAddPrdoct)
						.param("productName", "Pants for Mens")  // AS THE BODY IS IN FORM OF FORMDATA AND NOT JSON PAYLOAD FORMAT
						.param("productAddedBy", userId)         // OTHERWISE .body(json) is sent 
						.param("productCategory", "Fashion")
						.param("productSubCategory", "Jeans and Shirts")
						.param("productPrice", "900")
						.param("productDescription", "Addias Originals Jeans")
						.param("productFor", "Women")
						.multiPart("productImage",new File("C:\\Users\\vikas\\Desktop\\hmgoepprod.jpg"));// AS IT IS A FILE SO MULTIPART IS USED
						
				
				Response resAddRequest=intialAddReq.when().post("/api/ecom/product/add-product").then().log().all()
						.extract().response();
	
						String convertToString=resAddRequest.asString();
	
						JsonPath json=new JsonPath(convertToString);
						String value= json.get("productId");
						
						System.out.println(value);// This value gives productId Value
	
	
						RequestSpecification intialcreateproductorder=new RequestSpecBuilder()
								.setBaseUri("https://rahulshettyacademy.com")
								.setContentType(ContentType.JSON)
								.addHeader("authorization", token)
								.build();
						
						// AS ORDERDETAILS IS LIST OF ORDERS , SO WE HAVE TO MAKE ORDER CLASS AS BASE CLASS AND ORDER LIST CLASS THAT RETURNS LIST OF ORDERS
						// HERE WE ARE GETTING VALUES OF API COURSES , SO NO NEED TO CREATE OBJECT OF COURSES CLASS
						// OTHERWISE IF YOU HAVE TO PASS VALUES TO COURSES CLASS , YOU HAVE TO MAKE OBJECT OF THAT CLASS AND THEN PASS THE VALUES AS IN CASE OF ECOMMERCE CLASS EXAMPLE

						
						Orders order=new Orders();
						
						OrdersDetails orderDetail=new OrdersDetails();
						orderDetail.setCountry("India");
						orderDetail.setProductOrderedId(value);
						
						OrdersDetails orderDetail1=new OrdersDetails();
						orderDetail1.setCountry("pakistan");
						orderDetail1.setProductOrderedId(value);
						
						OrdersDetails orderDetail2=new OrdersDetails();
						orderDetail2.setCountry("Germany");
						orderDetail2.setProductOrderedId                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        (value);
						
						// AS IN ORDER CLASS SET ORDER IS EXPECTING LIST OF ORDERS 
						// SO WE HAVE TO CREATE A LIST OF ORDERS TO PASS
						
						List<OrdersDetails> orderDetailList=new ArrayList<OrdersDetails>();
						
						orderDetailList.add(orderDetail);
						orderDetailList.add(orderDetail1);
						orderDetailList.add(orderDetail2);
						
						
						// AND THE ARRAYLIST IS THEN PASSED TO OJECT CREATED FOR ORDERS CLASS
						order.setOrders(orderDetailList);
						
						
						
						RequestSpecification orderCreation=given().log().all().spec(intialcreateproductorder) // spec is used when you use specbuilder
								.body(order);
						
						
						Response ResponseValue= orderCreation.when().post("/api/ecom/order/create-order").then()
								.log().all().extract().response();
						
						String stringresponseValue=ResponseValue.asString();
						
//						JsonPath json3=new JsonPath(stringresponseValue);
//						
//						String msg=	json3.get("message");
//						
						System.out.println(stringresponseValue);
						
	}

}
