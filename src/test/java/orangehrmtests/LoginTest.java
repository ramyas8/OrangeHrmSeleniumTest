package orangehrmtests;

import orangehrmabstractcomponents.ExcelOperations;
import orangehrmpages.ForgotPasswordPage;
import orangehrmpages.LoginPage;
import orangehrmtestcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest extends BaseTest {


    @DataProvider(name = "LoginTestData")
    public Object[][] excelDataProvider() throws IOException {
        String fileName = "OrangeHrmData.xlsx";
        String sheetName = "LoginTestData"; // Specify the sheet name for valid login data
        ExcelOperations excOps = new ExcelOperations(fileName, sheetName);
        return excOps.getData();

    }

    @Test(dataProvider = "LoginTestData")
    public void testLogin(String testCaseName, String username, String password, String expectedMessage) throws IOException, InterruptedException {

        System.out.println("Executing Test: " + testCaseName);
        loginPage.login(username, password);

        if (expectedMessage.equalsIgnoreCase("Success")) {
            Assert.assertTrue(loginPage.isDashboardDisplayed(), "Login failed for: " + testCaseName);
        } else {
            // Check if error is due to both fields being blank
            if (username.isEmpty() && password.isEmpty()) {
                Assert.assertEquals(loginPage.getUsernameRequiredMessage(), expectedMessage, "Expected 'Required' for blank username." + testCaseName);
                Assert.assertEquals(loginPage.getPasswordRequiredMessage(), expectedMessage, "Expected 'Required' for blank password." + testCaseName);
            }
            // Check if only username is blank
            else if (username.isEmpty()) {
                Assert.assertEquals(loginPage.getUsernameRequiredMessage(), expectedMessage, "Expected 'Required' for blank username." + testCaseName);
            }
            // Check if only password is blank
            else if (password.isEmpty()) {
                Assert.assertEquals(loginPage.getPasswordRequiredMessage(), expectedMessage, "Expected 'Required' for blank password." + testCaseName);
            }
            // For invalid username and/or password
            else {
                Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Invalid credentials alert failure in: " + testCaseName);
            }
        }
    }

    @Test
    public void testPasswordFieldIsMasked() {

        String fieldType = loginPage.isPasswordFieldMasked();
        Assert.assertEquals(fieldType, "password", "Password field is not masked!");
    }

    @Test
    public void testForgotPasswordLink() {

        // Click on the 'Forgot Password' link
        loginPage.clickForgotYourPassword();

        // Verify that the user is redirected to the 'Forgot Password' page
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(), "Forgot Password page is not displayed!");
        Assert.assertTrue(forgotPasswordPage.isUsernameFieldDisplayed(), "Username field is not displayed on Forgot Password page!");


    }
}
