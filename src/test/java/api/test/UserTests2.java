package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	
	Faker faker ;                 // user is  generating own data
	User userpayload;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setUsername(faker.name().username());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
			
	}
  @Test(priority=1)
  
  public void testPostUser() {
	  
	  Response response = UserEndPoints2.createUser(userpayload);
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(),200);
  }
  
  @Test(priority=2)
  public void testGetUserByName() {
	  
	  Response response= UserEndPoints2.readUser(this.userpayload.getUsername());
	  response.then().log().all(); 
	  Assert.assertEquals(response.getStatusCode(),200);
	    
  }
	
  @Test(priority=3)
  public void testUpdateUserByName() {
	  
	  //update data using payload
	    userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
	  
	  Response response= UserEndPoints2.updateUser(this.userpayload.getUsername(),userpayload);
	  response.then().log().body(); 
	  Assert.assertEquals(response.getStatusCode(),200);
	  
	  //checking data after update
	  
	  Response responseAfterUpdate = UserEndPoints.createUser(userpayload);
	  Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	    
  }
  
  @Test(priority=4)
  public void testDeleteUserByName() {
	  
	  
	  Response response= UserEndPoints2.deleteUser(this.userpayload.getUsername());
	  Assert.assertEquals(response.getStatusCode(),200);
	  
	 
	    
  }
	  
  
}
