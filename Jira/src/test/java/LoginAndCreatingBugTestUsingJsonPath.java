import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginAndCreatingBugTestUsingJsonPath {

	public static void main(String[] args) {
	
		RestAssured.baseURI="http://localhost:8080";
		Response res=given().
		header("Content-Type","application/json").
		body("{ \"username\": \"suryaneel.mohanty\", \"password\": \"123surya\" }").
		when().
		post("/rest/auth/1/session"). 
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON) .
		extract().response();
		String stringResponse=res.asString();
		System.out.println(stringResponse);
		

		JsonPath js= new JsonPath(stringResponse);
		String sessionid=js.get("session.value");
		System.out.println(sessionid);
		
		

		given().
		header("Content-Type","application/json").
		//header("Cookie","JSESSIONID=C5D5C9C149153B6098F3EE43BAAD29AC").
		header("Cookie","JSESSIONID=\""+sessionid+"\"").
		body("{\r\n" + 
				"  \"fields\": {\r\n" + 
				"    \"project\": {\r\n" + 
				"      \"key\": \"RES\"\r\n" + 
				"    },\r\n" + 
				"    \"summary\": \"Bug Through uft\",\r\n" + 
				"    \"description\": \"Creating  bug through automation\",\r\n" + 
				"    \"issuetype\": {\r\n" + 
				"      \"name\": \"Bug\"\r\n" + 
				"    }\r\n" + 
				"   \r\n" + 
				"   \r\n" + 
				"    \r\n" + 
				"  }\r\n" + 
				"}").
		when().
		post("/rest/api/2/issue").
		then().assertThat().statusCode(201).and().contentType(ContentType.JSON) ;
		
		
		
	}
	}