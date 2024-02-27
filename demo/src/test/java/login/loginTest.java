package login;

import java.util.List;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM.InventoryPOM;
import POM.LoginPOM;
import io.github.bonigarcia.wdm.WebDriverManager;

public class loginTest {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); //setting up the driver
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }

    @Test
    public void logintest() throws InterruptedException{
        driver.get(LoginPOM.loginURL);
        LoginPOM loginPOM = new LoginPOM(driver);
        InventoryPOM inventoryPOM = new InventoryPOM(driver);
        List<String> userNames = Arrays.asList("standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user");
        for (String userName : userNames) {
            loginPOM.typeUsername(userName);
            loginPOM.typePassword("secret_sauce");
            loginPOM.clickLogin();
            inventoryPOM.logout();
        }//this creates a interdependency between each of the tests will fix later
    }

    @Test
    public void loginInvalidUsername(){
        driver.get("https://www.saucedemo.com/");
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.typeUsername("testfail");
            loginPOM.typePassword("secret_sauce");
            loginPOM.clickLogin();
            loginPOM.ConfirmErrorMessage("Epic sadface: Username and password do not match any user in this service");
    
    }

    @Test
    public void loginInvalidPassword(){ //TODO this is a duplicate of the previous test. I'll data-testing later
        driver.get("https://www.saucedemo.com/");
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.typeUsername("standard_user");
            loginPOM.typePassword("testfail");
            loginPOM.clickLogin();
            loginPOM.ConfirmErrorMessage("Epic sadface: Username and password do not match any user in this service");
    
    }
}
