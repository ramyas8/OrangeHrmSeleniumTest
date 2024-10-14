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

        loginPage.login("Admin", "admin123");

    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        loginPage.login("Admin","admin");
        Assert.assertEquals("Invalid credentials", loginPage.getErrorMessage());

    }
}
