package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;






public class TC003_LoginDDT extends BaseClass {

	//getting data provider from different class
	@Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class,groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) {
		logger.info("************Statting TC_003_loginDDT******");
		
	try {
			
		
		// Homepage
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		// LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();

		// MyAccount

		MyAccount macc = new MyAccount(driver);
		boolean targetPage = macc.isMyAccountPageExists();

		/*Data is valid - login success - test pass logout 
		 Data is valid -- login failed - test fail
		
		Data is invalid -login success - test fail-logout
		Data is invalid--login failed -test pass
		*/
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		  }
		} 
	catch (Exception e) {
			Assert.fail();
		}
		logger.info("************Finished TC_003_loginDDT******");	
	}

}
