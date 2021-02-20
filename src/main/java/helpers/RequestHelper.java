package helpers;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RequestHelper {
	
	public static String getUserToken() {
		Response response = given()
		.body(DataHelper.getUser())
		.post("/v1/user/login");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator.get("token.access_token");
	}
	

}
