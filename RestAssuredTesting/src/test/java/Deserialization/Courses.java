package Deserialization;

import java.util.List;

public class Courses {
	
	// AS ALL THESE ARE RETURNING ARRAY OF VALUES THAT IS WHY WE HAVE USED LIST TO INTIALIZE THEM
	
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
//		],                         list of api's
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
//		"mobile": [                   list of mobiles
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
	public List<Deserialization.api> getApi() {
		return api;
	}
	public void setApi(List<Deserialization.api> api) {
		this.api = api;
	}
	public List<Deserialization.mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Deserialization.mobile> mobile) {
		this.mobile = mobile;
	}
	
	

}
