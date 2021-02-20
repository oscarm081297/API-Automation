package tests;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import models.User;
import specifications.ResponseSpecs;
import helpers.DataHelper;

import static org.hamcrest.core.IsEqual.equalTo;


public class UserTest extends BaseTest {
	
	User user;

    @Test
    public void Test_CreateUser_AlreadyExist() {
        user = new User("pablo@test.com", "pablo", "pablotest");
        given()
                .body(user)
                .when()
                .post("v1/user/" + "register")
                .then()
                .body("message", equalTo("User already exists"))
                .and()
                .statusCode(406)
                .spec(ResponseSpecs.defaultSpec());

    }
    
    @Test(priority=1)
    public void Test_Create_User_Successful() {
    	
    	  user = new User(DataHelper.randomEmail(), "password", "NewUser2");
    	  System.out.println("Email-> "+user.getEmail());
    	  
    	  given()
                  .body(user)
                  .when()
                  .post("v1/user/" + "register")
                  .then()
                  .body("message", equalTo("Successfully registered"))
                  .and()
                  .statusCode(200)
                  .spec(ResponseSpecs.defaultSpec());
    	
    }
    
    @Test(priority=2)
    public void Test_Login_Successful() {
    	
    	given()
        .body(user)
        .when()
        .post("v1/user/" + "login")
        .then()
        .body("message", equalTo("User signed in"))
        .and()
        .statusCode(200)
        .spec(ResponseSpecs.defaultSpec());
    }
    

}