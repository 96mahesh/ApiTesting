package apipractices;

import files.PlayLoad;
import io.restassured.path.json.JsonPath;

public class ComlexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(PlayLoad.CoursePrice());
		
		//Print No of courses returned by API
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		//Print Title of the first course  using methos getString and get(get like a String method)
		
		String titleFrist = js.get("courses[0].title");
		System.out.println(titleFrist);
		
		String titleSecound = js.get("courses[1].title");
		System.out.println(titleSecound);
				
		String titleThroad = js.get("courses[2].title");
		System.out.println(titleThroad);
		
		//Print All course titles and their respective Prices
		
		
		
		
		
		
	}
	
}
