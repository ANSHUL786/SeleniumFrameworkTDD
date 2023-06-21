package anshFramework.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import anshFramework.pageComponent.Reusablecomponents;

public class Cartpage extends Reusablecomponents {

	WebDriver driver;
	ExtentTest logger;

	public Cartpage(WebDriver driver, ExtentTest logger) {
		super(driver);
		this.driver = driver;
		this.logger = logger;
	}

	// By cartItems=By.cssSelector(".cartSection h3");
	// By cartItemNameRelative=By.cssSelector(" h3");
	// By checkout=By.xpath("//button[text()='Checkout']");

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItemsList;

	@FindBy(css = ".cartSection h3")
	WebElement cartItems;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutButton;

	public Boolean verifyItemPresent(String itemName) throws IOException {
		try {
			logger.info("Going to Cart Page");
			goToCart();
			logger.log(Status.INFO,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("cart page")).build());
			logger.info("Waiting for all item to load");

			waitUntilElementVisible(cartItems);

			Boolean isPresent = cartItemsList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(itemName));
			logger.log(Status.INFO,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("ProductIsInCart")).build());

			return isPresent;
		} catch (Exception e) {
			logger.log(Status.FAIL,e);
			logger.log(Status.FAIL,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Failed Stuff")).build());
			return false;

		}
	}

	public void clickCheckout() {
		checkoutButton.click();
		logger.info("Click on checkout button");

	}
}
