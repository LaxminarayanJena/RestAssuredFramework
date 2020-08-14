import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExternalJsonFileDynamicJSON {

	@Test
	public void addBook() throws IOException
	{
		//change json data before running otherwise it will fail as book already exist
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().header("Content-Type", "application/json").
				//body(payload.AddBook()).
				//body(payload.AddBook("aszsa","3142")).
				//body(GenerateStringFromResource("C:\\JENA\\RestAssured\\LibraryAPIDynamicJSON\\src\\test\\java\\files\\AddBookDetails.json")).
				body(GenerateStringFromResource(System.getProperty("user.dir")+"/src//test//java//files//AddBookDetails.json")).
				
				when().
				post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();
		
		JsonPath js =ReusableMethods.rawToJson(res);
		System.out.println(js.get("ID"));
		
		
	}

	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}
}
