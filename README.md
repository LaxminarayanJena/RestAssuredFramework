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
books[1].isbn </br>

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
