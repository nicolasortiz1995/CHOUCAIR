package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseClass {
    protected WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver chromeConnection() {
        try {
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            return driver;
        } catch (Exception e) {
            System.out.println("An error occurred while connecting to Chrome: " + e.getMessage());
            return null;
        }
    }

    public WebElement findElement (By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator){
        return driver.findElements(locator);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getText(By locator){
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public Boolean isDisplayed(By locator){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void visit(String url){
        driver.get(url);
    }
    
}
