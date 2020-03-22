package Tests_UI;

import Pages.MainPage;
import Pages.ProfilePage;
import Utils.TestListener;
import Utils.UserInitializer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import static Pages.ProfilePage.AVATAR_IMG;
import static Utils.DriverHandler.*;
import static Utils.TestListener.toSetRetry;
import static Utils.UserInitializer.usersList;

@Listeners(TestListener.class)
public class Test_Login_UI {

    private MainPage mainPageUI;
    private ProfilePage profilePageUI;

    @BeforeSuite
    public void beforeSuite(ITestContext testContext)
    {
        if (toSetRetry())
        {
            for (ITestNGMethod method : testContext.getAllTestMethods())
            {
                method.setRetryAnalyzer(new TestListener());
            }
        }
    }

    @BeforeMethod
    private void setUp() {
        createChromeDriver();
        mainPageUI = new MainPage(getDriver());
        profilePageUI = new ProfilePage(getDriver());
        mainPageUI.openMainPage();
    }

    @DataProvider (name = "UserId")
    public static Object[] usersId() {
        return UserInitializer.getUsersId().toArray();
    }

    @Description("In this text, the user logs in, goes to the profile and sets an avatar")
    @Test (dataProvider = "UserId")
    public void loginExistingUsers_AddAvatar(Integer userId) {
        mainPageUI.inputEmail(usersList.get(userId).getLogin());
        mainPageUI.inputPassword(usersList.get(userId).getPassword());
        mainPageUI.submit();
        mainPageUI.verifyIsOpened_correctName(usersList.get(userId).getFirstName(), usersList.get(userId).getFirstName());
        mainPageUI.openProfileMenu();
        profilePageUI.uploadProfileImage(AVATAR_IMG);
        profilePageUI.verifyPhotoDisplayedCorrectly_UI();
        tearDown();
    }

    private void tearDown() {
        turnOffDriver();
    }

}
