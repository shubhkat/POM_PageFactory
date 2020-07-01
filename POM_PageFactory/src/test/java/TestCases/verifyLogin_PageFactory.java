package TestCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.PageFactoryLogin;
import utility.BrowserFactory;

public class verifyLogin_PageFactory extends BrowserFactory {

	@Parameters("browsername")
	@BeforeClass
	public void testOpenBrowser(String browsername) {
		BrowserFactory.openBrowser(browsername);
	}
	
	@BeforeSuite
	public void testSetReport() {
		BrowserFactory.setReport();
	}
	
	@AfterClass
	public void testCloseBrowser() {
		BrowserFactory.closeBrowser();
	}
	
	@AfterMethod
	public void testSetSaveReport(ITestResult result) {
		BrowserFactory.setSaveReport(result);
	}
	
	@Test
	public void testLogin() {
		PageFactoryLogin login = new PageFactoryLogin(driver);
		login.setLogin();
		Assert.assertEquals(login.verifyLogin(), "DummyWikiAccount");
		
	}
	
}
