package deserialization;

import java.util.List;

import io.restassured.RestAssured;

public class DeserializationTest {

	public static void main(String[] args) {
	
		RestAssured.baseURI="https://reqres.in/api/users?page=2";
		
		//ListUsersPojo pojo = new ListUsersPojo();
		
		ListUsersPojo pojo=RestAssured.given().when().get().as(ListUsersPojo.class);
		
		System.out.print(pojo.toString());
		
		System.out.println("----------");
		System.out.println("List" + pojo.getData());
		
		List<DataPojo> list= pojo.getData();
		for(int i=0; i<list.size();i++)
		{
			System.out.println("This is my " + i + "element" + list.get(i));
		}
		}
		
		
		

	}

