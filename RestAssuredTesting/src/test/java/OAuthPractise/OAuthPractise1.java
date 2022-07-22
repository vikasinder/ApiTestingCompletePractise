package OAuthPractise;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver; 
public class OAuthPractise1 {
	

												// WHY WE NEED AUTHENICATION //
	
	// TO MAKE ACCESS TO AN API MORE SECURE - SO THAT NOT EVERYBODY CANNOT ACCESS IT WITHOUT USING PROPER AUTHORIZATION MECHANISM//
	
	//  TYPES OF AUTHENICATION DISSCUSSED  //
	// 1. BASIC AUTHENTICATION
	// 2. COOKIE BASED AUTHENTICATION DISSCUSSED IN JIRA API
	// 3. OAuth AUTHENICATION FOR GOOGLE
	// 4. BEARER TOKEN
	
	 // TWO TYPES OF GRANT TYPES POPULARLLY USED 1. AUTHORIZATION CODE 2. CLIENT CREDENTIALS (BETWEEN TWO APPLICATIONS - NO HUMAN INTERVENTION - NO CODE IS NEEDED - STARIGHT AWAY IMPLEMENTED)
	
	///*********************** GRANT TYPE DISSCUSSED IS AUTHORIZATION CODE  ************************* ///
	
					//	*********  WHY WE NEED OAuth 2.0 AUTHENTICATION ? **********//
		
	
	// ALSO CALLED A S THIRD PARTY AUTHENTICATION
	// NO DATA BREACH HEADACHE FOR APPLICATION ( IF IN CASE CLIENT PERSONAL INFORMATION IS LEAKED )
							// THIS CAN CAUSE BILLIONS OF DOLLARS OF FINE
				// BY THIS MECHANISM NOTHING IS SAVED ON YOUR SERVER OF THE CLIENT (BOOKMYSHOW) IN THIS CASE
							// CENTRALIZED AUTHENTICATION SYSTEM
	// GOOGLE WILL PASS ONLY BASIC INFORMATION TO THE SITE THAT IS TAKING ITS SERVICE ( REMEMBER PASSWORD IS NOT SHARED )
	// LIKE HELLO VIKAS SHARMA -- WELCOME TO OUR SITE
	// THEY WILL NOT GIVE SECRECT INFORMATION TO THE CLIENT SITE EG. (BOOK MY SHOW)
	// NEED NOT MAINTAIN USER PROFILE DATA
	// COMMON LOGIN FOR HOTEL BOOKING - CAR BOOKING -- RENTAL BOOKING
	// NO NEED TO CREATE DIGFFRENT ACCOUNTS FOR DIFFTRENT WEBSITES
	// NO NEED TO REMEMBER DIFFRENT PASSWORDS AS WELL - ONLY GOOGLE WILL DO ALL 
	// THIS ALLOW RICHER WEBSITES BY ALLOWING DISPERATE APPLICATIONS  TO TALK TO EACH OTHER
	
	
	
	
	//***********STEPS HAPPENS AT BACK END ***************//
	
	// USER SIGN INTO GOOGLE BY HITTING GOOGLE AUTHORIZATION SERVER //
	// AUTHORIZATION SERVER SENDS ONE TIME PASSWORD ( ACCESS CODE ) BACK TO BOOK MY SHOW //
	// BOOK-MY-SHOW USES THE CODE ALONG WITH ITS REQUEST AND HITS GOOGLE RESOURCE SERVER THIS TIME //
	// GOOGLE RESOURCE SERVER KNOWS THIS TIME THAT THIS CODE IS GIVEN BY GOOGLE AUTHORIZATION SERVER
	// MATCHES THE CODE WITH PROFILE
	// WITH WHAT PROFILE THAT CODE IS ASSOCIATED ( AUTHENICATED THROUGH BOOK MY SHOW )
	// AND SENDS BACK ACCESS TOKEN BACK THIS TIME  WITH SOME ADDITIONAL INFORMATION //
	// GENERALLY IT IS CALLED SCOPE- MEANING WHAT KIND OF RESORCES YOU NEED FROM GOOGLE RESOURCE SERVER
	// LIKE FIRST NAME / LASTNAME / EMAIL + THE ACCESS TOKEN + OR IN SOME CASES DISPLAY PICTURE AS WELL
	// FOR EVERY OPERATION YOU PERFORM AFTERWARS ON BOOKMYSHOW THIS ACCESS TOKEN IS THERE
	// FOR ALL THE TRANSACTION YOU DO ON BOOKMYSHOW APPLICATION
	// BOOK-MY-SHOW GRANTS ACCESS TO USER BY VALIDATING THE ACCESS TOKEN TO PERFORM VARIOUS TASKS
	
	
	
	
	/// How to perform complete process ///
	
