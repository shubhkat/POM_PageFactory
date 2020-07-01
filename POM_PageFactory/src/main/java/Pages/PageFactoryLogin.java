package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.Excel;

public class PageFactoryLogin {

	//constructor
	public PageFactoryLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id=\"pt-login\"]/a")
	WebElement loginTab;
	
	@FindBy(id="wpName1")
	WebElement username;
	
	@FindBy(how=How.ID, using="wpPassword1")
	WebElement password;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"wpLoginAttempt\"]")
	WebElement login;

	@FindBy(partialLinkText = "DummyWikiAccount")
	WebElement userAccountName;
	
	String testDataPath = "../POM_PageFactory/testData/Test_Data.xlsx";
	String sheetName = "Login Details";
	
	public void setLogin() {
		loginTab.click();
		String userName = Excel.ReadExcel(testDataPath, sheetName, 1, 0);
		String passWord = Excel.ReadExcel(testDataPath, sheetName, 1, 1);
		username.sendKeys(userName);
		password.sendKeys(passWord);
		login.click();
		//closeButton.click();
	}
	
	public String verifyLogin() {
		String accountName = userAccountName.getText();
		return accountName;
	}
	
}
