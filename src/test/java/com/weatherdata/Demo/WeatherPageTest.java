package com.weatherdata.Demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class WeatherPageTest {

    WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws InterruptedException {
		
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver();
	        
        //Maximize Window & Launch Application 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to("https://www.visualcrossing.com/");
	        
        //Accept all cookies
		WebElement acceptAllCookiesButton = driver.findElement(By.xpath("//button[contains(text(), 'Accept all cookies')]"));
        acceptAllCookiesButton.click();
	}
        
	@Test (priority = 1)
	public void weather_data() throws InterruptedException {
		
		//click weather data menu
		driver.findElement(By.linkText("Weather Data")).click();
			
		//Enter the city name and search for the weather
		WebElement city_element = driver.findElement(By.id("wxlocation"));
		city_element.sendKeys("Coimbatore");
			
		driver.findElement(By.xpath("//*[@id=\"wxdataform\"]/div[2]/button")).click();
	}
		
	@Test (priority = 2)
	public void weather_in_C() throws InterruptedException {
		
		//Validate the weather report 
		WebElement element = driver.findElement(By.xpath("/html/body/main/section[1]/h1"));
		String text = element.getText();
		System.out.println("Section Title : " + text);
	        
		//Validate the celsius/fahrenheit button enabled
		WebElement btnGroup = driver.findElement(By.className("btn-group"));
		WebElement activeButton = btnGroup.findElement(By.cssSelector(".btn.active"));
		String buttonText = activeButton.getText();
		System.out.println("Active C/F Button Enabled : " + buttonText);
	}
		
		
	@Test (priority = 3)
	public void test_PageData() {
		// Locate the elements in the summary
		WebElement maxTempElement = driver.findElement(By.xpath("//*[@id=\"weatherSummary\"]/div/div[1]/div/div[3]/div[1]/div[2]"));
		WebElement minTempElement = driver.findElement(By.xpath("//*[@id=\"weatherSummary\"]/div/div[1]/div/div[3]/div[2]/div[2]"));
		WebElement totalPrecipElement = driver.findElement(By.xpath("//*[@id=\"weatherSummary\"]/div/div[1]/div/div[3]/div[3]/div[2]"));
		WebElement maxDailyPrecipElement = driver.findElement(By.xpath("//*[@id=\"weatherSummary\"]/div/div[1]/div/div[3]/div[4]/div[2]"));
		WebElement rainDaysElement = driver.findElement(By.xpath("//*[@id=\"weatherSummary\"]/div/div[1]/div/div[3]/div[5]/div[2]"));
		WebElement maxSustainedWindElement = driver.findElement(By.xpath("//*[@id=\"weatherSummary\"]/div/div[1]/div/div[3]/div[6]/div[2]"));

		// Get text values from elements
		String maxTemp = maxTempElement.getText();
		String minTemp = minTempElement.getText();
		String totalPrecip = totalPrecipElement.getText();
		String maxDailyPrecip = maxDailyPrecipElement.getText();
		String rainDays = rainDaysElement.getText();
		String maxSustainedWind = maxSustainedWindElement.getText();

		// Perform assertions or validations
		System.out.println("MaxTemp : "+maxTemp);
		System.out.println("MinTemp : "+minTemp );
		System.out.println("TotalPrecip : "+totalPrecip );
		System.out.println("MaxDailyPrecip : "+maxDailyPrecip );
		System.out.println("RainDays : "+ rainDays);
		System.out.println("MaxSustainedWind : "+ maxSustainedWind);
	}
		
			
	@AfterSuite
	public void exit_Driver() {
		if (driver != null) {
			driver.quit();
		}
    }
}