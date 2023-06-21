package anshFramework.pageComponent;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusablecomponents {

	WebDriver driver;
	
	public Reusablecomponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//By cartHeader=By.xpath("//*[contains(@routerlink,'cart')]");
	
	@FindBy(xpath="//*[contains(@routerlink,'cart')]")
	WebElement cartHeader;
	
	@FindBy(xpath="//*[contains(@routerlink,'order')]")
	WebElement orderHeader;
	
	
	public void goToCart() {
		cartHeader.click();
	}
	
	public void goToOrderPage() {
		orderHeader.click();
	}
	
	public JavascriptExecutor jsexecutor() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		return js;
	}
	
	public void waitTillElementVisible(By byLocator) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	public void waitUntilElementVisible(WebElement ele) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitTillElementclickable(WebElement element) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public void waitTillElementInVisible(WebElement element) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitTillLocatorInvisible(By by) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public String takeScreenshot(String title) throws IOException {
		String path =System.getProperty("user.dir")+"//Screenshot//"+title+".png";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		return path;
		
	}
}
