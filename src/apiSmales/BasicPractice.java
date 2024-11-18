package apiSmales;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import apipractices.ReusabulMethods;
import files.PlayLoad;

public class BasicPractice {

	@Test
	public void getHttpPost() {

//		 given ================> all input details
//		 when  ================> Submit the api -- resource and http methods under when Annotation
//		 Then  ================> validate the response

		int statuscode = 200;
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PlayLoad.Addplace()).when().post("maps/api/place/add/json").then().assertThat()
				.statusCode(statuscode).header("Server", "Apache/2.4.52 (Ubuntu)").body("scope", equalTo("APP"))
				.header("Connection", "Keep-Alive").header("Content-Length", "194").extract().response().asString();

		// Add Place ===>Update [lace with new Address ==> get place to validate if new
		// Address is present in response
		
		System.out.println("\n=========================");
		
		System.out.print("=========="+response);
		JsonPath jp = new JsonPath(response);// it used for parsing Json
		System.out.println(jp);
		String placeid = jp.get("place_id");
		System.out.println("Json place id is :" + placeid);

		//Update Api]
	
	   String newAdderss = "Winter wlak in Hyderbad";
	   String newlanguage = "French-IN"
	   		+ "";
		 String str = given().log().all().queryParam("key", "qaclick123").header("Connection","Connection")
				  .body("{\r\n"
				  		+ "\"place_id\":\""+placeid+"\",\r\n"
				  		+ "\"address\":\""+newAdderss+"\",\r\n"
				  		+"\"language\":\""+newlanguage+"\",\r\n"
				  		+ "\"key\":\"qaclick123\"\r\n"
				  		+ "}")
				   .when().put("/maps/api/place/update/json")
				   .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
				  System.out.println("========================"+str);
				  
				  
	  //Get place Api
		
				 String placeidResponce =  given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Connection","Connection").when()
				  .get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
				 
				 System.out.println(placeidResponce);
				 
				 //Address validation
				 
				JsonPath j =  ReusabulMethods.rawToJson(placeidResponce);
				String actualAddress = j.getString("address");
				System.out.println("Actual Address Is :"+actualAddress);
				Assert.assertEquals(actualAddress, newAdderss);
				
				String actualLanguage = j.getString("language");
				System.out.println("Actual Language Is :"+actualLanguage);
				Assert.assertEquals(actualLanguage, newlanguage);
	}

}
