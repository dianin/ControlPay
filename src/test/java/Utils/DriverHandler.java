package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverHandler {

    public static WebDriver driver;

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setSettings();
        return driver;
    }

    public static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        setSettings();
        return driver;
    }

    public static WebDriver creatOperaDriver() {
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
        setSettings();
        return driver;
    }

    private static void setSettings() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public static void turnOffDriver() {
        if (driver != null)
            driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
