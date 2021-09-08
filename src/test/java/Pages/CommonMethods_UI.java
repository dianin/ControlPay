package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CommonMethods_UI {

    private WebDriver driver;
    private WebDriverWait shortWait;


    public CommonMethods_UI(WebDriver driver) {
        this.driver = driver;
        shortWait = new WebDriverWait(driver, 10);
    }

    public String getText(By locator) {
        return shortWait.until(presenceOfElementLocated(locator)).getText();
    }

    public void verifyAvailability(By locator) {
        List<WebElement> list = returnList(locator);
        Assert.assertEquals(list.size(), 1);
    }

    public List<WebElement> returnList(By locator) {
        return driver.findElements(locator);
    }

    public void clearThenInput(By locator, String textToInput) {
        shortWait.until(visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(textToInput);
    }

    public void click(By locator) {
        shortWait.until(elementToBeClickable(locator)).click();
    }

    public void waitForAbsence(By locator) {
        shortWait.until(invisibilityOfElementLocated(locator));
    }

    public void uploadPhoto(By locator, String pathToFile) {
        shortWait.until(elementToBeClickable(locator)).sendKeys(pathToFile);
    }

    public String getAttribute(By locator, String attribute) {
        return shortWait.until(visibilityOfElementLocated(locator)).getAttribute(attribute);
    }

    public void verifySizeOfImage_Attributes(By locator, int expectedHeight, int expectedWidth) {
        int actualHeight = Integer.parseInt(getAttribute(locator, "height"));
        int actualWidth = Integer.parseInt(getAttribute(locator, "width"));
        Assert.assertEquals(actualHeight, expectedHeight);
        Assert.assertEquals(actualWidth, expectedWidth);
    }

}
