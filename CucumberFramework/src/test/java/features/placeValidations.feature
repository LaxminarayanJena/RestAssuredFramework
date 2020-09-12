Feature: Validating Place API's
Scenario Outline: Verify if Place is being sucessfully added using AddPlaceAPI

Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call is sucess with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

Examples:

|name|language|address|
|AAhouse|english|puri|
|BBhouse|french|cuttack |









