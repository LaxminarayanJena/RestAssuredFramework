# RestAssured
Rest-api-automation-testing-rest-assured.The difference between POST and PUT is that PUT requests are idempotent. That is, calling the same PUT request multiple times will always produce the same result. In contrast, calling a POST request repeatedly have side effects of creating the same resource multiple times. </br>

Rest is stateless because each request must contain all of the information necessary to be understood by the server, rather than be dependent on the server remembering prior requests.
</br>
get,put,delete-idempotent (safely repeatable) </br>
post- non idempotent </br>
</br>
 HTTP is unsecured while HTTPS is secured. HTTP sends data over port 80 while HTTPS uses port 443. HTTP operates at application layer, while HTTPS operates at transport layer. No SSL certificates are required for HTTP, with HTTPS it is required that you have an SSL certificate and it is signed by a CA. </br>
 
 JSON Data Types-a string,a number,an object (JSON object),an array a boolean,null </br>
 
 ### Mocking
https://github.com/typicode/json-server </br>
npm install -g json-server </br>
json-server --watch db.json </br>
localhost:3000

 </br>
io rest assured -5.2.0 9th sept -2022
### Status Codes
#### 1x-informational </br>
#### 2x-success 
   200-ok  The request was successful, and the server provides the requested resource. getcall,post call</br>
   201-created  The request has been fulfilled, resulting in the creation of a new resource (/api/users/123456)</br>
    202-accepted 202 Your report is being generated. Please check back later.</br>
#### 3x-redirection </br>
#### 4x-client error</br>
   400-bad request -Missing required field: email</br>
    401-unuthorized -error": "Unauthorized access. Please provide valid credentials.</br>
    402-payment required</br>
    403-forbidden-  restrict access to certain resources or actions based on the client's authorization level.The client may need to contact an administrator or authenticate with different credentials to gain the necessary permissions.</br>
    404-NotFound-mistyped URL, a deleted resource, or a resource that was never created.</br>
    408-status timeout </br?
    415-unsupported media type</br>
    
#### 5x-server error</br>
500- internal server error- such as a database connection failure or a code exception.</br>
502-bad gateway-upstream server that the gateway contacts encounters an error </br>
503-Service unavailaible-server is currently experiencing high traffic or undergoing maintenance</br>

