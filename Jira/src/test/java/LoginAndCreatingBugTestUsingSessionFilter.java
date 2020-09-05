import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginAndCreatingBugTestUsingSessionFilter {

	public static void main(String[] args) {
	
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session = new SessionFilter();
		
		String res=given().
		header("Content-Type","application/json").
		body("{ \"username\": \"suryaneel.mohanty\", \"password\": \"123surya\" }")
		.log().all().
		filter(session).
		when().
		post("/rest/auth/1/session"). 
		then().
		log().all().extract().response().asString();
		
		
		

		given().
		header("Content-Type","application/json").
		body("{\r\n" + 
				"  \"fields\": {\r\n" + 
				"    \"project\": {\r\n" + 
				"      \"key\": \"RES\"\r\n" + 
				"    },\r\n" + 
				"    \"summary\": \"Bug Through session filter\",\r\n" + 
				"    \"description\": \"Creating  bug through automation\",\r\n" + 
				"    \"issuetype\": {\r\n" + 
				"      \"name\": \"Bug\"\r\n" + 
				"    }\r\n" + 
				"   \r\n" + 
				"   \r\n" + 
				"    \r\n" + 
				"  }\r\n" + 
				"}").
		filter(session).
		when().
		post("/rest/api/2/issue").
		then().assertThat().statusCode(201).and().contentType(ContentType.JSON) ;
		
		
		
	}
	}