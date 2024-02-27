package generic_tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM.InventoryPOM;
import POM.LoginPOM;
import Util.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class sortingTest {
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
    public void sortAlphabeticalAsc(){
        driver.get(LoginPOM.loginURL);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.selectSorting("Name (A to Z)");
        List<String> itemNames = inventoryPOM.getAllItemNames();
        String temp = itemNames.get(0);
        for (String item : itemNames) {
            if (item.compareTo(temp) < 0) {
                Assert.fail("The items are not in alphbetical order");
            }

            temp = item;
        }
    }

    @Test
    public void sortAlphabeticalDsc(){
        driver.get(LoginPOM.loginURL);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.selectSorting("Name (Z to A)");
        List<String> itemNames = inventoryPOM.getAllItemNames();
        String temp = itemNames.get(0);
        for (String item : itemNames) {
            if (item.compareTo(temp) > 0) {
                Assert.fail("The items are not in alphbetical order");
            }

            temp = item;
        }
    }

    @Test
    public void sortpriceAsc(){
        driver.get(LoginPOM.loginURL);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.selectSorting("Price (low to high)");
        List<String> itemPrices = inventoryPOM.getAllItemPrices();
        String temp = itemPrices.get(0);
        for (String price : itemPrices) {
            if (Float.parseFloat(price) < Float.parseFloat(temp)) {
                Assert.fail("The items are not in alphbetical order");
            }

            temp = price;
        }
    }

    @Test
    public void sortpriceDsc(){
        driver.get(LoginPOM.loginURL);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.selectSorting("Price (high to low)");
        List<String> itemPrices = inventoryPOM.getAllItemPrices();
        String temp = itemPrices.get(0);
        for (String price : itemPrices) {
            if (Float.parseFloat(price) > Float.parseFloat(temp)) {
                System.out.println(price);
                System.out.println(temp);
                Assert.fail("The items are not in alphbetical order");
            }

            temp = price;
        }
    }


}
