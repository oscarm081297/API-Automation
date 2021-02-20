package helpers;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.User;
import specifications.RequestSpecs;

public class DataHelper {

	public static String randomEmail() {

		return randomString(4) + "@gmail.com";
	}

	public static String randomString(int size) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < size) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public static User getUser() {
		return new User("0C7O@gmail.com", "password", "NewUser");
	}

	public static String get_post_id() {

		Response response = given().spec(RequestSpecs.generateToken()).get("v1/posts");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<String> jsonString = jsonPathEvaluator.get("results");
		
		JSONArray array = new JSONArray(jsonString);

		JSONObject data = array.getJSONObject(0);
		
		array = data.getJSONArray("data");
		List<String> postIds = new ArrayList<String>();
		
		 Iterator<Object> iterator = array.iterator();

	        while (iterator.hasNext()) {
	            JSONObject jsonObject = (JSONObject) iterator.next();

	            for (String key : jsonObject.keySet()) {
	            //	postIds.add((String) jsonObject.get(key));
	            	 if(key.equals("id")) {
	            		 postIds.add(jsonObject.get(key).toString());
	            	 }
	               //System.out.println(key + ":" + jsonObject.get(key));
	            }
	        }
	        Random randomGenerator = new Random();
	        int index = randomGenerator.nextInt(postIds.size());
	        return postIds.get(index);

	}
}
