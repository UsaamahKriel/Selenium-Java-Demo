package generic_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM.InventoryPOM;
import POM.LoginPOM;
import Util.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class removeAnItemTest {
    WebDriver driver;
    LoginPOM loginPOM;
    InventoryPOM inventoryPOM;
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // setting up the driver
        
        loginPOM = new LoginPOM(driver);
        inventoryPOM = new InventoryPOM(driver);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
    @Test
    public void removeAllItems(){
        driver.get(LoginPOM.loginURL);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.addAllItemsToCart();
        inventoryPOM.removeAllItemsFromCart();
        inventoryPOM.confirmNoItemsInCart();
    }
}
