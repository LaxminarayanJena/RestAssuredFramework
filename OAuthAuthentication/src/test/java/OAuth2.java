import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2 {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
		
		Response res=RestAssured.given()
		 .auth()
		.oauth2("ab9faf629e4f75772ad40760249cc30d188cf7ee") //generate using client credentials
		.post("/api/1349/chickens-feed");
		
		System.out.println(res.getStatusCode());
		
		System.out.println(res.jsonPath().prettify());
		System.out.println("---------------");
		System.out.println(res.getBody().asString());
		

	}

}
