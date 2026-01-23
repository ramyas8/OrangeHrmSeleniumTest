package orangehrmtestcomponents;

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
    Properties prop;
    FileInputStream fis;

    public void loadProperties() throws IOException {
        if (prop == null) {  // To ensure properties are loaded only once
            prop = new Properties();
            fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties");
            prop.load(fis);
        }
    }


    public WebDriver initializeDriver() throws InterruptedException, IOException {

        loadProperties();
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();

        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            // Initialize Firefox Driver (use WebDriverManager or GeckoDriver)
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            // Initialize Edge Driver (use WebDriverManager or EdgeDriver)
        }
        else {
            throw new RuntimeException("Browser not supported");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return testCaseName + ".png";

    }

    @BeforeMethod
    public LoginPage launchApplication() throws IOException, InterruptedException
    {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        return loginPage;
    }

    public void doLogin() throws IOException, InterruptedException {

        loadProperties();
        String user = prop.getProperty("username");
        String pass = prop.getProperty("password");
        loginPage.login(user,pass);

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


    }
}
