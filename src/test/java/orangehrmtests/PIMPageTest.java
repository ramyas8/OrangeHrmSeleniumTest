package orangehrmtests;

import base.Base;

import orangehrmpages.HomePage;
import orangehrmpages.LoginPage;
import org.testng.annotations.Test;


public class PIMPageTest extends Base {

    @Test
    public void addEmp() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        HomePage homePage = new HomePage(driver);
        homePage.clickPIM();

        orangehrmpages.PIMPage pimPage = new orangehrmpages.PIMPage(driver);
        try {
            pimPage.addEmployee("Testfn", "testmn", "testln", 123, "testun", "testpass", "testpass");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
