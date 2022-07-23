package SerializationDeserialization;

import java.util.List;

public class Courses {
	
	private List<webAutomation> webAutomation;
	private  List<api> api;
	private List<mobile> mobile;
	
//	 { "instructor": "RahulShetty", "url": "rahulshettycademy.com", "services": "projectSupport", "expertise": "Automation",
//	"courses": { "webAutomation": [ { "courseTitle": "Selenium Webdriver Java", "price": "50" }, { "courseTitle": "Cypress",
//		"price": "40"
//		},
//		{
//		"courseTitle": "Protractor",
//		"price": "40"
//		}
//		],
//		"api": [
//		{
//		"courseTitle": "Rest Assured Automation using Java",
//		"price": "50"
//		},
//		{
//		"courseTitle": "SoapUI Webservices testing",
//		"price": "40"
//		}
//		],
//		"mobile": [
//		{
//		"courseTitle": "Appium-Mobile Automation using Java",
//		"price": "50"
//		}
//		]
//		},
//		"linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
//		}
//	
	
	public List<webAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<webAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<SerializationDeserialization.api> getApi() {
		return api;
	}
	public void setApi(List<SerializationDeserialization.api> api) {
		this.api = api;
	}
	public List<SerializationDeserialization.mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<SerializationDeserialization.mobile> mobile) {
		this.mobile = mobile;
	}
	
	

}
