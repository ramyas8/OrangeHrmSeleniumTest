package orangehrmtests;

import orangehrmtestcomponents.BaseTest;
import orangehrmpages.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() throws IOException, InterruptedException {

        loginPage.login("Admin", "admin123");
    }
}
