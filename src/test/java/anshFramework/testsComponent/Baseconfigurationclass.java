package anshFramework.testsComponent;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import anshFramework.Reports.Customreport;
import anshFramework.pages.Loginpage;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;


public class Baseconfigurationclass {

	WebDriver driver; 
	protected static Customreport cr;
	protected String useremail;
	protected String correctPassword;
	protected String productName;
	String url ="https://rahulshettyacademy.com/client";

	@BeforeSuite(alwaysRun=true)
	public void initialize() {
		cr=new Customreport();
	}
	
	@BeforeClass
	public WebDriver initializeBrowser() throws IOException {
		Properties prop = new Properties();
		String path = System.getProperty("user.dir")+"//src//test//java//anshFramework//dataFiles//globalvariable.properties";
		FileInputStream fis=new FileInputStream(path);
		
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
		System.out.println(browserName);
		useremail=prop.getProperty("useremail");
		correctPassword=prop.getProperty("correctPassword");
		productName=prop.getProperty("productName");
		
		
		if(browserName.contains("chrome")) {
			ChromeOptions op=new ChromeOptions();
			if(browserName.contains("headless")) {
				op.addArguments("headless");

			}
			driver =new ChromeDriver(op);	
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.contains("firefox")) {
			driver =new FirefoxDriver();	
		}
		else if(browserName.contains("edge")) {
			driver =new EdgeDriver();	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	
	public Loginpage launchApplication(ExtentTest logger) {
		logger.log(Status.INFO,"Launcing Url");
		Loginpage loginpage=new Loginpage(driver,logger);
		loginpage.goToLoginPage(url);
		logger.log(Status.INFO,"Url loaded");
		return loginpage;
	}
	
	@AfterClass
	public void quitDriver() {
		driver.quit();
	}
	
	@AfterSuite(alwaysRun=true)
	public void flush() {
		cr.flush();
	}
}
