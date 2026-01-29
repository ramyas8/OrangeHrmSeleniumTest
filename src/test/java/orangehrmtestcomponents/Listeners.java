package orangehrmtestcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import orangehrmresources.ExtentReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporter.getReportObject();

    // This is the "Thread Safe" container for the tests
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        // Logic to make test names more descriptive in the report
        String testName = result.getMethod().getMethodName();
        if (result.getParameters().length > 0) {
            testName += " (" + result.getParameters()[0].toString() + ")";
        }

        ExtentTest test = extent.createTest(testName);
        extentTest.set(test); // Unique lane for this specific test execution
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        try {

            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e1) {
            // If reflection fails, we log it but don't crash the whole suite
            System.out.println("Listener could not find the driver field: " + e1.getMessage());
        }

        if (driver != null) {
            try {

                BaseTest bt = new BaseTest();
                String filePath = bt.getScreenshot(result.getMethod().getMethodName(), driver);
                extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}