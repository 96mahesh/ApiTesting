package apipractices;

import io.restassured.path.json.JsonPath;

public class ReusabulMethods {

	public static JsonPath rawToJson(String responce) {
		JsonPath j = new JsonPath(responce);
		return j;
	}
}
