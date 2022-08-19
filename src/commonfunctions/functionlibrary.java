package commonfunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import constant.apputil;

public class functionlibrary extends apputil
{
	public static  boolean verifylogin(String username,String password)
	{
		driver.findElement(By.xpath(config.getProperty("objreset"))).click();
		driver.findElement(By.xpath(config.getProperty("objusername"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("objpassword"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("objlogin"))).click();
		String expected="dashboard";
		String actual=driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("login success",true);
			return true;
		}
		
		else
		{
			Reporter.log("login failed",true);
			return false;
		}
	}
	public static boolean logout()
	{
		driver.findElement(By.xpath(config.getProperty("objlogout"))).click();
		if(driver.findElement(By.xpath(config.getProperty("objlogin"))).isDisplayed())
		{
			Reporter.log("logout success",true);
			return true;
		}
		else
			{
				Reporter.log("logout failed",true);
				return false;
			}
		
	}
	
}
