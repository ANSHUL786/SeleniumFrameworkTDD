package anshFramework.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import anshFramework.pageComponent.Reusablecomponents;

public class Orderpage extends Reusablecomponents {
	WebDriver driver;
	ExtentTest logger;

	public Orderpage(WebDriver driver, ExtentTest logger) {
		super(driver);
		this.driver = driver;
		this.logger = logger;
	}

	// By placedOrderList=By.xpath("//tr/td[2]");
	// By orderViewButtonRelative=By.xpath("./following-sibling::td[3]/button");
	// By thanksOrderMsg=By.cssSelector(".email-preheader");
	// By viewOrderTitle=By.cssSelector(".title");

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> placedOrderList;

	@FindBy(css = ".email-preheader")
	WebElement thanksOrderMsg;

	@FindBy(css = ".title")
	WebElement viewOrderTitle;

	By orderViewButtonRelative = By.xpath("./following-sibling::td[3]/button");

	public String[] verifyOrder(String expectedProduct) throws IOException {
		logger.info("Going to Order Page");
		try {
			goToOrderPage();
			logger.log(Status.INFO,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("order page")).build());
			WebElement foundOrderEle = placedOrderList.stream()
					.filter(s -> s.getText().equalsIgnoreCase(expectedProduct)).findFirst().orElse(null);
			String[] extractedTitles = new String[3];
			
			extractedTitles[0] = foundOrderEle.getText();
			logger.log(Status.INFO,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("orderisinpage")).build());
			jsexecutor().executeScript("arguments[0].click()",
			foundOrderEle.findElement(orderViewButtonRelative));
			logger.info("View that order");

			extractedTitles[1] = thanksOrderMsg.getText();
			logger.log(Status.INFO,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("particularOrder")).build());

			extractedTitles[2] = viewOrderTitle.getText();
			return extractedTitles;

		} catch (Exception e) {
			logger.log(Status.FAIL,e);
			logger.fail("Something went wrong",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Failed Stuff")).build());
			return null;
		}
	}

}
