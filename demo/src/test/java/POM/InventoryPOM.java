package POM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class InventoryPOM {
    WebDriver driver;

    public static String inventoryUrl = LoginPOM.loginURL + "/inventroy.html";

    public InventoryPOM(WebDriver driver){
        this.driver = driver;
    }

    public void addItemToCart(String productName){
        driver.findElement(By.id("add-to-cart-" + productName.replace(" ", "-").toLowerCase())).click();

    }

    public void clickShoppingCart(){
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    public List<String> getAllItemNames(){
        List<String> temp = new ArrayList<>();
        List<WebElement> divElements = driver.findElements(By.className("inventory_item_name"));

        for (WebElement webElement : divElements) {
            String divText = webElement.getText();
            temp.add(divText);
        }
        return temp;
    }
    
    public List<String> getAllItemPrices(){
        List<String> temp = new ArrayList<>();
        List<WebElement> divElements = driver.findElements(By.className("inventory_item_price"));

        for (WebElement webElement : divElements) {
            String divText = webElement.getText();
            temp.add(divText.replace("$", "").trim());
        }
        return temp;
    }

    public void clickProductName(String productName){
        Map<String, String> map = new HashMap<>();
        map.put("Sauce Labs Backpack", "item_4_title_link");
        map.put("Sauce Labs Bike Light", "item_0_title_link");
        map.put("Sauce Labs Bolt T-Shirt", "item_1_title_link");
        map.put("Sauce Labs Fleece Jacket", "item_5_title_link");
        map.put("Sauce Labs Onesie", "item_2_title_link");
        map.put("Test.allTheThings() T-Shirt (Red)", "item_3_title_link");
        driver.findElement(By.id(map.get(productName))).click();
    }

    public void addAllItemsToCart(){
        List<String> items = getAllItemNames();
        for (String item : items) {
            driver.findElement(By.name("add-to-cart-" + item.replace(" ", "-").toLowerCase())).click();
        }
    }

    public void selectSorting(String sorting){
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(sorting);
    }

    public void removeAllItemsFromCart(){
        List<String> items = getAllItemNames();
        for (String item : items) {
            driver.findElement(By.id("remove-" + item.replace(" ", "-").toLowerCase())).click();
        }
    }

    public void confirmNoItemsInCart(){
        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).size() == 0);    
        Assert.assertFalse(driver.findElement(By.tagName("body")).getText().contains("Remove"));
    }

    public void logout() throws InterruptedException{
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500); //this isn't the best way to do it. Will change it later
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
