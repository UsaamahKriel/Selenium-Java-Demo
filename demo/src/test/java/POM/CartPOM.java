package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPOM {
    WebDriver driver;
    public CartPOM(WebDriver driver){
        this.driver = driver;
    }

    public void clickCheckout(){
        driver.findElement(By.id("checkout")).click();
    }

    //add a method to check if the prices are right
}
