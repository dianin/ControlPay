package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static Utils.DriverHandler.getDriver;

public class MainPage extends CommonMethods_UI {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By welcomeTitle = By.id("welcome");
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By submitButton = By.id("submit");
    private By errorLabel = By.id("error_submit");
    private By openProfileButton = By.id("btn_profile");
    private By profileLabel = By.xpath("//*[@text='Profile']");
    private static final String URL_LOGIN = "https://testapp.com/login";

    public void openMainPage() {
        getDriver().get("URL_LOGIN");
        verifyPageOpened();
    }

    @Step
    public void inputEmail(String email) {
        clearThenInput(emailInput, email);
    }

    @Step
    public void inputPassword(String password) {
        clearThenInput(passwordInput, password);
    }

    @Step
    public void submit() {
        click(submitButton);
    }

    public void verifyErrorLabel(String text) {
        Assert.assertEquals(getText(errorLabel), text);
    }

    public void verifyPageOpened() {
        verifyAvailability(welcomeTitle);
    }

    private By userGreeting = By.id("greeting");

    private static String greetingLabel = "Hello, ";

    @Step
    public void verifyIsOpened_correctName(String firstName, String lastName) {
        Assert.assertEquals(getText(userGreeting), greetingLabel + firstName + " " + lastName);
    }

    @Step
    public void openProfileMenu() {
        click(openProfileButton);
        verifyAvailability(profileLabel);
    }


}
