package anshFramework.stepDefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import anshFramework.Reports.Customreport;
import anshFramework.pages.Cartpage;
import anshFramework.pages.Loginpage;
import anshFramework.pages.Proceedpaymentpage;
import anshFramework.pages.Productpage;
import anshFramework.testsComponent.Baseconfigurationclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinition extends Baseconfigurationclass{
	WebDriver driver;
	ExtentTest logger;
	Loginpage loginpage;
	Proceedpaymentpage proceedPage;
	String actualMsg;
	
	@Given("Go to ecommerce website landing page")
	public void go_to_ecommerce_website_landing_page() throws IOException {
		cr=new Customreport();
		driver=initializeBrowser();
		logger=cr.createTest("Login App","Login into application");
		loginpage = launchApplication(logger);
		
	}
	
	@Given ("^user login to application with email (.+) and pass (.+)$")
	public void user_login_to_application(String userEmail,String Password) throws IOException {
		driver =loginpage.login(userEmail, Password);
	}
	
	@When ("^select an product with (.+) and add to cart$")
	public void select_an_product_and_add_to_cart(String productName) throws IOException {
		logger=cr.createTest("Choose Product - "+productName+" & Add to cart","Try to find the product and adding them in cart");
		Productpage productpage = new Productpage(driver,logger);
		String productAddedMsg=productpage.addTocart(productName);
		Assert.assertEquals(productAddedMsg,"Product Added To Cart");
		logger.pass("Product is added");
	}
	@When("^go to cart to proceed further with product (.+)$")
	public void go_to_cart_to_proceed_product(String productName ) throws IOException {
		logger=cr.createTest("Verify Product - "+productName+" present in cart","check the product added to cart is actually added in cart");
		Cartpage cartpage=new Cartpage(driver,logger);
		Boolean isPresent=cartpage.verifyItemPresent(productName);
		Assert.assertTrue(isPresent);
		logger.pass("Product is present");

	}
	
	@When("^proceed to checkout and fill all details with country (.+)$")
	public void proceed_to_checkout_and_fill_details(String country) throws Exception {
		logger=cr.createTest("Proceed To Checkout","Proceed to buy that product");
		Cartpage cartpage=new Cartpage(driver,logger);
		cartpage.clickCheckout();
		proceedPage= new Proceedpaymentpage(driver,logger);
		actualMsg=proceedPage.proceedToCheckout(country);
		
	}
	
	@Then ("then {string} message will be displayed")
	public void then_message_will_be_displayed(String expectedMsg) {
		Assert.assertEquals(actualMsg,expectedMsg);
		logger.pass("Order Placed successfully!!!");
		cr.flush();
		driver.quit();
	}
}
