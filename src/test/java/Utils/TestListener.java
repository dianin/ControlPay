package Utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Path;


import static Utils.DriverHandler.getDriver;

public class TestListener implements ITestListener, IRetryAnalyzer {

    private int retryCount;
    private int maxRetryCount = 1;
    private static Path pathToLogs;
    private static boolean toSetAnalyzer = true;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxRetryCount) {
            iTestResult.getThrowable().printStackTrace();
            System.out.println(
                    "Rerun method " + iTestResult.getMethod().getTestClass().getRealClass().getSimpleName()
                            + "." + iTestResult.getMethod().getMethodName());
            retryCount++;
            return true;
        }
        return false;
    }

    public static boolean toSetRetry() {
        if (toSetAnalyzer) {
            toSetAnalyzer = false;
            return true;
        } else {
            return false;
        }
    }

    @Attachment(value = "page screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Started: ");
        System.out.println(methodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Success: ");
        System.out.println(methodName(iTestResult));
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Failed: ");
        System.out.println(methodName(iTestResult));
        takeScreenshot();

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test Skipped: ");
        methodName(iTestResult);
        takeScreenshot();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test Skipped: ");
        methodName(iTestResult);
        takeScreenshot();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Suite started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Suite finished");
    }

    private static String methodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getTestClass().getRealClass().getSimpleName() + "." + iTestResult.getMethod().getMethodName();
    }


}
