package Deserialization;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

public class GetCourses {

	
	// PROCESS OF CONVERTING JAVA OBJECT CLASS TO  PAYLOAD IN THE FORM OF ( JSON OR XML ) OBJECT IS CALLED SERIALIZATION
	// AS DATA IS TRANSFERED IN FORM OF JSON OVER THE APIS REQUEST - RESPONSE
	//  FROM JAVA OBJECT TO JSON IS CONVERTED AT RUNTIME
	// OBJECT IS PASSED IN BODY AS PAYLOAD eg:  .body(m).when().post() AND AT RUNTIME IT IS CONVERTED TO JSON OBJECT
	
	// PROCESS OF CONVERTING JSON RESPONSE BODY BACK TO JAVA OBJECT IS CALLED DESERIALIZATION

	//                SERIALIZATION                      DESERIALIZATION
	
	//            WHEN WE ARE SENDING BODY				WHEN WE ARE RECIVING BODY AS RESPONSE
	//               AS PAYLOAD								GET REQUEST
	//         eg:  .body(m).when().post()						Getters are used 
	//         // PUT -  POST - DELETE 
		//          setters are used
	
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String instructor;
	private String linkedIn;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	
	
	
}
