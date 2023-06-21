package anshFramework.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import anshFramework.pages.Loginpage;
import anshFramework.pages.Orderpage;
import anshFramework.testsComponent.Baseconfigurationclass;

public class Verifyorder extends Baseconfigurationclass {
	WebDriver driver;
	Loginpage loginpage;
	ExtentTest logger;
	//String useremail="test@anshul.mtp";
	//String correctPassword="987654@Mtp";
	//String productName="ADIDAS ORIGINAL";
	
	@Test (priority=-1)
	public void login() throws IOException {
		logger=cr.createTest("Login to App", "Login Application steps");
		loginpage=launchApplication(logger);
		driver=loginpage.login(useremail, correctPassword);
		logger.pass("Login Successfully!!!");
	}
	
	@Test
	public void verifyOrder() throws IOException {
		logger=cr.createTest("Verify order", "Placed order is present in order section");
		Orderpage orderPage=new Orderpage(driver,logger);
		String[] productTitle= orderPage.verifyOrder(productName);
		Assert.assertEquals(productTitle[0], productName.toLowerCase());
		Assert.assertEquals(productTitle[1], "Thank you for Shopping With Us");
		Assert.assertEquals(productTitle[2], productName.toLowerCase());
		logger.pass("Placed order Found in order section");


	}
	
	
	
	
}
