package generic_tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM.InventoryPOM;
import POM.LoginPOM;
import POM.ProductPOM;
import Util.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class viewAnItem {
    WebDriver driver;
    LoginPOM loginPOM;
    InventoryPOM inventoryPOM;
    ProductPOM productPOM;
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // setting up the driver
        
        loginPOM = new LoginPOM(driver);
        inventoryPOM = new InventoryPOM(driver);
        productPOM = new ProductPOM(driver);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void viewAllItems() throws InterruptedException{
        driver.get(LoginPOM.loginURL);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        List<String> items = inventoryPOM.getAllItemNames();
        for (String item : items) {
            inventoryPOM.clickProductName(item);
            productPOM.confirmProduct(item);
            productPOM.goBackToProducts();
        }
    }
}
