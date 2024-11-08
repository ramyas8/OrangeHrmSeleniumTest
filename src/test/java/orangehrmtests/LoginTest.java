package orangehrmtests;

import com.aventstack.extentreports.Status;
import orangehrmabstractcomponents.ExcelOperations;
import orangehrmpages.ForgotPasswordPage;
import orangehrmtestcomponents.BaseTest;
import orangehrmpages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    private static final String excelFilePath = "//Users//ramyasri//Downloads//OrangeHRMTestData.xlsx";

    @DataProvider(name = "LoginTestData")
    public Object[][] excelDataProvider() throws IOException {

        String sheetName = "LoginTestData"; // Specify the sheet name for valid login data
        ExcelOperations excOps = new ExcelOperations(excelFilePath, sheetName);
        return excOps.getData(excelFilePath, sheetName);

    }

    @Test(dataProvider = "LoginTestData")
    public void testLogin(String username, String password, String expectedMessage) throws IOException, InterruptedException {

        loginPage.login(username, password);

        if (expectedMessage.equalsIgnoreCase("Success")) {
            Assert.assertTrue(loginPage.isDashboardDisplayed());
        } else {
            // Check if error is due to both fields being blank
            if (username.isEmpty() && password.isEmpty()) {
                Assert.assertEquals(loginPage.getUsernameRequiredMessage(), "Required", "Expected 'Required' for blank username.");
                Assert.assertEquals(loginPage.getPasswordRequiredMessage(), "Required", "Expected 'Required' for blank password.");
            }
            // Check if only username is blank
            else if (username.isEmpty()) {
                Assert.assertEquals(loginPage.getUsernameRequiredMessage(), "Required", "Expected 'Required' for blank username.");
            }
            // Check if only password is blank
            else if (password.isEmpty()) {
                Assert.assertEquals(loginPage.getPasswordRequiredMessage(), "Required", "Expected 'Required' for blank password.");
            }
            // For invalid username and/or password
            else {
                Assert.assertEquals("Invalid credentials", loginPage.getErrorMessage());
            }
        }
    }

    @Test
    public void testPasswordFieldIsMasked() {

        // Verify if the 'type' attribute of the password field is set to 'password'
        String fieldType = loginPage.isPasswordFieldMasked();
        Assert.assertEquals(fieldType, "password", "Password field is not masked!");
    }

    @Test
    public void testForgotPasswordLink()
    {

        LoginPage loginPage = new LoginPage(driver);

        // Click on the 'Forgot Password' link
        loginPage.clickForgotYourPassword();

        // Verify that the user is redirected to the 'Forgot Password' page
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(), "Forgot Password page is not displayed!");
        Assert.assertTrue(forgotPasswordPage.isUsernameFieldDisplayed(), "Username field is not displayed on Forgot Password page!");


    }
}
