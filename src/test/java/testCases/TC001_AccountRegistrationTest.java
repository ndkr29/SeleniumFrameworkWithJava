package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	

	@Test(groups={"regression","master"})
	public void verify_account_registration(){
		try {
		logger.info("************ Starting TC001_AccountRegistrationTest ************");
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("clicked on Register Link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details....");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastname(randomeString().toUpperCase());
		//Randomly generated the email 
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setPassword(randomeAlphaNumberic() );
		
		regpage.setPraivacyPolicy();
		regpage.clickContinue();

		logger.info("Validting expected message... ");
		String confirm= regpage.getconfirmatiocMsg();
		
		
		if(confirm.equalsIgnoreCase("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed.... ");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
	
		
	
			
		} catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("************ Finished TC001_AccountRegistrationTest ************");
	}
	

	
}
