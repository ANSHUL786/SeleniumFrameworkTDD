package anshFramework.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class Customreport {

	ExtentReports extent;
	ExtentTest logger;

	public Customreport() {
		String reportPath = System.getProperty("user.dir") + "/src/main/java/anshFramework/Reports/AnshReport.html";
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.setSystemInfo("Analyst", "Ansh");
		htmlReporter.config().setDocumentTitle("Test Report");
		htmlReporter.config().setReportName("Test Automation Results");
		
		extent.attachReporter(htmlReporter);

	}
	
	public ExtentTest createTest(String TestName,String Desc) {
		
		logger=extent.createTest(TestName, Desc);
		return logger;
		
	}

	public void flush() {
		 extent.flush();
	}
}
