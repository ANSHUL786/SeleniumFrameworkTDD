package anshFramework.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import anshFramework.pageComponent.Reusablecomponents;

public class Loginpage extends Reusablecomponents {

	WebDriver driver;
	ExtentTest logger;

	public Loginpage(WebDriver driver, ExtentTest logger) {
		super(driver);
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	// By email=By.id("userEmail");
	// By password=By.id("userPassword");
	// By login=By.id("login");

	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = ".toast-bottom-right.toast-container")
	WebElement loginToast;

	@FindBy(css = "[routerlink*='dashboard']")
	WebElement dashboard;

	public void goToLoginPage(String url) {
		driver.get(url);
	}

	public WebDriver login(String emailAddress, String pass) throws IOException {
		logger.log(Status.INFO, "Enter email and pass");
		email.sendKeys(emailAddress);
		password.sendKeys(pass);
		loginButton.click();
		logger.log(Status.INFO, "Click on login button");

		try {
			dashboard.isDisplayed();
			logger.pass("Logged in Successfully",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Dashboard Page")).build());
		} catch (Exception e) {
			logger.info(e);
			logger.fail("Logged failed",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Incorrect Dashboard Page")).build());

		}
		return driver;
	}

	public WebDriver incorrectlogin(String emailAddress, String pass) throws IOException {
		logger.log(Status.INFO, "Enter email and pass");
		try {
			email.sendKeys(emailAddress);
			password.sendKeys(pass);
			loginButton.click();
			logger.log(Status.INFO, "Click on login button");

		} catch (Exception e) {
			logger.info(e);
			logger.fail("Something failed",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Incorrect Dashboard Page")).build());

		}
		return driver;
	}

	public String getInvalidEmailPasswordMsg() throws IOException {
		waitUntilElementVisible(loginToast);
		String text = loginToast.getText();
		
logger.log(Status.INFO,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("Invalid" + System.currentTimeMillis())).build());
		System.out.println(text);
		return text;

	}
}
