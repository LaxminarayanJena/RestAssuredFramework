import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
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

		// -----------------------update place-------------------------

		String newAddress = "summer walking";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// -----------------------get place-------------------------

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);

		// JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");

		System.out.println("The adress is - " + actualAddress);

		Assert.assertEquals(actualAddress, newAddress);

	}

}