	// 1. End Point:  AUTHORIZATION SERVER URL
	// 2. ADD QUERY PARAMETERS TO IT IN POSTMAN
	// URL WILL BE : ENDPOINT ( authorization server url ) + SCOPE + Auth_url + response type + redirect_url
	// 3. AFTER THIS EXTRACT THE BUILD URL - COPY THAT AND PASTE THAT IN YOUR BROWSER TO GET THE ACCESS CODE
	// 4. OUTPUT - YOU WILL RECIEVE A CODE
	// 5. TAKE THAT CODE AND IN POSTMANT CREATE ANOTHER URL TO HIT RESOURCE SERVER
	// 6. YOU WILL RECIEVE ACCESS TOKEN
	// URL WILL BE : ENDPOINT ( Resource server url ) + client_id + client secret + response type + redirect_url + grant type
	
	
	
	// fIRST CREATE URL WHITH VALUES GIVEN ( URL VALUES ARE GIVEN INTIALLY )
	//*************** AUTHORIZARION URL + SCOPE + CLIENT_ID  + RESPONSE_TYPE + REDIRECT_URL *********** //
	
	// THEN GO ON YOUR BROWSER AND IN THE NAVIGATION TAB PASTE THAT URL AND HIT ENTER
	// IT WILL GOES TO BLANK PAGE
	// EXTRACT THE  NEW URL WITH CODE IN IT 
	// COME BACK TO POSTMAN PASTE THAT URL IN NEW GET REQUEST
	// EXTRACT THE CODE FROM QUERY PARAMETERS VALUE
	// PASTE THAT CODE IN  TOKEN URL TO GET THE ACCES TOKEN VALUE ( URL VALUES ARE GIVEN INTIALLY  + ACCESS CODE)
	// ********** ACCESS TOKEN URL + CLIENTID + CLIENT SECRET + REDIRECT+URI + GRANT TYPE
	// IT WILL RETURN JSON VALUES WITH ACCESS TOKEN IN
	
	// TAKE THAT ACCESS TOKEN AND USE IN YOUR APPLICATION URL
	// **********CALLBACK URL -- RAHULSHETTY ACACDEMY + ACCESS TOKEN
	
	
	public static void main(String[] args) throws InterruptedException
	{
	//RestAssured.baseURI="https://rahulshettyacademy.com/";
	
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikas\\git\\RestAssuredApiTesting\\RestAssuredTesting\\Driver\\chromedriver.exe");
//		WebDriver driver=new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
//		driver.manage().window().maximize();
//		Thread.sleep(4000);
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("vikas_sharma1057@yahoo.com");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//		
		//driver.findElement(By.cssSelector("input[type]='email'")).sendKeys(Keys.ENTER);
		
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector("input[type]='password'")).sendKeys("Vikas@1057");
//		driver.findElement(By.cssSelector("input[type]='password'")).sendKeys(Keys.ENTER);
//		
//		String url=driver.getCurrentUrl();
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qgAu-rEgMjKzIPuJSUsBGn2Cc1NKWM0gLT3ctyZCSuM5tVvOVTXoJ5cV0bR8pBYGg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
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
		
		
	String response=given().queryParam("access_token", accessToken).
	when().get("https://rahulshettyacademy.com/getCourse.php").then().extract().response().asString();
	
	System.out.println(response);
	
	
	}

}
