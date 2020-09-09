package serialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class serializeTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
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
		
		
		Response res=given().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response();
		
		System.out.println(res.jsonPath().prettify());
		System.out.println("---------------");
		System.out.println(res.getBody().asString());
		
		//String token=res.jsonPath().get("access_token");
		//System.out.println(token);
		
		
		
	

	}

}
