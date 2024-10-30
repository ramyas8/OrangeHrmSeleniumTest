package orangehrmtestcomponents;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import orangehrmresources.ExtentReporter;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

        ExtentTest test;
       ExtentReports extent = ExtentReporter.getReportObject();

        public void onTestStart(ITestResult result) {
          test = extent.createTest(result.getMethod().getMethodName());
        }

        public void onTestSuccess(ITestResult result) {
            test.log(Status.PASS, "Test Passed");
        }

        public void onTestFailure(ITestResult result) {
            test.fail(result.getThrowable());

            Object testClass = result.getInstance();
            driver = ((BaseTest) testClass).driver;
            String filePath = null;
            try {
                filePath = getScreenshot(result.getMethod().getMethodName(),driver);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        }

        public void onTestSkipped(ITestResult result) {
        }

        public void onStart(ITestContext context) {

        }

        public void onFinish(ITestContext context) {
            extent.flush();
        }
    }
