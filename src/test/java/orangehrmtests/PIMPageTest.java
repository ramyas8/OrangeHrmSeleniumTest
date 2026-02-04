package orangehrmtests;

import orangehrmabstractcomponents.ExcelOperations;
import orangehrmpages.LoginPage;
import orangehrmpages.PIMPage;
import orangehrmtestcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class PIMPageTest extends BaseTest {

    PIMPage pimPage;
    ExcelOperations excOps;

    @DataProvider(name = "PIMPageTestData")
    public Object[][] excelDataProvider() throws IOException {
        String fileName = "OrangeHrmData.xlsx";
        String sheetName = "PIMPageTestData"; // Specify the sheet name for valid login data
        excOps = new ExcelOperations(fileName, sheetName);
        return excOps.getData();

    }

    @Test(dataProvider = "PIMPageTestData", priority = 1)
    public void addEmp(String firstName, String middleName, String lastName, String userName, String password, String confirmPassword,
                       String jobTitle, String employmentStatus, String terminationDate, String terminationReason, String employeeId)
            throws InterruptedException, IOException {

        loginAndNavigateTo("PIM", prop.getProperty("username"), prop.getProperty("password"));
        pimPage = new PIMPage(driver);
        pimPage.addEmployee(firstName, middleName, lastName, userName, password, confirmPassword,
                jobTitle, employmentStatus, terminationDate, terminationReason, employeeId);
        String newId = pimPage.getEmployeeId();
        Assert.assertNotNull(newId, "Failed to capture Employee ID from the UI!");
        int rowIndex = excOps.getRowNumber(firstName, lastName);
        excOps.setCellData(rowIndex, excOps.getColumnIndex("employeeId"), newId);
        pimPage.clickSaveButton();
        Assert.assertTrue(pimPage.isPersonalDetailsDisplayed(), "Failed to redirect to Personal Details after adding employee!");

    }

    @Test(dataProvider = "PIMPageTestData", priority = 2, dependsOnMethods = "addEmp")
    public void updateEmpDetails(String firstName, String middleName, String lastName, String userName, String password,
                                 String confirmPassword, String jobTitle, String employmentStatus, String terminationDate, String terminationReason, String employeeId)
            throws InterruptedException, IOException {
        loginAndNavigateTo("PIM", prop.getProperty("username"), prop.getProperty("password"));
        pimPage = new PIMPage(driver);
        // Uses existingId from Excel (column 10)
        pimPage.searchEmployeeWithId(employeeId);
        pimPage.updateEmployeeDetails(jobTitle, employmentStatus);
        String toastMessage = pimPage.getToastMessage();
        Assert.assertTrue(toastMessage.contains("Successfully Updated"), "Update toast message not displayed!");

    }

    @Test(dataProvider = "PIMPageTestData", priority = 3, dependsOnMethods = "updateEmpDetails")
    public void terminateEmployee(String firstName, String middleName, String lastName, String userName, String password,
                                  String confirmPassword, String jobTitle, String employmentStatus, String terminationDate, String terminationReason, String employeeId)
            throws InterruptedException, IOException {
        loginAndNavigateTo("PIM", prop.getProperty("username"), prop.getProperty("password"));
        pimPage = new PIMPage(driver);
        pimPage.searchEmployeeWithId(employeeId);
        pimPage.terminateEmployee(terminationDate, terminationReason);
        Assert.assertEquals(pimPage.getToastMessage(), "Successfully Updated", "Termination failed!");
    }


}
