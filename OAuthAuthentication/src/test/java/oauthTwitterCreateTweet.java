

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class oauthTwitterCreateTweet {

	String ConsumerKey = "LukYUFWBLMwOE5xjjy8j1Pp17";
	String ConsumerSecret = "I1ygoeHA0M8ymi6O223NrkI038uF0s0n0uvvbas8m38AojLC3K";
	String Token = "1003172218380083200-sQA0SJl386y32Pq86BxtXD4eepVTGF";
	String TokenSecret = "g2g9VgBjYPRSQ14U7wFtTyLPh1lDlMta9tnIPS30ISSW7";

	@Test
	public void CreateNewTweet() {
		RestAssured.baseURI = "https://api.twitter.com/1.1";

		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.queryParam("status", "tweeting from eclipse 4th time")
				.when()
				.post("/statuses/update.json").then()
				.extract().response();

		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().jsonPath().prettify());

		JsonPath js = res.jsonPath();
		System.out.println(js.get("id"));

	}
}