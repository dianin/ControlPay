package Tests_API;

import Utils.TestListener;
import Utils.UserInitializer;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;

@Listeners(TestListener.class)
public class Test_Login_API extends BaseTest_API {

    private Response response;
    private String content = "grant_type=client_credentials";
    private static String access_token = null;
    private static String token_type = null;
    private static Map<String, String> requestBody;

    @DataProvider(name = "UserId")
    public static Object[] usersId() {
        return UserInitializer.getUsersId().toArray();
    }

    @Description("Validation of the access token during the login, the validity of permissions and validity of userProfile json")
    @Test(dataProvider = "UserId")
    public void login_VerifyPermissionAndProfile(Integer userId) {
        login(userId);

        response = createRequestForProfile(token_type, access_token);
        verifyStatusCode(response, 200);
        verifyUserProfile(response, userId);
    }

    private void login(Integer userId) {
        requestBody = createLoginBody(userId);
        response = createRequestForToken(requestBody, encode(content));
        verifyStatusCode(response, 200);
        access_token = verifyAccessToken_withExpires(response);
        token_type = getTokenType(response);
        verifyUserPermissions(access_token, userId);
    }

}
