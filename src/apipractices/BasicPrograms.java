package apipractices;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import files.PlayLoad;

public class BasicPrograms {
  public static void main(String[] args) {
	
	  //validate if add place Api is working as Expected
	  
	  //key words or annitations of api given when then
	  
	  RestAssured.baseURI="https://rahulshettyacademy.com"; 
	  String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	  .body(PlayLoad.Addplace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)")
	        .body("scope",equalTo ("APP")).header("Connection","Keep-Alive").header("Content-Length", "194").extract().response().asString();
	  //Api annitations packegs => import static io.restassured.RestAssured.*;
	 
	  
	  
	  //Add place --> update place with new Address --> place go to validate if new Address is present in response
	  
	  System.out.println(responce);
	  JsonPath js = new JsonPath(responce);//for parsing json
	  String placeid = js.getString("place_id");
	  System.err.println("json place id = :" + placeid);
//	  String scopeid = js.getString("scope");
//	  System.err.println("json scopeid id = :" + scopeid);
	  
	  
	  //Update place Api
	  
	  String newaddress = "Winter walk in Australiya";
	  
	  String str = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	  .body("{\r\n"
	  		+ "\"place_id\":\""+placeid+"\",\r\n"
	  		+ "\"address\":\""+newaddress+"\",\r\n"
	  		+ "\"key\":\"qaclick123\"\r\n"
	  		+ "}")
	   .when().put("/maps/api/place/update/json")
	   .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
	  System.out.println("====================="+str);
	  
	  //Get place Api
	  
	  String getPlaceResonce = given().log().all().queryParam("key", "qaclick123")
	  .queryParam("place_id", placeid).when().get("/maps/api/place/get/json")
	  .then().log().all().assertThat().statusCode(200).extract().response().asString();
	  
	  
	  JsonPath j = ReusabulMethods.rawToJson(getPlaceResonce);
	  String actualAddress = j.getString("address");
	  System.out.println("Actual Address is :"+actualAddress);
	  
	  Assert.assertEquals(actualAddress, newaddress);
	  
	  
	  //Arrays responce code 	  
}
}
