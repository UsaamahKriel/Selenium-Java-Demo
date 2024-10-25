package Util;

import java.io.File;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeMethod
    public void SetUp() throws Exception {
        Dotenv env = Dotenv.load();
        WebDriverManager.chromedriver().setup();
        String browser = env.get("BROWSER");
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            throw new Exception("Incorrect Browser");
        }
    }

    @AfterMethod
    public void TearDown(ITestResult result) {
        // Here will compare if test is failing then only it will enter into if
        // condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // Create refernce of TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;

                // Call method to capture screenshot
                File source = ts.getScreenshotAs(OutputType.FILE);

                // Copy method specific location here it will save all screenshot in our project
                // home directory and
                // result.getName() will return name of test case so that screenshot name will
                // be same
                try {
                    FileHandler.copy(source, new File("C:\\Users\\Dell\\Pictures\\DELETE\\" + result.getName()
                            + "nuggies" + LocalDateTime.now().getMinute() + ".png"));
                    System.out.println("Screenshot taken");
                } finally {

                }
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            } finally {

            }
        }
        // close application
        driver.quit();
    }
}
