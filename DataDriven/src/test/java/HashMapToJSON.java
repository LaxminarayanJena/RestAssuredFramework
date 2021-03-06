import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class HashMapToJSON {

	public static void main(String[] args) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "java");
		map.put("isbn", "isbnjena99");
		map.put("aisle", "685934");
		map.put("author", "Jenaa");

		//change isbn value before running
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().header("Content-Type", "application/json").body(map).when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(res);
		System.out.println(js.get("ID"));

	}
	

}
