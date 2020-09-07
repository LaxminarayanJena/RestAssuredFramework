import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2AcessTokenGenerator {
	
	//Authenticate-Determines whether users are who they claim to be
	//Authorise -Determines what users can and cannot access
	
    //OAuth acess token generator using client credentials -authentication
	public static void main(String[] args) {
		
		Response res=RestAssured.given()
		.formParam("client_id", "seleniumapp")
		.formParam("client_secret", "af3a34d8f26b9a439bfa38432d98c73f")
		.formParam("grant_type", "client_credentials")
		.post("http://coop.apps.symfonycasts.com/token");
				
		String token=res.jsonPath().get("access_token");
		System.out.println(token);
		
		
		
		

	}

}
