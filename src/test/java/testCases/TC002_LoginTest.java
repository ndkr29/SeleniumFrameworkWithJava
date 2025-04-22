package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","master" })
	public void verify_login() {
		logger.info("************Statting TC_002_logintest Case******");
		try {
		//Homeage
		Homepage hp =new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(p.getProperty("Email"));
		lp.setPassword(p.getProperty("Password"));
		lp.clickLogin();
		
		//MyAccount
		Thread.sleep(3000);
		MyAccount macc =new MyAccount(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
	
			
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("************Finished TC_002_logintest Case******");
		
	}

}
