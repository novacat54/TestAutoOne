package TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.SearchPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by denysta on 7/25/2018.
 */
public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void createWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://www.autohero.com/de/search");
    }

     @AfterMethod
    public void closeDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    public void checkSearchFunctionality() {
        SearchPage page = new SearchPage(driver);
        page.filterRegistration("Erstzulassung ab");
        page.selectYear(2015);
        page.selectDescOrdering();
        Assert.assertTrue(page.checkThatDescPriceOrderingCorrect(), "Price oredering is not desc.");
        Assert.assertTrue(page.checkThatSelectedCarsAreOlderThenSelectedYear(2015), "Selected car's year is out of the requested range.");
    }
}
