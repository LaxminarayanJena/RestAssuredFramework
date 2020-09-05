import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class CreateCommentPathParamAndUploadAttachment {

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
				"    \"summary\": \"END TO END Bug Through session filter\",\r\n" + 
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
		

		given().
		header("Content-Type","application/json").
		//    \""+sessionid+"\"
		body("{\r\n" + 
				"      \"body\": \" end to end Jena comment added 1st time\",\r\n" + 
				"      \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"      }\r\n" + 
				"    }").
		filter(session).
		when().
		post("/rest/api/2/issue/"+commentid+"/comment").
		then().assertThat().statusCode(201).and().contentType(ContentType.JSON) ;
		
		//AddAttachment -Note jira field must be configured to upload attachmnet
		
		//curl -D- -u {username}:{password} -X POST -H "X-Atlassian-Token: nocheck" -F "file=@{path/to/file}" http://{base-url}/rest/api/2/issue/{issue-key}/attachments

		given().header("X-Atlassian-Token","nocheck").filter(session).pathParam("key", commentid)
		.multiPart("file",new File("jira.txt")).when()
		.post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		
		/*
		given().header("X-Atlassian-Token","nocheck").filter(session)
		.multiPart("file",new File("jira.txt")).when()
		.post("rest/api/2/issue/"+commentid +"/attachments").then().log().all().assertThat().statusCode(200);
			*/
		
		//GetIssue
		String issueDetails=given().filter(session).pathParam("key", commentid)
		.queryParam("fields", "comment")
		.log().all().when().get("/rest/api/2/issue/{key}").then()
		.log().all().extract().response().asString();
		
		System.out.println(issueDetails);
	}

}
