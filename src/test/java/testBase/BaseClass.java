package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {

	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;

	@BeforeClass(groups={"regression","master","sanity","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		logger =LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equals("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
		    //os
			if(os.equalsIgnoreCase("Windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("no matching os");
				return;
			}
			//browser
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid browser....");
				return;
			}
			
		   driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_env").equals("local"))
		{
			switch (br.toLowerCase()) {
			case "chrome":
				driver =new ChromeDriver();
				break;
			case "edge":
				driver =new EdgeDriver();
				break;
			case "firefox":
				driver =new FirefoxDriver();
				break;

			default:
				System.out.println("Invalid browser....");
				return;
			}
		}
		
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups={"regression","master","sanity"})
	public void tearDown() throws InterruptedException {
		
		driver.quit();
	}

	// Randomly generated the string
	public String randomeString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);

		return generatedstring;
	}

	// Randomly generated the number
	public String randomeNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(5);

		return generatednumber;
	}

	// Randomly generated the AlphaNumberic
	public String randomeAlphaNumberic()

	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		String generatednumber = RandomStringUtils.randomNumeric(5);

		return (generatedstring + "@" + generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}


}
