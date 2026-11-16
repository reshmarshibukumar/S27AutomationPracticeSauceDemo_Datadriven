package testClass;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass_SauceDemo;
import pageObject.LoginSauceDemo_PageObject;

public class Login_SaucedemoTest extends BaseClass_SauceDemo 
{
	@Test(priority=1)
	public void validLogin()
	{
		LoginSauceDemo_PageObject obj=new LoginSauceDemo_PageObject(driver);
		obj.enterUsername("standard_user");
		obj.enterPassword("secret_sauce");
		obj.clickLogin();
		
		//Validation
		String actualurl=obj.getURL();
		String expectedurl="https://www.saucedemo.com/inventory.html";
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("/inventory.html"));
		Assert.assertEquals(actualurl, expectedurl);
	}
	
	@Test(priority=2)
	public void invalidUsername()
	{
		LoginSauceDemo_PageObject obj=new LoginSauceDemo_PageObject(driver);
		obj.enterUsername("standard_use");
		obj.enterPassword("secret_sauce");
		obj.clickLogin();
		
		//Validations
		String actualURL=obj.errorMsg();
		String expectedURL="Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(actualURL, expectedURL);
		
	}

}
