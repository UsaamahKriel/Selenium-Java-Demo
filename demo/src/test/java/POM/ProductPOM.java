package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductPOM {
    WebDriver driver;
    public ProductPOM(WebDriver driver){
        this.driver = driver;
    }

    public void goBackToProducts(){
        driver.findElement(By.id("back-to-products")).click();;
    }

    public void confirmProduct(String productName){
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(productName));
    }
}
