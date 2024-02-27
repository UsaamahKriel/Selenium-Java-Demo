package locked_out_user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM.LoginPOM;
import io.github.bonigarcia.wdm.WebDriverManager;

public class loginTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // setting up the driver
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void logintest() {
        driver.get("https://www.saucedemo.com/");
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.typeUsername("locked_out_user");
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        loginPOM.ConfirmErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }
}
