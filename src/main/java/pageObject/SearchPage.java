package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by denysta on 7/25/2018.
 */
public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void filterRegistration(String byYear){
        driver.findElement(By.xpath("//*[text()='"+byYear+"']")).click();
    }

    public void selectYear(int year){
        driver.findElement(By.name("yearRange.min")).click();
        WebElement yearFrom = driver.findElement(By.xpath("//*[@name='yearRange.min']//option[text()='"+year+"']"));
        yearFrom.click();
    }

    public void selectDescOrdering(){
        driver.findElement(By.name("sort")).click();
        waitSomeTime(2000);
        driver.findElement(By.xpath("//*[@data-qa-selector-value='offerPrice.amountMinorUnits.desc']")).click();
        waitSomeTime(2000);
    }

    public static void waitSomeTime(int timeToWait){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkThatDescPriceOrderingCorrect(){
        List<WebElement> selectedCars ;
        selectedCars = driver.findElements(By.xpath("//*[@data-qa-selector='price']"));
        for (int i=0;i<selectedCars.size()-1;i++){
            Float bigger = Float.parseFloat(selectedCars.get(i).getText().substring(0,5));
            Float smaller = Float.parseFloat(selectedCars.get(i+1).getText().substring(0,5));
            if(bigger>=smaller){
                System.out.println("Ok, " + bigger + " is bigger then " + smaller);
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean checkThatSelectedCarsAreOlderThenSelectedYear(int year){
        List<WebElement> selectedCars;
        selectedCars = driver.findElements(By.xpath("//*[@data-qa-selector='price']"));
        for (WebElement a : selectedCars){
            Integer carYear= Integer.parseInt(a.findElement(By.xpath("//*[@data-qa-selector='spec'][1]")).getText().substring(2,6));
            if (carYear>=year){
                System.out.println("Car year  " + carYear + " is correct and it is equals or greater than " + year);;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
