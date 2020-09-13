import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class HashMapToJSON {

	public static void main(String[] args) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "RestAssured");
		map.put("isbn", "s3n84e");
		map.put("aisle", "585934");
		map.put("author", "Jena");

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().header("Content-Type", "application/json").body(map).when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(res);
		System.out.println(js.get("ID"));

	}
	

}