![webservicevsapi](https://user-images.githubusercontent.com/24494133/57190744-b318a080-6f3b-11e9-988a-75337cd47ff5.PNG)
![soap vs rest](https://user-images.githubusercontent.com/24494133/81675213-1224c580-946c-11ea-97be-bae0c3d3f939.PNG)


#### Validation in restassured=  response,status code,header ,responsetime,contentType
```
Assert.assertTrue(response.getTimeIn(TimeUnit.SeECONDS)<=10,"Response Time is not within limit");</br>
then().assertThat().statusCode(200).and().contentType(ContentType.JSON) .and(). </br>
		body("status",equalTo("OK") .and().header("Server","Cloudfare");

import org.hamcrest.core.Is
then().assertThat().body("[PostOffice.Name[0]", Is.is("Electronics City")).log.all()
```

#### cookies
```
Response response = RestAssured.get("https://example.com/api/endpoint");
Cookies cookies = response.getDetailedCookies();
String cookieValue = cookies.getValue("cookieName");
RestAssured.given().cookie("cookieName", "cookieValue").get("https://example.com/api/endpoint");
RestAssured.given().cookie("cookieName", "").get("https://example.com/api/endpoint");


```
#### CODE
```
RestAssured.baseURI = "https://api.example.com";
String requestBody = "{\"name\": \"John\", \"age\": 30}";
String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/users")
                .then().assertThat().body("scope", equalTo("APP"))
                .statusCode(201).extract().response().asString();
    }
System.out.println(response);
JsonPath js = new JsonPath(response);
String placeId = js.getString("place_id");


 File fileToUpload = new File("path_to_your_file.txt");
  given().multiPart("file", fileToUpload)
  .when().post("your_api_endpoint_here")
```
books[0].isbn  starts with 0 index o/p-9781593275846
</br>
books[1].isbn  o/p-9781449331818
</br>
books[?(@.isbn == 9781593275846)]
```
{
  "books": [
    {
      "isbn": "9781593275846",
      "title": "Eloquent JavaScript, Second Edition"
},
    {
      "isbn": "9781449331818",
      "title": "Learning JavaScript Design Patterns"
    }
               
            ]

}
```

```
  JsonPath js = new JsonPath(response);
        List<String> names = js.getList("findAll { it.postId == 2 }.name");
        for (String name : names) {
            System.out.println("Name: " + name);
        }
----
 String firstName = js.getString("find { it.postId == 1 }.name");

```
#### Hard Assertion
```
.then()
.statusCode(200)
.body("user.name", hasItem("RestAPI Automation"))
.body("entities.hashtags[0].text", hasItem("multiple1"))
.body("entities.hashtags[0].size()", equalTo(3))
.body("entities.hashtags[1].size()", lessThan(2));
```
#### Soft Assertion
```
.then()
.statusCode(200)
.body("user.name", hasItem("RestAPI Automation"))
.body("entities.hashtags[0].text", hasItem("multiple1"),
"entities.hashtags[0].size()", equalTo(3),
"entities.hashtags[1].size()", lessThan(2));
```

#### issue in below code
```
what is wrong with this code
import io.restassured.RestAssured; 
import org.junit.Test; 
import static io.restassured.RestAssured.given; 
import static org.hamcrest.Matchers.equalTo; 
public class ApiTestWithError 
{ @Test public void getUserDetails() 
{ RestAssured.baseURI = "https://reqres.in/api"; 
given() .pathParam("id", 1) .when() .get("/users/{id}") .
then() .assertThat() .statusCode(201) .body("data.id", equalTo(2)) .
body("data.first_name", equalTo("Janet")) .body("data.last_name", equalTo("Weaver"))
 }
}
Incorrect Status Code: The expected status code for a successful GET request is typically 200, not 201. Status code 201 is for a successful creation of a resource (POST request).
Incorrect Assertions: The user with id=1 on https://reqres.in/api does not match the expected values for id, first_name, and last_name in your assertions.
```


#### Challenges in API Automation
```
1)Complexity of APIs:
APIs can be complex, especially if they have a large number of endpoints, different request methods, and various authentication mechanisms. Understanding the API documentation and effectively mapping test cases to different API functionalities can be a challenge.

2)Dynamic data: APIs often deal with dynamic data, such as timestamps or generated identifiers. Testing APIs with dynamic data requires careful handling to ensure consistent and accurate test results.

3)Dependency management: APIs may have dependencies on other APIs or external services. Coordinating and managing these dependencies while automating API tests can be challenging, especially when dealing with complex workflows.

4)Versioning and backward compatibility: APIs evolve over time, and new versions are released. Ensuring backward compatibility with older versions while also testing the latest version can be a challenge. Testers need to consider versioning strategies and handle any breaking changes effectively.

5)Security and authentication: APIs often require authentication and authorization mechanisms to protect sensitive data. Implementing and managing authentication mechanisms in automated tests can be complex, especially when dealing with tokens, API keys, or OAuth flows.

6)Test environment setup: Setting up the necessary test environment for API automation can be challenging, especially when dealing with third-party APIs or complex infrastructure requirements. Testers need to ensure they have the right resources, configurations, and access permissions.

7)Test data management: API tests often require specific test data sets for different scenarios. Managing and maintaining test data, as well as ensuring data integrity during test execution, can be a challenge.

8)Error handling and response validation: APIs can produce a wide range of responses and error codes. Validating and handling these responses accurately during automation is crucial for reliable test results.

9)Performance and load testing: APIs need to handle high loads and concurrent requests efficiently. Creating and executing performance and load tests for APIs can be challenging, requiring careful configuration and monitoring.

10)Continuous integration and delivery:  Integrating API tests into a continuous integration and delivery (CI/CD) pipeline can be challenging. Ensuring the automation framework can be seamlessly integrated with the existing CI/CD infrastructure and providing timely feedback on API changes can be complex.
```

