package anshFramework.tests;


import anshFramework.Reports.Customreport;
import anshFramework.pages.*;
import anshFramework.testsComponent.Baseconfigurationclass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Submitorder extends Baseconfigurationclass{

	WebDriver driver;
	ExtentTest logger;
	//String useremail="test@anshul.mtp";
	//String correctPassword="987654@Mtp";
	//String productName="ADIDAS ORIGINAL";
	Loginpage loginpage;
	String country="French Polynesia";
	
	@Test
	public void loginTest() throws IOException {
		logger=cr.createTest("Login App","Login into application");
		loginpage = launchApplication(logger);
		driver =loginpage.login(useremail, correctPassword);
	}
	
	@Test (dependsOnMethods="loginTest")
	public void addToCart() throws IOException {
		logger=cr.createTest("Choose Product - "+productName+" & Add to cart","Try to find the product and adding them in cart");
		Productpage productpage = new Productpage(driver,logger);
		String productAddedMsg=productpage.addTocart(productName);
		Assert.assertEquals(productAddedMsg,"Product Added To Cart");
		logger.pass("Product is added");

		
	}
	
	@Test (dependsOnMethods="addToCart")
	public void verifyProductInCart() throws IOException {
		logger=cr.createTest("Verify Product - "+productName+" present in cart","check the product added to cart is actually added in cart");
		Cartpage cartpage=new Cartpage(driver,logger);
		Boolean isPresent=cartpage.verifyItemPresent(productName);
		Assert.assertTrue(isPresent);
		logger.pass("Product is present");

	}
	
	@Test (dependsOnMethods="verifyProductInCart")
	public void proceedToCheckout() throws Exception {
		logger=cr.createTest("Proceed To Checkout","Proceed to buy that product");
		Cartpage cartpage=new Cartpage(driver,logger);
		cartpage.clickCheckout();
		
		Proceedpaymentpage proceedPage= new Proceedpaymentpage(driver,logger);
	
		String thanksMsg=proceedPage.proceedToCheckout(country);
		Assert.assertEquals(thanksMsg,"THANKYOU FOR THE ORDER.");
		logger.pass("Order Placed successfully!!!");

	}
	
	
}
