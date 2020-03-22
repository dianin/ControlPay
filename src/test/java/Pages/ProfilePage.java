package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends CommonMethods_UI {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    By uploadImageButton = By.xpath("//input[@type='file' and @name='fileUpload']");
    By avatarPicture = By.id("image");

    public static final String AVATAR_IMG = "src/picture.jpg";

    @Step
    public void uploadProfileImage(String pathToPhoto) {
        uploadPhoto(uploadImageButton, pathToPhoto);
    }

    @Step
    public void verifyPhotoDisplayedCorrectly_UI() {
        verifySizeOfImage_Attributes(avatarPicture, 300, 300);
    }


}
