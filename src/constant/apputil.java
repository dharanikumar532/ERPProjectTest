package constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class apputil 
{
public static WebDriver driver;
public static Properties config;
@BeforeSuite
public void setup() throws Throwable
{
config=new Properties();
config.load(new FileInputStream("D:\\hybridframework\\ERP_project\\propertyfile\\locators.properties"));
driver=new ChromeDriver();
driver.manage().window().maximize();
driver.get(config.getProperty("url"));
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
@AfterSuite
public void teardown() throws Throwable
{
	Thread.sleep(4000);
	driver.close();
}
}
