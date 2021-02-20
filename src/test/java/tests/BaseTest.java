package tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.BeforeClass;



public class BaseTest {
	
    protected RequestSpecification baseRequest;
    protected String baseUrl = "https://api-coffee-testing.herokuapp.com/";
    protected ResponseSpecification headerSpec;

    @BeforeClass
    public void Setup() {
    	//Configurar la url base de forma global
    	RestAssured.baseURI=baseUrl;
        
    }
    
}
