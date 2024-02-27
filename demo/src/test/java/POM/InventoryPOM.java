package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPOM {
    WebDriver driver;

    public static String inventoryUrl = LoginPOM.loginURL + "/inventroy.html";
    public InventoryPOM(WebDriver driver){
        this.driver = driver;
    }
    public void logout() throws InterruptedException{
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500); //this isn't the best way to do it. Will change it later
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
