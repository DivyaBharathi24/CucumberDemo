package com.demo.StepDefinitions;

import java.io.FileWriter;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.findby.By;


public class Demo1 {
	
	WebDriver driver;
	Response response;
    String projectPath = System.getProperty("user.dir");
	
	@Given("^User is on Home page of the React JS webpage.$")
	public void user_is_on_homepage_of_the_react_js_webpage() throws Throwable {
	    String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/driver/chromedriver.exe");					
        driver= new ChromeDriver();					
        driver.manage().window().maximize();			
        driver.get("https://reactjs.org/");	
        String actualUrl="https://reactjs.org/";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
	}
	
	@When("^When User clicks on Docs tab$")
	public void when_user_clicks_on_docs_tab() throws Throwable {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/header/div[2]/div/nav/a[1]"));
        element.click();
        String actualUrl="https://reactjs.org/docs/getting-started.html";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

	}
	
	@When("^When User clicks on Tutorials tab$")
	public void when_user_clicks_on_tutorials_tab() throws Throwable {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/header/div[2]/div/nav/a[2]"));
        element.click();
        String actualUrl="https://reactjs.org/tutorial/tutorial.html";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

	}
	
	
	@Then("^Verify that sub elements and links are displayed and save in file$")
	public void verify_that_sub_elements_and_links_are_displayed() throws Throwable {
		 List<WebElement> webElements = driver.findElements(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/div/div/div[1]/div/div/article/div/div[1]/ul[1]/li"));
	        Actions actions = new Actions(driver);
	        for(int i=0; i<webElements.size(); i++) {	
	        	actions.moveToElement(webElements.get(i)).perform();
	        	webElements.get(i).click();
	        	String text = driver.findElement(By.cssSelector(".css-6nf64v p, [data-css-6nf64v] p")).getAttribute("text");
	        	System.out.println("Text"+i+" is "+text);
	        	FileWriter fWriter = new FileWriter(projectPath+"\\writeText.txt");
	        	fWriter.write(text);
	        	fWriter.close();
	        }
	}
	
	@Then("^Verify the scrolling functionality in tutorials tab$")
	public void verify_the_scrolling_functionality_in_tutorials_tab() throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-350)", "");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"section_011783313736182932\"]/li[1]/a/span"));
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"section_011783313736182932\"]/li[1]/a"));
        String fontWeight = (String) js.
                executeScript(
                        "return getComputedStyle(arguments[0]).getPropertyValue('font-Weight');",
                        element1);
        Assert.assertEquals(fontWeight.trim(), "bold","To validate if the text is displayed in bold letters");
	}
	
	@When("^When User send an API request$")
	public void when_user_sends_an_api_request() throws Throwable {
		 RestAssured.baseURI = "https://coinmap.org/api/v1/venues/";
		 response = SerenityRest.get(RestAssured.baseURI);
	}
	
	@Then("^User get the count of the categories and prints the details$")
	public void user_gets_the_count_of_the_categories_and_prints_the_details() throws Throwable {
		List<String> categories = response.getBody().jsonPath().getList("venues.category");
		 int atmCount=0;
		 int defaultCount =0;
		 int sportsCount =0;
		 int groceryCount =0;
		 int lodgingCount =0;
		 int shoppingCount = 0;
		 int transportCount = 0;
		 int nightlifeCount = 0;
		 int cafeCount = 0;
		 int foodCount = 0;
		 int attractionCount = 0;
		 for(int i=0; i< categories.size(); i++) {
			 if(categories.get(i).equals("atm")) {
				 atmCount++;
			 }
			 else if(categories.get(i).equals("food")) {
				 atmCount++;
			 }
			 else if(categories.get(i).equals("default")) {
				 foodCount++;
			 } 
			 else if(categories.get(i).equals("sports")) {
				 sportsCount++;
			 }
			 else if(categories.get(i).equals("grocery")) {
				 groceryCount++;
			 }
			 else if(categories.get(i).equals("lodging")) {
				 lodgingCount++;
			 }
			 else if(categories.get(i).equals("shopping")) {
				 shoppingCount++;
			 }
			 else if(categories.get(i).equals("transport")) {
				 transportCount++;
			 }
			 else if(categories.get(i).equals("nightlife")) {
				 nightlifeCount++;
			 }
			 else if(categories.get(i).equals("cafe")) {
				 cafeCount++;
			 }
			 else if(categories.get(i).equals("attraction")) {
				 	attractionCount++;
			 }
		 }
		 System.out.println("ATM Count is "+atmCount);
		 System.out.println("Default Count is "+defaultCount);
		 System.out.println("sports Count is "+sportsCount);
		 System.out.println("grocery Count is "+groceryCount);
		 System.out.println("lodging Count is "+lodgingCount);
		 System.out.println("shopping Count is "+shoppingCount);
		 System.out.println("transport Count is "+transportCount);
		 System.out.println("NightLife Count is"+nightlifeCount);
		 System.out.println("Food Count is"+foodCount);
		 System.out.println("Cafe Count is"+cafeCount);
		 System.out.println("Attraction Count is"+attractionCount);
		 
	 }
	
	@And("^Print the name of Food category and geo location$")
	public void print_the_name_of_food_category_and_geo_location() throws Throwable{
		 RestAssured.baseURI = "https://coinmap.org/api/v1/venues/?select=names,geolocation_degrees&category=food";
		 Response response = SerenityRest.get(RestAssured.baseURI);
		 System.out.println(response.getBody().asString());
	}
	
}
