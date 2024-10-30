package orangehrmtests;

import orangehrmabstractcomponents.ExcelOperations;
import orangehrmtestcomponents.BaseTest;

import orangehrmpages.LoginPage;
import orangehrmpages.PIMPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class PIMPageTest extends BaseTest {

    PIMPage pimPage;
    ExcelOperations excOps;
    String empIdValue;

    private static final String excelFilePath = "//Users//ramyasri//Downloads//OrangeHRMTestData.xlsx";

    @DataProvider(name = "PIMPageTestData")
    public Object[][] excelDataProvider() throws IOException {

        String sheetName = "PIMPageTestData"; // Specify the sheet name for valid login data
        excOps = new ExcelOperations(excelFilePath, sheetName);
        return excOps.getData(excelFilePath, sheetName);

    }

    @Test(dataProvider = "PIMPageTestData")
    public void addEmp(String firstName, String middleName, String lastName, String userName, String password, String confirmPassword, String empId)
            throws InterruptedException, IOException {

        pimPage = new PIMPage(driver);
        doLogin();
        LoginPage login = new LoginPage(driver);
        login.isDashboardDisplayed();
        Assert.assertTrue(loginPage.isDashboardDisplayed());
        pimPage.clickPimPage();
        try
        {
            pimPage.addEmployee(firstName, middleName, lastName, userName, password, confirmPassword, empId);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        empIdValue = pimPage.getEmployeeId();
        System.out.println("Employee ID is " + empIdValue);
        // Get the row number corresponding to the current test data set
        int rowIndex = excOps.getRowNumber(firstName, lastName);

        if (rowIndex != -1) {
            // Get the column index for "Employee ID" dynamically
            int empIdColumnIndex = excOps.getColumnIndex("employeeId"); // Adjust the header name as per your sheet

            if (empIdColumnIndex != -1) { // Check if column exists
                // Write Employee ID to the determined row and column
                excOps.setCellData(rowIndex, empIdColumnIndex, empIdValue);
            } else {
                System.out.println("Employee ID column not found!");
            }
        } else {
            System.out.println("Row for the employee not found!");
        }
        pimPage.clickSaveButton();
        pimPage.waitForPersonalDetails();
    }

    @Test (dependsOnMethods = "addEmp", dataProvider = "PIMPageTestData")
    public void searchEmployeeWithValidId(String firstName, String middleName, String lastName, String userName, String password,
                                          String confirmPassword, String empId) throws InterruptedException, IOException {

        pimPage = new PIMPage(driver);
        doLogin();
        LoginPage login = new LoginPage(driver);
        login.isDashboardDisplayed();
        Assert.assertTrue(loginPage.isDashboardDisplayed());
        pimPage.clickPimPage();
        pimPage.searchEmployeeWithId(empIdValue);
        Map<String, String> displayedEmployeeDetails = pimPage.getEmployeeDetailsFromTable(); //Get employee details from the table

        Assert.assertEquals(displayedEmployeeDetails.get("firstName"), firstName, "First name does not match");
        Assert.assertEquals(displayedEmployeeDetails.get("middleName"), middleName, "First name does not match");
        Assert.assertEquals(displayedEmployeeDetails.get("lastName"), lastName, "Last name does not match");
        Assert.assertEquals(displayedEmployeeDetails.get("employeeId"), empId, "Employee ID does not match");



    }


}
