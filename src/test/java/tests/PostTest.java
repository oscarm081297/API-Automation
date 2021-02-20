package tests;

import org.testng.annotations.Test;

import helpers.DataHelper;
import models.Post;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.core.StringContains;

public class PostTest extends BaseTest{

	int createdPost;
	
	//Post tests
	
	@Test(priority=0)
	public void create_post() {
		Post post = new Post(DataHelper.randomString(4),DataHelper.randomString(20));
		System.out.println("Post Positive");
	       createdPost = given()
	        .spec(RequestSpecs.generateToken())
	        .body(post)
	        .post("/v1/post")
	        .then()
	        .statusCode(200)
	        .and()
	        .body("message", equalTo("Post created"))
	        .spec(ResponseSpecs.defaultSpec())
	        .extract().path("id");
	}
	
	@Test
	public void create_post_with_fake_token() {
		Post post = new Post(DataHelper.randomString(4),DataHelper.randomString(12));
		System.out.println("Post Negative");
		 given()
	        .spec(RequestSpecs.generateFakeToken())
	        .body(post)
	        .post("/v1/post")
	        .then()
	        .statusCode(401)
	        .and()
	        .body("message", equalTo("Please login first"))
	        .and()
	        .spec(ResponseSpecs.defaultSpec());
	}
	
	//Get tests
	
	@Test
	public void get_allPosts() {
		System.out.println("Get All Positive");
    	given()
    	 .spec(RequestSpecs.generateToken())
        .get("v1/posts")
        .then()
        .body(StringContains.containsString("results"))
        .and()
        .statusCode(200);
	}
	
	@Test
	public void get_allPosts_Negative() {
		System.out.println("Get All Negative");
    	given()
    	 .spec(RequestSpecs.generateFakeToken())
        .get("v1/posts")
        .then()
        .body("message", equalTo("Please login first"))
        .and()
        .statusCode(401);
	}
	
	@Test
	public void get_post() {
		System.out.println("Get Positive");
		String postId = DataHelper.get_post_id();
    	given()
   	 	.spec(RequestSpecs.generateToken())
       .get("v1/post/"+postId)
       .then()
       .body(StringContains.containsString("title"))
       .and()
       .statusCode(200);
	}
	
	@Test
	public void get_post_negative() {
		System.out.println("Get Negative");
    	given()
   	 	.spec(RequestSpecs.generateToken())
       .get("v1/post/009")
       .then()
       .body("Message", equalTo("Post not found"))
       .and()
       .statusCode(404);
	}
	
	
	
	//Put tests
	
	@Test
	public void update_post() {
		System.out.println("Put Positive");
		Post post = new Post(DataHelper.randomString(4),DataHelper.randomString(12));
		String postId = DataHelper.get_post_id();
    	given()
   	 	.spec(RequestSpecs.generateToken())
   	 	.body(post)
   	 	.put("v1/post/"+postId)
   	 	.then()
   	 	.statusCode(200)
   	 	.and()
   	 	.spec(ResponseSpecs.defaultSpec());
	}
	
	@Test
	public void update_post_invalidId() {
		System.out.println("Put Negative");
		Post post = new Post(DataHelper.randomString(4),DataHelper.randomString(12));
    	given()
   	 	.spec(RequestSpecs.generateToken())
   	 	.body(post)
   	 	.put("v1/post/0000")
   	 	.then()
   	 	.statusCode(406)
   	 	.and()
   	 	.body("message", equalTo("Post could not be updated"))
   	 	.and()
   	 	.spec(ResponseSpecs.defaultSpec());
	}
	
	//Delete
	
	@Test(priority=1)
	public void delete_post() {
		System.out.println("Delete Positive");
		given()
   	 	.spec(RequestSpecs.generateToken())
   	 	.delete("v1/post/"+createdPost)
   	 	.then()
   	 	.statusCode(200)
   	 	.and()
   	 	.body("message", equalTo("Post deleted"))
   	 	.spec(ResponseSpecs.defaultSpec());
	}
	
	
	@Test()
	public void delete_post_negative() {
		System.out.println("Delete Negative");
		given()
   	 	.spec(RequestSpecs.generateToken())
   	 	.delete("v1/post/000")
   	 	.then()
   	 	.statusCode(406)
   	 	.and()
   	 	.body("error", equalTo("Post not found"))
   	 	.spec(ResponseSpecs.defaultSpec());
	}


	
}
