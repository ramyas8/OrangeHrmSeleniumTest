package orangehrmtests;

import base.Base;
import orangehrmpages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends Base {

    @Test
    public void testLogin() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
    }
}
