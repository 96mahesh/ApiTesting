package apiSmales;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DetaProviderclass {

	//Parameterized the Api Tests with multipuldata sets
	//Test ng Data Provider Concepts
	
	@DataProvider
	public Object[][] getData() {
		
		Object deta[][] = new Object[][] {{"ojfwty","9363"},
			                            {"yetsgd","7842"},
		                                {"yetsgd","7842"}};
		                                
		  return deta;
			                                             
	}
		
}
