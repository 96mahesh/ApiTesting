package apiSmales;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import apipractices.ReusabulMethods;
import files.PlayLoad;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class Dynamicjson {

//	@Test
//	public void addBookType() {
//		RestAssured.baseURI="http://216.10.245.166";
//		given().log().all().header("Content-Type","application/json").
//		body(PlayLoad.Addbook()).
//		when().post("Library/Addbook.php").
//		then().log().all().assertThat().statusCode(200);
//	}
	
	//Dynamically build json payload with external data inputs
	@Test(dataProvider="BooksData")
	
	
	public void addBook(String x , String y ) {
	    RestAssured.baseURI="http://216.10.245.166";
	    String response = given().log().all().header("Content-Type","application/json").
		body(PlayLoad.Addbook("bczd","2270")).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).body("Msg", equalTo("Book Already Exists")).
		body("ID",equalTo("bczd2270")).extract().response().asString();
		
		JsonPath j =  ReusabulMethods.rawToJson(response);
		String actualId = j.get("ID");
		System.out.println("Actual Address Is :"+actualId);
		
		String actualMsg = j.get("Msg");
		System.out.println("Actual Address Is :"+actualMsg);
		//Delete book	
	}
	
	//Parameterized the Api Tests with multipuldata sets
   //Test ng Data Provider Concepts
	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		
		return new Object[][] {{"ojfwty","9363"},
			                              {"cwetee","4823"},
		                                  {"okmfet","533"}};
		                                	                                             
	}
	
	
}
