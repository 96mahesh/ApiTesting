package apiSmales;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PlayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	int sum = 0;
	int courcecount;	
		JsonPath js = new JsonPath(PlayLoad.CoursePrice());
		
		//Print No of courses returned by API
		@Test
		public void getCoursesSize() {
	    courcecount = js.getInt("courses.size()");
		System.out.println("Course count is := " + courcecount);
		}
		//.Print Purchase Amount
		@Test
		public void getPurchaseAmount() {
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchaseAmount : = " + amount);
		}
		//Print Title of the first course
		@Test
		public void getFristCourseTitle() {
		String fristtitle = js.getString("courses.title[0]");
		System.out.println("Frist course Title Is : = " + fristtitle);
		}
		
		@Test
		public void getSecoundCourseTitle() {
		String secoundtitle = js.getString("courses[1].title");
		System.out.println("Secound course Title Is : = " + secoundtitle);
		}
		
		@Test
		public void getThroadCourseTitle() {
		String throadtitle = js.getString("courses.title[2]");
		System.out.println("Throad course Title Is : = " + throadtitle);
		}
		//Print All course titles and their respective Prices
		@Test
		public void getTotalCourseTitle() {
		String TotalCourse = js.getString("courses.title");
		System.out.println("Total course Title Is : = " + TotalCourse);
		System.err.println("=================");
		}
		@Test
		public void getFristCourseTitleAndPrice() {
		for(int i=0;i<courcecount;i++) {
			String allcourse = js.get("courses["+i+"].title");
			String priceamount = js.get("courses["+i+"].price").toString();
			System.out.println(allcourse+" "+priceamount);
		}
		}
		
		//Print no of copies sold by RPA Course
		@Test
		public void getRFACoursecopies() {
		for(int i=0;i<courcecount;i++) {
			String allcourse = js.get("courses["+i+"].title");
			if(allcourse.equals("RPA")) {
				int copiescount = js.get("courses["+i+"].copies");
				System.err.println("copiescount is : + " + copiescount);
				break;
			}
		}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		@Test
		public void getSumOfAllCoursePrice() {
		for(int i=0;i<courcecount;i++) {
			int allcourse = js.get("courses["+i+"].price");
			sum = sum+allcourse;	
	}
		System.out.println(sum);
		}
		
		
		
		int s = 0;
		@Test
		public void sumOfCoursePrice() {
		for(int i=0;i<courcecount;i++) {
			int allcourse = js.get("courses["+i+"].price");
			int copiescount = js.get("courses["+i+"].copies");
			int sum = allcourse*copiescount;
			s += sum;
			System.out.println(sum);
	}
		System.out.println(s);
		int amount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(amount, s);
		
		}
}

