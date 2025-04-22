package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {
	
	public Homepage (WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//li[@class='list-inline-item']//a[@class='dropdown-toggle']")
	WebElement lnkMYAccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Login']")
	WebElement lnkLogin;
	
	
	
	public void clickMyAccount() {
		lnkMYAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}

}	
