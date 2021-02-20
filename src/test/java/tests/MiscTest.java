package tests;
import org.testng.annotations.Test;
import org.hamcrest.core.StringContains;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class MiscTest extends BaseTest {


    @Test
    public void Test_Ping() {
    	given()

                .when()
                .get("ping")
                .then()
                .header("Content-Length", equalTo("50"))
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .and()
                .body("response", equalTo("pong"))
                .and()
                .statusCode(200);
    }

    @Test
    public void Test_HomePage() {
    	given()
                .get()
                .then()
                .body(StringContains.containsString("Gin Boilerplate"))
                .and()
                .statusCode(200);
    }
}