import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class FirstTest {

	public static void main(String[] args) {

		// Add Place
		// given -all input details
		// when-submit api -resource.http method
		// then-validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// import static io.restassured.RestAssured.*;
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract()
				.response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println("The placeid is - " + placeId);
		
		//-----------------------update place-------------------------
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\"70 winter run, Indias\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		

	}

}
