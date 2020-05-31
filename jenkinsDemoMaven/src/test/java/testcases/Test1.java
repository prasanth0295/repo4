package testcases;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {

	public WebDriver driver;
	Date d;
	String fileName;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.yahoo.com");
		d = new Date();
		fileName = "G://Workspace1/" + d.toString().replace(":", "_").replace(" ", "_").trim() + ".jpg";
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	@Test
	public void openInChromeBrowser() {
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(screenshot, new File(fileName));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
