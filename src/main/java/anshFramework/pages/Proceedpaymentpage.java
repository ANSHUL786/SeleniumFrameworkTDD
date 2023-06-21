package anshFramework.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import anshFramework.pageComponent.Reusablecomponents;

public class Proceedpaymentpage extends Reusablecomponents {
	WebDriver driver;
	ExtentTest logger;

	public Proceedpaymentpage(WebDriver driver, ExtentTest logger) {
		super(driver);
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	// By selectCountry=By.cssSelector("input[placeholder='Select Country']");
	// By countryResultBox=By.cssSelector(".ta-results");
	// By eachCountryFromResult=By.cssSelector(".list-group-item span");
	// By placeOrder=By.xpath("//a[text()='Place Order ']");
	// By thanksText=By.cssSelector(".hero-primary");

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(css = ".ta-results")
	WebElement countryResultBox;

	@FindBy(css = ".list-group-item span")
	List<WebElement> searchCountryList;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeOrder;

	@FindBy(css = ".hero-primary")
	WebElement thanksText;

	public String proceedToCheckout(String country) throws Exception {
		logger.info(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("checkoutPage")).build());
		try {
			waitUntilElementVisible(selectCountry);
			logger.info("Searching country - " + country);
			selectCountry.sendKeys(country);
			waitUntilElementVisible(countryResultBox);

			logger.info("Selecting country - " + country);

			WebElement element = searchCountryList.stream().filter(s -> s.getText().equalsIgnoreCase(country))
					.findFirst().orElse(null);
			element.click();
			logger.info(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("countrySelected")).build());
			jsexecutor().executeScript("window.scrollBy(0,15000)");
			Thread.sleep(2000);
			waitTillElementclickable(placeOrder);
			logger.info("Placing Order");
			placeOrder.click();
			jsexecutor().executeScript("window.scrollTo(0,0)");

			String textThanks = thanksText.getText();
			logger.info("Placed order",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("placedOrder")).build());

			return textThanks;
		} catch (Exception e) {
			logger.log(Status.FAIL,e);
			logger.fail("Something went wrong",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Something went wrong")).build());
			return null;
		}
	}

}
