package POM;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void addAllItemsToCart(){
        List<String> items = getAllItemNames();
        for (String item : items) {
            driver.findElement(By.name("add-to-cart-" + item.replace(" ", "-").toLowerCase())).click();
        }
    }

    public void logout() throws InterruptedException{
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500); //this isn't the best way to do it. Will change it later
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
