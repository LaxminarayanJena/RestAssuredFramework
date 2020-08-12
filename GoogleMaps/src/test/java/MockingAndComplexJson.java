import files.payload;
import io.restassured.path.json.JsonPath;

public class MockingAndComplexJson {

	public static void main(String[] args) {
		JsonPath js= new JsonPath(payload.CoursePrice());//we are mocking
		
		//1)print no of courses returned by api
		int count= js.getInt("courses.size()");
		System.out.println(count);
		
		//2.print purchase amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//3.print title of first course
		String FirstCoursetitle=js.get("courses[0].title");
		System.out.println(FirstCoursetitle);
		
		//print all courses title and respective prices
		
		for(int i=0;i<count;i++)
		{
			System.out.println(js.get("courses["+i+"].title"));
			System.out.println(js.get("courses["+i+"].price"));
		}
		
		
		//print no of copies sold by selenium course
		for(int i=0;i<count;i++)
		{
			String courseTitles =js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("selenium"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		

	}

}
