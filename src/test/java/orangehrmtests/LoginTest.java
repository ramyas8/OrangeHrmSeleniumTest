package orangehrmtests;

import com.aventstack.extentreports.Status;
import orangehrmtestcomponents.BaseTest;
import orangehrmpages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() throws IOException, InterruptedException {

        test = extent.createTest("Login Test - Valid Login");

        test.log(Status.INFO, "Launching the browser and navigating to the login page");

        loginPage.login("Admin", "admin123");

    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        loginPage.login("Admin","admin");
        Assert.assertEquals("Invalid credentials", loginPage.getErrorMessage());

    }
}
