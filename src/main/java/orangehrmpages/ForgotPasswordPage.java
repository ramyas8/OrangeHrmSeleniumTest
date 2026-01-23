package orangehrmpages;

import orangehrmabstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ForgotPasswordPage extends AbstractComponents {

    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name ="username")
    WebElement fp_username;

    @FindBy(xpath = "//h6[text()='Reset Password']")
    WebElement resetPassword;

    public boolean isForgotPasswordPageDisplayed()
    {
        waitForElementToAppear(resetPassword);
        return resetPassword.isDisplayed();
    }

    public boolean isUsernameFieldDisplayed()
    {
        waitForElementToAppear(fp_username);
        return fp_username.isDisplayed();
    }
}
