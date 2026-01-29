package orangehrmtestcomponents;

import orangehrmpages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;
    Properties prop;


    public void loadProperties() throws IOException {
        if (prop == null) {
            prop = new Properties();
            try (InputStream fis = getClass().getClassLoader().getResourceAsStream("GlobalData.properties")) {
                if (fis != null) {
                    prop.load(fis);
                } else {
                    throw new FileNotFoundException("Property file not found in classpath");
                }
            }
        }
    }


    public WebDriver initializeDriver() throws InterruptedException, IOException {

        loadProperties();

        // 1. Get browser from System (Terminal) -> then Properties file -> default to "chrome"
        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : prop.getProperty("browser", "chrome"); // "chrome" is the fallback default

        // 2. Check for Headless mode from Terminal
        String headless = System.getProperty("headless") != null
                ? System.getProperty("headless")
                : "false";

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new"); // Runs without a window
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless");
            }
            driver = new EdgeDriver(options);

        } else {
            throw new RuntimeException("Browser '" + browserName + "' not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png";
        File file = new File(destinationPath);
        FileUtils.copyFile(source, file);

        return testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException, InterruptedException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        String user = prop.getProperty("username");
        String pass = prop.getProperty("password");
        loginPage.login(user, pass);
    }

//    public void doLogin() throws IOException, InterruptedException {
//
//        loadProperties();
//        String user = prop.getProperty("username");
//        String pass = prop.getProperty("password");
//        loginPage.login(user, pass);
//
//    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


    }
}
