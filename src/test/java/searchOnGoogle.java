import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class searchOnGoogle {

    WebDriver driver;

    @Test
    public void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920x1080");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test(dependsOnMethods = "launchBrowser")
    public void navigateToGoogle() {
        driver.get("https://www.google.com/");
    }

    @Test(dependsOnMethods = "navigateToGoogle")
    public void typeIntoSearchbox() {
        WebElement searchbox = driver.findElement(By.xpath("//form[@action='/search']//input[@type='text']"));

        searchbox.sendKeys("longcat");
    }

    @Test(dependsOnMethods = "typeIntoSearchbox")
    public void clickSearch() {
        WebElement searchButton = driver.findElement(By.xpath("(//form[@action='/search']//input[@name='btnK'][1])[1]"));
        searchButton.click();
    }

    @Test(dependsOnMethods = "clickSearch")
    public void resultsPageIsReached() {
        Assert.assertTrue(driver.getCurrentUrl().contains("search?q=longcat"));
    }

    @Test(dependsOnMethods = "resultsPageIsReached")
    public void closeBrowser() {
        driver.close();
    }
}
