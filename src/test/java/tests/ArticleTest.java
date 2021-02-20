package tests;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import helpers.DataHelper;
import models.Article;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;



public class ArticleTest extends BaseTest{

    @Test
    public void Test_Create_Article() {
    	
    	Article article = new Article(DataHelper.randomString(5),DataHelper.randomString(8));
    	
        given()
        .spec(RequestSpecs.generateToken())
        .body(article)
        .post("/v1/article")
        .then()
        .statusCode(200)
        .spec(ResponseSpecs.defaultSpec());
                
    }
    
    @Test
    public void Test_Create_Article_Fail() {
    	
    	Article article = new Article(DataHelper.randomString(5),DataHelper.randomString(8));
    	
    	given()
        .spec(RequestSpecs.generateFakeToken())
        .body(article)
        .post("/v1/article")
        .then()
        .statusCode(401)
        .spec(ResponseSpecs.defaultSpec());
                
    }
    

	
	
}
