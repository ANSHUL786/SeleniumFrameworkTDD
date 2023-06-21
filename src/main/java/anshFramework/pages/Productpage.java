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

public class Productpage extends Reusablecomponents {
	WebDriver driver;
	ExtentTest logger;

	public Productpage(WebDriver driver, ExtentTest logger) {
		super(driver);
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	//
	// By productTextRelativeCss=By.cssSelector("b");
	// By addToCartRelativeCss=By.cssSelector("button:last-of-type");
	// By productAddedToast=By.cssSelector(".toast-bottom-right.toast-container");
	// By loader=By.cssSelector(".ng-animating");

	@FindBy(css = ".card-body")
	List<WebElement> productsList;

	@FindBy(css = ".toast-bottom-right.toast-container")
	WebElement productAddedToast;

	@FindBy(css = ".ng-animating")
	WebElement loader;

	@FindBy(css = ".toast-bottom-right.toast-container")
	WebElement toast;

	By productTextRelativeCss = By.cssSelector("b");
	By productsCard = By.cssSelector(".card-body");
	By loaderLocator = By.cssSelector(".ng-animating");
	By addToCartRelativeCss = By.cssSelector("button:last-of-type");
	By productAddedToastBy = By.cssSelector(".toast-bottom-right.toast-container");

	public List<WebElement> getAllProducts() {
		waitTillElementInVisible(toast);
		waitTillElementVisible(productsCard);
		logger.log(Status.INFO, "Getting all products");
		List<WebElement> allProductsList = productsList;
		System.out.print(allProductsList.size());
		return allProductsList;
	}

	public WebElement getProduct(String expectedProduct) {

		logger.log(Status.INFO, "Searching the desired product");
		WebElement actualProductElement = getAllProducts().stream()
				.filter(p -> p.findElement(productTextRelativeCss).getText().equalsIgnoreCase(expectedProduct))
				.findFirst().orElse(null);
		return actualProductElement;
	}

	public String addTocart(String expectedProduct) throws IOException {
		try {
			getProduct(expectedProduct).findElement(addToCartRelativeCss).click();
			// waitTillElementInVisible(loader);
			// waitTillLocatorInvisible(loaderLocator);
			waitTillElementVisible(productAddedToastBy);
			String toast = productAddedToast.getText();
			System.out.println(toast);
			logger.pass(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("ProductAddedToCart")).build());
			return toast;
		} catch (Exception e) {
			logger.log(Status.FAIL,e);
			logger.fail(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Product not found")).build());
			return null;
		}
	}

}
