import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ExcelReading {

	public static void main(String[] args) throws IOException {

		dataDriven d = new dataDriven();
		ArrayList<String> data = d.getData("RestAddbook", "LibraryData");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));

		// change all value before running
		System.out.println(map);

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().header("Content-Type", "application/json").body(map).when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(res);
		System.out.println(js.get("ID"));
	}

}
