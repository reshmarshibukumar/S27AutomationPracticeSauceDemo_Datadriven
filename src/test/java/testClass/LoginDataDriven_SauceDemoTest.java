package testClass;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseClass.BaseClass_SauceDemo;
import pageObject.LoginSauceDemo_PageObject;
import utils.ExcelUtils;

public class LoginDataDriven_SauceDemoTest extends BaseClass_SauceDemo{
	
	@Test(priority=1)
	public void loginValidation() throws InterruptedException
	{
		int rowCount=ExcelUtils.getRowCount();
		SoftAssert sa=new SoftAssert();
		
		//System.out.println("Rowcount : "+rowCount);
		for(int i=1;i<=rowCount;i++)
		{
			driver.get("https://www.saucedemo.com/");
			String username=ExcelUtils.getCellData(i, 0);
			String password=ExcelUtils.getCellData(i, 1);
			String expected=ExcelUtils.getCellData(i, 2);
			Thread.sleep(1000);
			
			LoginSauceDemo_PageObject obj=new LoginSauceDemo_PageObject(driver);
			obj.enterUsername(username);
			obj.enterPassword(password);
			obj.clickLogin();
			
			//Validation
			String actual;
			if(obj.getURL().contains("/inventory.html"))
			{
				actual=obj.getURL();
			}
			else
			{
				try
				{
					actual=obj.errorMsg();
				}
				catch(Exception e)
				{
					actual="No Message displayed";
				}
			}
			sa.assertEquals(actual, expected);
			
			//Write result to the Cell
			ExcelUtils.setCellData(i, 3, actual);
			
			//Ternary/Conditional Operator
			String status=actual.equalsIgnoreCase(expected)?"PASS":"FAIL";
			ExcelUtils.setCellData(i, 4, status);
			System.out.println("Row  "+i+ " : "+actual + " "+status);
			//System.out.println("Actual URL :"+actual);
		   	//System.out.println("Expected URL :"+expected);
			
		}
		sa.assertAll();
		
	}

}
