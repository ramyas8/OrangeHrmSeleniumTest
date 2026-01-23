package orangehrmpages;

import orangehrmabstractcomponents.AbstractComponents;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    WebElement userName;

    @FindBy(name = "password")
    WebElement passwordEle;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    WebElement errorMessage;

    @FindBy(xpath = "//input[@name='username']/parent::div/following-sibling::span")
    WebElement userNameRequiredMessage;

    @FindBy(xpath = "//input[@name='password']/parent::div/following-sibling::span")
    WebElement passwordRequiredMessage;

    @FindBy(xpath = "//span[text()='Dashboard']")
    WebElement dashboard;

    @FindBy(xpath = "//p[text()='Forgot your password? ']")
    WebElement forgotYourPassword;

    public void login(String username, String password) throws InterruptedException {

        userName.sendKeys(username);
        passwordEle.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage()
    {
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public String getUsernameRequiredMessage()
    {
        waitForElementToAppear(userNameRequiredMessage);
        return userNameRequiredMessage.getText();
    }

    public String getPasswordRequiredMessage()
    {
        waitForElementToAppear(passwordRequiredMessage);
        return passwordRequiredMessage.getText();
    }


    public boolean isDashboardDisplayed()
    {
        waitForElementToAppear(dashboard);
        return dashboard.isDisplayed();
    }

    public @Nullable String isPasswordFieldMasked()
    {
        waitForElementToAppear(passwordEle);
        return passwordEle.getAttribute("type");
    }

    public void clickForgotYourPassword()
    {
        forgotYourPassword.click();
    }

    public void goTo()
    {
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

}
