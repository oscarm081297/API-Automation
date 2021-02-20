package specifications;

import helpers.RequestHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static RequestSpecification generateToken() {
    	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
    	String token = RequestHelper.getUserToken();
    	//System.out.println(token);
    	requestBuilder.addHeader("Authorization", "Bearer "+token);
    	return requestBuilder.build();
    }
    
    public static RequestSpecification generateFakeToken() {
    	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
    	requestBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInRsdfsd5cCI6IkpXVCJ9.eyJhY2Nlc3NfdXVpZCI6IjZjZDc4MDIyLWI2OTgtNDlmOS1hMjZhLWY2OTU0OGVlN2MwOSIsImF1dGhvcml6ZWQiOnRydWUsImV4cCI6MTYxMzAxMzc5MSwidXNlcl9pZCI6MTczfQ.Q_lCf32m5_PeSh2wcNIaoAgSAmhfy9B9SmLNWWeGC68");
    	return requestBuilder.build();
    }
    
}
