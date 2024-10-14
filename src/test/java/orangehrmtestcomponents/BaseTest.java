package orangehrmtestcomponents;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import orangehrmpages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {

    protected WebDriver driver;
    public LoginPage loginPage;

    public WebDriver initializeDriver() throws InterruptedException, IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//GlobalData.Properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();

        }
        else if (browserName.equalsIgnoreCase("firefox"))
        {

        }
        else if (browserName.equalsIgnoreCase("edge"))
        {

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
    }

    @BeforeMethod
    public LoginPage launchApplication() throws IOException, InterruptedException
    {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        return loginPage;

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


    }
}
