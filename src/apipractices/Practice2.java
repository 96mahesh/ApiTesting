package apipractices;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.LibraruTest;

public class Practice2 {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
	    given().log().all().header("Content-Type","application/json").body(LibraruTest.AddMethod()).when().post("/Library/Addbook.php").then().log().all().assertThat()
	            .statusCode(200).body("Msg", equalTo("Book Already Exists")).header("Server", "Apache").body("ID", equalTo("bcd227"));
	}
}
