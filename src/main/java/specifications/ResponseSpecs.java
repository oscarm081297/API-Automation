package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {
	
	public static ResponseSpecification defaultSpec() {
        //Esto se hace para setear propiedades en comun para todos los request
        //Por ejemplo, siempre tienen que responder un content type json
        //Para no agregar esa linea en cada test, se hace de la siguiente manera
        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
        return responseBuilder.build();
	}

}
