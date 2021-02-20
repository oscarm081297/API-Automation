package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.core.StringContains;
import org.testng.annotations.Test;

import de.svenjacobs.loremipsum.LoremIpsum;

import models.Comment;
import specifications.ResponseSpecs;


public class CommentTest extends BaseTest{
	
	int createdComment;
	private LoremIpsum loremIpsum;
	
	
	
	//Post
	
	@Test
	public void create_comment() {
		System.out.println("Post Comment Positive");
		loremIpsum = new LoremIpsum();
		Comment comment = new Comment(loremIpsum.getWords(5),loremIpsum.getWords(30));
		createdComment = given()
	   	        .auth().basic("testuser", "testpass")
	   	        .body(comment)
	   	        .post("/v1/comment/105")
	   	        .then()
	   	        .statusCode(200)
	   	        .and()
	   	        .body("message", equalTo("Comment created"))
	   	        .spec(ResponseSpecs.defaultSpec())
	   	        .extract().path("id");
	}
	
	
	@Test
	public void create_comment_negative() {
		System.out.println("Post Comment Negative");
		loremIpsum = new LoremIpsum();
		Comment comment = new Comment(loremIpsum.getWords(5),loremIpsum.getWords(30));
		given()
	   	        .auth().basic("testuser", "testpass")
	   	        .body(comment)
	   	        .post("/v1/comment/01000")
	   	        .then()
	   	        .statusCode(406)
	   	        .and()
	   	        .body("message", equalTo("Comment could not be created"))
	   	        .spec(ResponseSpecs.defaultSpec());
	}
	
	//Get
	@Test
	public void get_allComments() {
		System.out.println("Get All Comments Positive");
    	given()
    	.auth().basic("testuser", "testpass")
        .get("v1/comments/105")
        .then()
        .body(StringContains.containsString("results"))
        .and()
        .statusCode(200);
	}
	
	@Test
	public void get_allComments_Negative() {
		System.out.println("Get All Comments Negative");
    	given()
    	.auth().basic("testuser", "LoremImpsum")
        .get("v1/comments/105")
        .then()
        .body("message", equalTo("Please login first"))
        .and()
        .statusCode(401);
	}
	
	@Test
	public void get_Comment() {
		System.out.println("Get All Comments Positive");
    	given()
    	.auth().basic("testuser", "testpass")
        .get("v1/comment/105/110")
        .then()
        .body(StringContains.containsString("data"))
        .and()
        .statusCode(200);
	}
	
	@Test
	public void get_Comment_Negative() {
		System.out.println("Get All Comments Positive");
    	given()
    	.auth().basic("testuser", "testpass")
        .get("v1/comment/1053/1103")
        .then()
        .body(StringContains.containsString("Comment not found"))
        .and()
        .statusCode(404);
	}
	
	//Put
	
	
	
	

}
