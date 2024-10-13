package orangehrmtests;

import orangehrmabstractcomponents.ExcelOperations;
import orangehrmtestcomponents.BaseTest;

import orangehrmpages.LoginPage;
import orangehrmpages.PIMPage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;


public class PIMPageTest extends BaseTest {

    PIMPage pimPage;
   // ExcelOperations excel = new ExcelOperations();
   // ArrayList data = excel.getData("PIMPageTest");

    public PIMPageTest() throws IOException {
    }

    @Test
    public void addEmp() throws InterruptedException, IOException {

        loginPage.login("Admin", "admin123");

        pimPage = new PIMPage(driver);
        try
        {
            pimPage.addEmployee("Testfn", "testmn", "testln",  "testun", "testpass", "testpass");
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

  @Test
  public void searchEmployeeWithValidId() throws InterruptedException {

        loginPage.login("Admin","admin123");
        pimPage = new PIMPage(driver);
        pimPage.searchEmployeeWithId("123");

    }


}
