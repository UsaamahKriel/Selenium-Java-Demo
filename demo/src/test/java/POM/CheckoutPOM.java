package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPOM {
    WebDriver driver;
    public CheckoutPOM(WebDriver driver){
        this.driver = driver;
    }

    public void enterFirstName(String firstName){
        driver.findElement(By.id("first-name")).sendKeys(firstName);;
    }

    public void enterLastName(String lastName){
        driver.findElement(By.id("last-name")).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode){
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }

    public void fillInYourInfo(String firstName, String lastName, String postalCode){
        enterFirstName(firstName);
        enterLastName(lastName);    
        enterPostalCode(postalCode);
    }

    public void clickContinue(){
        driver.findElement(By.id("continue")).click();
    }

    public void clickFinish(){
        driver.findElement(By.id("finish")).click();
    }

    public void confimSuccessfulOrder(){
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!"), "Text not found!");
    }

    public void clickBackToHome(){
        driver.findElement(By.id("back-to-products")).click();
    }
}
