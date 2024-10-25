package generic_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POM.CartPOM;
import POM.CheckoutPOM;
import POM.InventoryPOM;
import POM.LoginPOM;
import Util.BaseTest;
import Util.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class buyItemsTest extends BaseTest {

    // @BeforeTest
    // public void setup() {
    //     WebDriverManager.chromedriver().setup();
    //     driver = new ChromeDriver(); // setting up the driver
    // }

    // @AfterMethod
    // public void teardown(ITestResult result) {
    //     BaseTest.TearDown(driver, result);
    //    // driver.quit();
    // }

    @Test
      public void buyAnItem(){
        driver.get(LoginPOM.loginURL);
        LoginPOM loginPOM = new LoginPOM(driver);
        InventoryPOM inventoryPOM = new InventoryPOM(driver);
        CartPOM cartPOM = new CartPOM(driver);
        CheckoutPOM checkoutPOM = new CheckoutPOM(driver);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.addItemToCart("Sauce Labs Backpack");
        inventoryPOM.clickShoppingCart();
        cartPOM.clickCheckout();
        checkoutPOM.fillInYourInfo("Usaamah", "Kriel", "7405"); 
        checkoutPOM.clickContinue();
        checkoutPOM.clickFinish();
        checkoutPOM.confimSuccessfulOrder();
    }

    @Test
    public void buyAllItems(){
        driver.get(LoginPOM.loginURL);
        LoginPOM loginPOM = new LoginPOM(driver);
        InventoryPOM inventoryPOM = new InventoryPOM(driver);
        CartPOM cartPOM = new CartPOM(driver);
        CheckoutPOM checkoutPOM = new CheckoutPOM(driver);
        loginPOM.typeUsername(Parameters.userName);
        loginPOM.typePassword("secret_sauce");
        loginPOM.clickLogin();
        inventoryPOM.addAllItemsToCart();
        inventoryPOM.clickShoppingCart();
        cartPOM.clickCheckout();
        checkoutPOM.fillInYourInfo("Usaamah", "Kriel", "7405"); 
        checkoutPOM.clickContinue();
        checkoutPOM.clickFinish();
        checkoutPOM.confimSuccessfulOrder();
    }
}
