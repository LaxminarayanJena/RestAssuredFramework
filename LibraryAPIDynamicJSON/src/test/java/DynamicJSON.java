import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DynamicJSON {

	@Test
	public void addBook()

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().header("Content-Type", "application/json").
				body(payload.AddBook()).
				when().
				post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		
		JsonPath js =ReusableMethods.rawToJson(res);
		System.out.println(js.get("ID"));
		
		
	}

}
