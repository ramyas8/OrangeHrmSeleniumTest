package orangehrmtests;

import orangehrmabstractcomponents.ExcelOperations;
import orangehrmtestcomponents.BaseTest;

import orangehrmpages.LoginPage;
import orangehrmpages.PIMPage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;


public class PIMPageTest extends BaseTest {

    PIMPage pimPage = new PIMPage(driver);
    ExcelOperations excel = new ExcelOperations();
    ArrayList data = excel.getData("PIMPageTest");

    public PIMPageTest() throws IOException {
    }

    @Test
    public void addEmp() throws InterruptedException, IOException {

        loginPage.login("Admin", "admin123");
        try
        {
            pimPage.addEmployee("Testfn", "testmn", "testln", 123, "testun", "testpass", "testpass");
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

  //  @Test
  // public void searchEmployeeWithValidId() {

       // pimPage.searchEmployeeWithId();
   // }


}
