package anshFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import anshFramework.pages.Loginpage;
import anshFramework.testsComponent.Baseconfigurationclass;
import anshFramework.testsComponent.Iretry;
import anshFramework.testsComponent.Readdatafromjson;
import anshFramework.testsComponent.Readdatafromexcel;

public class Invalidcases extends Baseconfigurationclass {
	WebDriver driver;
	Loginpage loginPage;
	ExtentTest logger;


	@Test(dataProvider = "getData", enabled = true)
	public void login(String email, String pass) throws IOException {
		logger=cr.createTest("Checking Invalid Crediantial Login", "Invalid Email and password");
		logger.info("Data >>>> Email : "+email+" and Password : "+pass );
		loginPage = launchApplication(logger);
		loginPage.incorrectlogin(email, pass);
		logger.pass("Not able to login");

	}

	@Test(dataProvider = "getHashData", enabled = true,retryAnalyzer=Iretry.class )
	public void loginHash(HashMap<String, String> input) throws IOException {
		logger=cr.createTest("Checking Invalid Crediantial Login", "Invalid Email and password");

		String email = input.get("email");
		String pass = input.get("password");
		logger.info("Data >>>> Email : "+email+" and Password : "+pass );

		loginPage = launchApplication(logger);
		loginPage.incorrectlogin(email, pass);
		Assert.assertEquals(loginPage.getInvalidEmailPasswordMsg(), "Incorrect email or password.");
		logger.pass("Not able to login");

	}
	
	@Test(dataProvider = "getJSONData", enabled = true,retryAnalyzer=Iretry.class)
	public void loginJSONData(HashMap<String, String> input) throws IOException {
		logger=cr.createTest("Checking Invalid Crediantial Login", "Invalid Email and password");
		String email = input.get("email");
		String pass = input.get("password");
		logger.info("Data >>>> Email : "+email+" and Password : "+pass );

		loginPage = launchApplication(logger);
		loginPage.incorrectlogin(email, pass);
		Assert.assertEquals(loginPage.getInvalidEmailPasswordMsg(), "Incorrect email or password.");
		logger.pass("Not able to login");
	}

	

	@Test(dataProvider = "getExcelData", enabled = true)
	public void loginExcelData(String email, String pass) throws IOException {
		logger=cr.createTest("Checking Invalid Crediantial Login", "Invalid Email and password");
		logger.info("Data >>>> Email : "+email+" and Password : "+pass );
		loginPage = launchApplication(logger);
		loginPage.incorrectlogin(email, pass);
		Assert.assertEquals(loginPage.getInvalidEmailPasswordMsg(), "Incorrect email or password.");
		logger.pass("Not able to login");

	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][2];

		data[0][0] = "es@df.g";
		data[0][1] = "es@df.g";
		data[1][0] = "es1@df.g";
		data[1][1] = "es2@df.g";
		return data;
	}

	@DataProvider
	public Object[][] getHashData() {
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "dasfg@j.op");
		map1.put("password", "dasfg@j.op");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "rety@df.op");
		map2.put("password", "fsdg.op");
		return new Object[][] { { map1 }, { map2 } };
	}

	@DataProvider
	public Object[][] getJSONData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/anshFramework/dataFiles/data.json";
		Readdatafromjson read=new Readdatafromjson();
		List<HashMap<String,String>> data= read.getJSONData(path);
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	}
	
	@DataProvider
	public Object[][] getExcelData() throws IOException{
		String path = System.getProperty("user.dir") + "/src/test/java/anshFramework/dataFiles/dataTest.xlsx";
		Readdatafromexcel read=new Readdatafromexcel();
		
		Object[][] data=read.getDatafromExcel(path);
		return data;
	}
}
