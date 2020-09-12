package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import pojo.LocationPojo;

public class AddPlace {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;

	@Given("Add Place Payload")
	public void add_place_payload() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlacePojo p = new AddPlacePojo();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");

		LocationPojo l = new LocationPojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(req).body(p);

	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {

		response=res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
	}

	@Then("the API call is sucess with status code {int}")
	public void the_api_call_is_sucess_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {

		String resp=response.asString();
		JsonPath js= new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(),ExpectedValue);
	}


}
