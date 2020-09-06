import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.stream.IntStream;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class ListOfCommentsAndHttpsValidation {

	public static void main(String[] args) {
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session = new SessionFilter();
		
		String res=given().relaxedHTTPSValidation().
		header("Content-Type","application/json").
		body("{ \"username\": \"suryaneel.mohanty\", \"password\": \"123surya\" }")
		.log().all().
		filter(session).
		when().
		post("/rest/auth/1/session"). 
		then().
		log().all().extract().response().asString();
		
		
		//creating issue

		String CreateIssueResponse=given().
		header("Content-Type","application/json").
		body("{\r\n" + 
				"  \"fields\": {\r\n" + 
				"    \"project\": {\r\n" + 
				"      \"key\": \"RES\"\r\n" + 
				"    },\r\n" + 
				"    \"summary\": \"Multiple comments bug\",\r\n" + 
				"    \"description\": \"Creating  bug through automation\",\r\n" + 
				"    \"issuetype\": {\r\n" + 
				"      \"name\": \"Bug\"\r\n" + 
				"    }\r\n" + 
				"   \r\n" + 
				"   \r\n" + 
				"    \r\n" + 
				"  }\r\n" + 
				"}").
		log().all().
		filter(session).
		when().
		post("/rest/api/2/issue").
		then().	
		log().all().extract().response().asString()		 ;
		
		
		JsonPath js= new JsonPath(CreateIssueResponse);
		String commentid=js.get("id");
		System.out.println(commentid);
		
		//Adding comment
		
for(int i=0;i<=5;i++)
{
	String expectedComment= "end to end Jena comment added -" +i + "time";
	given().
	header("Content-Type","application/json").
	//    \""+sessionid+"\"   
	
	body("{\r\n" + 
			"      \"body\": \""+expectedComment+"\",\r\n" + 
			"      \"visibility\": {\r\n" + 
			"        \"type\": \"role\",\r\n" + 
			"        \"value\": \"Administrators\"\r\n" + 
			"      }\r\n" + 
			"    }").
	filter(session).
	when().
	post("/rest/api/2/issue/"+commentid+"/comment").
	then().assertThat().statusCode(201).and().contentType(ContentType.JSON) ;
	
}
			
		
		//GetIssue
		String issueDetails=given().filter(session).pathParam("key", commentid)
		.queryParam("fields", "comment")
		.log().all().when().get("/rest/api/2/issue/{key}").then()
		.log().all().extract().response().asString();
		
		System.out.println(issueDetails);
		
		JsonPath js1= new JsonPath(issueDetails);
		int commentsCount=js1.getInt("fields.comment.comments.size()");	
		for(int i=0; i<commentsCount ;i++)
		{
			System.out.println(js1.getInt("fields.comment.comments["+i+"].id"));
		}
		
		
	}

}
