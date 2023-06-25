package api.endpoints;
 // Created to perform CRUD operations for user API

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {             //using properties class
	
	
	
	//method created for getting URLs from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties files
		return routes;
		
	}	
	
	
	  // Create User
	
	public static Response createUser(User Payload){
		
		String post_url = getURL().getString("post_url");
		
	Response response = given()
		                    .contentType(ContentType.JSON)
		                    .accept(ContentType.JSON)
		                    .body(Payload)
		                .when()
		                    .post(post_url);
	
	return response;
	}
	
	public static Response readUser(String userName){
		
		String get_url = getURL().getString("get_url");
		
		
		Response response = given()
			                    .pathParam("username",userName)
			                .when()
			                    .get(get_url);
		
		return response;
		}
	
public static Response updateUser(String userName,User Payload){
	
	String update_url = getURL().getString("update_url");	
		
		Response response = given()
				                  .contentType(ContentType.JSON)
                                  .accept(ContentType.JSON)
			                      .pathParam("username",userName)
			                      .body(Payload)
			                .when()
			                    .put(update_url);
		
		return response;
		}

public static Response deleteUser(String userName){
	
	String delete_url = getURL().getString("delete_url");	
	
	Response response = given()
		                    .pathParam("username",userName)
		                .when()
		                    .delete(delete_url);
	
	return response;
	}
}
