import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CookieBasedAuthentication {

	public static void main(String[] args) {

		JSONObject json = new JSONObject();
		// add json simple jar,jackson databind
		json.put("username", "suryaneel.mohanty");
		json.put("password", "123surya");
		
		RestAssured.baseURI = "http://localhost:8080/";
		Response res=RestAssured.given()
				.header("Content-Type", "application/json")
				.body(json)
				.post("/rest/auth/1/session");
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().jsonPath().prettify());
		
		String sessionid=res.getCookies().get("JSESSIONID");
		System.out.println(sessionid);
		
		
		Response response=given().contentType(ContentType.JSON)
				.cookie("JSESSIONID",sessionid).
		body("{\r\n" + 
				"  \"fields\": {\r\n" + 
				"    \"project\": {\r\n" + 
				"      \"key\": \"RES\"\r\n" + 
				"    },\r\n" + 
				"    \"summary\": \"cookie Bug Through uft\",\r\n" + 
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
		post("/rest/api/2/issue") ;
		
		System.out.println(response.getBody().jsonPath().prettify());

	}

}
