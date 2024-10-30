package orangehrmtests;

import com.aventstack.extentreports.Status;
import orangehrmabstractcomponents.ExcelOperations;
import orangehrmtestcomponents.BaseTest;
import orangehrmpages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    private static final String excelFilePath = "//Users//ramyasri//Downloads//OrangeHRMTestData.xlsx";

    @DataProvider(name = "ValidLoginData")
    public Object[][] excelDataProvider() throws IOException {

        String sheetName = "ValidLoginData"; // Specify the sheet name for valid login data
        ExcelOperations excOps = new ExcelOperations(excelFilePath, sheetName);
        return excOps.getData(excelFilePath, sheetName);

    }

    @DataProvider(name = "InvalidLoginData")
    public Object[][] invalidLoginData() throws IOException {

        String sheetName = "InvalidLoginData"; //Specify the sheet name for valid login data
        ExcelOperations excOps = new ExcelOperations(excelFilePath, sheetName);
        return excOps.getData(excelFilePath, sheetName);

    }


    @Test(dataProvider = "ValidLoginData")
    public void testLogin(String username, String password) throws IOException, InterruptedException {

        loginPage.login(username,password);
        Assert.assertTrue(loginPage.isDashboardDisplayed());

    }

    @Test(dataProvider = "InvalidLoginData")
    public void testInvalidLogin(String username, String password) throws InterruptedException {

        loginPage.login(username,password);
        Assert.assertEquals("Invalid credentials", loginPage.getErrorMessage());

   }

    @Test(dataProvider = "InvalidLoginData")
    public void tesEmptyUserName(String username, String password) throws InterruptedException {

        loginPage.login(username,password);
        Assert.assertTrue(loginPage.getUserRequiredMessage());

    }

    @Test(dataProvider = "InvalidLoginData")
    public void tesEmptyPassword(String username, String password) throws InterruptedException {

        loginPage.login(username,password);
        Assert.assertTrue(loginPage.getPasswordRequiredMessage());

    }




}
