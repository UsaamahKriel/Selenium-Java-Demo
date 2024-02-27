package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * LoginPOM
 */
public class LoginPOM {

    private String usernameID = "user-name";
    private String passwordID = "password";
    private String loginButtonID = "login-button";
    private WebDriver driver;
    public static String loginURL = "https://qa-challenge.codesubmit.io";

    public LoginPOM(WebDriver driver){
        this.driver = driver;
    }

    public void typeUsername(String username){
        driver.findElement(By.id(usernameID)).sendKeys(username);
    }

    public void typePassword(String password){
        driver.findElement(By.id(passwordID)).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(By.id(loginButtonID)).click();
    }

    public void CheckLogin() {
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPOM.inventoryUrl);
    }
    public void ConfirmErrorMessage(String message){
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains(message), "Text not found!");
    }
}