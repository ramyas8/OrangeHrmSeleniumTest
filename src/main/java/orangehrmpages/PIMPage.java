package orangehrmpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PIMPage {

    WebDriver driver;

    public PIMPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement search_employeeId;

    @FindBy(xpath = "//button[text()=' Add ']")
    WebElement addButton;

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployee;

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeList;

    @FindBy(xpath = "//a[text()='Reports']")
    WebElement reports;

    @FindBy(name = "firstName")
    WebElement firstName;

    @FindBy(name = "middleName")
    WebElement middleName;

    @FindBy(name = "lastName")
    WebElement lastName;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement employeeID;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    WebElement loginDetailsRadioButton;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    WebElement username;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement password;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPassword;

    @FindBy(xpath = "//label[text()='Enabled']")
    WebElement enabledRadioButton;

    @FindBy(xpath = "//label[text()='Disabled']")
    WebElement disabledRadioButton;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='oxd-select-wrapper']")
    List<WebElement> searchDropDowns;

    @FindBy(xpath = "//li[@class='oxd-main-menu-item-wrapper']//span[text()='PIM']")
    WebElement pimPage;

    public void addEmployee(String fn, String mn, String ln, int id, String userName, String pass, String confirmPass) throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(addButton));
        addButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(firstName));

        firstName.sendKeys(fn);
        middleName.sendKeys(mn);
        lastName.sendKeys(ln);
       // employeeID.sendKeys(id);
        loginDetailsRadioButton.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfAllElements(username));
        username.sendKeys(userName);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirmPass);
        Thread.sleep(5000);
        saveButton.click();

    }

    public void searchEmployee() {

    }

}
