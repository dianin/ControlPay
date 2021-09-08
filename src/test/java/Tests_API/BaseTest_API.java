package Tests_API;

import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Jwts;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import static Utils.UserInitializer.usersList;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class BaseTest_API {

    private static String BASE_URI = "https://testapp.com";

    public String encode(String stringToEncode) {
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    }

    @Step
    public static void verifyStatusCode(Response response, int expectedCode) {
        Assert.assertEquals(response.getStatusCode(), expectedCode);
    }

    @Step
    public static void verifyUserProfile(Response response, Integer userId) {
        Assert.assertEquals(response.getBody().jsonPath().get("firstName"), usersList.get(userId).getFirstName());
        Assert.assertEquals(response.getBody().jsonPath().get("lastName"), usersList.get(userId).getLastName());
        Assert.assertEquals(response.getBody().jsonPath().get("email"), usersList.get(userId).getLogin());
        Assert.assertEquals(response.getBody().jsonPath().get("age"), usersList.get(userId).getAge());
        Assert.assertEquals(response.getBody().jsonPath().get("profilePicture"), "picture.jpg");
        Assert.assertEquals(response.getBody().jsonPath().get("userRole"), usersList.get(userId).getUserRole());
    }

    @Step
    public static Map<String, String> createLoginBody(Integer userId) {
        Map<String, String> request = new HashMap<>();
        request.put("login", usersList.get(userId).getLogin());
        request.put("password", usersList.get(userId).getPassword());
        return request;
    }

    @Step
    public String verifyAccessToken_withExpires(Response response) {
        assertEquals(response.getBody().jsonPath().get("expires_in"), "3600");
        String access_token = response.getBody().jsonPath().get("access_token");
        Assert.assertTrue(verifyAccessToken(access_token));
        return access_token;
    }

    @Step
    public static String getTokenType(Response response) {
        return response.getBody().jsonPath().get("token_type");
    }

    public static String[] getPermissions_ByToken(String access_token) {
        return (String[]) Jwts.parser().setSigningKey("secret").parseClaimsJws(access_token).getBody().get("permission");
    }

    public static String[] getPermissions_ByUserId(int userId) {
        return (String[]) usersList.get(userId).getPermissions().toArray();
    }

    @Step
    public static void verifyUserPermissions(String access_token, Integer userId) {
        Assert.assertEqualsNoOrder(getPermissions_ByToken(access_token), getPermissions_ByUserId(userId));
    }

    @Step
    public static Response createRequestForToken(Map<String, String> request, String header) {
        RestAssured.baseURI = BASE_URI;
        Response response;
        response = given()
                .body(request)
                .header("Authorization", header)
                .when()
                .expect()
                .statusCode(200)
                .when()
                .post("/token");
        return response;
    }

    @Step
    public static Response createRequestForProfile(String token_type, String access_token) {
        RestAssured.baseURI = BASE_URI;
        Response response;
        response = given()
                .header("Authorization", token_type + " " + access_token)
                .when()
                .get("/profile");
        return response;
    }

    public boolean verifyAccessToken(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
                    .parseClaimsJws(jwt).getBody();
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

}
