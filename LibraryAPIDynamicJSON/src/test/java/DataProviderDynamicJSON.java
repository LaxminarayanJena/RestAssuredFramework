import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DataProviderDynamicJSON {

	@Test(dataProvider="booksData")
	public void addBook(String isbn, String aisle)

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().header("Content-Type", "application/json").
				//body(payload.AddBook()).
				body(payload.AddBook(isbn,aisle)).
				when().
				post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		
		JsonPath js =ReusableMethods.rawToJson(res);
		System.out.println(js.get("ID"));
		
		
	}
	
	@DataProvider(name="booksData")
	public Object[][] getData()
	{
		return new Object[][] {{"aswd","1231"},{"dcd","656"},{"aswa","3444"}};
	}

}
