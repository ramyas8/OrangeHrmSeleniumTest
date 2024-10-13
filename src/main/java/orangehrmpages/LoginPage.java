package orangehrmpages;

import orangehrmabstractcomponents.AbstractComponents;
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

    public void login(String username, String password) throws InterruptedException {
        userName.sendKeys(username);
        passwordEle.sendKeys(password);
        loginButton.click();
        Thread.sleep(5000);
    }

    public void goTo()
    {
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

}
