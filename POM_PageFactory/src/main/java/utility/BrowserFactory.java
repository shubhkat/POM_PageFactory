package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	public static ExtentHtmlReporter htmlreport;
	public static ExtentReports report;
	public static ExtentTest test;

	protected static WebDriver driver;

	public static void openBrowser(String browsername) {
		
		if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		else if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/");
	}

	public static void setReport() {
		String reportpath = "../POM_PageFactory/extentReports/report.html";
		htmlreport = new ExtentHtmlReporter(reportpath);
		htmlreport.config().setDocumentTitle("Pom Page Factory testing report");
		htmlreport.config().setReportName("Automation testing");
		htmlreport.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Browsername", "Firefox");
		report.setSystemInfo("Tester", "Test Engineer");
		report.setSystemInfo("OS", "windows-10");
		
	}

	public static void setSaveReport(ITestResult result) {
		test = report.createTest(result.getName());
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel("Test Method Failed", ExtentColor.RED));
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel("Test Method Passed", ExtentColor.GREEN));
		}
		else {
			test.log(Status.SKIP, MarkupHelper.createLabel("Test Method Skiped", ExtentColor.GREY));
		}
		report.flush();
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
}
