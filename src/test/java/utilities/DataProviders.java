package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		//taking xl file from testData
		String path =".\\testData\\Test_data.xlsx";
		
		//creating an object for XlUtility
		ExcelUtility xlutil = new ExcelUtility(path);
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		//create for two dimension array which can store data 
		String logindata [] [] = new String [totalrows][totalcols];
		
		//read the data from xl string in two deminsional array
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				// 1, 0
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i,j);
				
			}
		}
		
		//
		
		 return logindata;	
	}
 
}
