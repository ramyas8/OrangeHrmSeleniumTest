package orangehrmpages;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import orangehrmabstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PIMPage extends AbstractComponents {

    WebDriver driver;

    public PIMPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement searchEmployeeId;

    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    WebElement searchEmployeeName;

    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[2]")
    WebElement searchJobTitle;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchButton;

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

//    @FindBy(xpath = "//span[text()='PIM']")
//    WebElement pim;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[2]")
    WebElement pim;

    public void addEmployee(String fn, String mn, String ln, int id, String userName, String pass, String confirmPass) throws InterruptedException {

        System.out.println("Reached add employee method and waiting for PIM");

        if (pim != null) {
            System.out.println("PIM element is not null, attempting to click");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(pim));  // Wait for PIM to be clickable
            pim.click();
        } else {
            System.out.println("PIM element is null, check locator or initialization");
        }




        //pim.click();
        System.out.println("clicked on pim");
        waitForElementToAppear(addButton);
        addButton.click();
        waitForElementToAppear(firstName);
        firstName.sendKeys(fn);
        middleName.sendKeys(mn);
        lastName.sendKeys(ln);
       // employeeID.sendKeys(id);
        loginDetailsRadioButton.click();
        waitForElementToAppear(username);
        username.sendKeys(userName);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirmPass);
        saveButton.click();

    }

    public void searchEmployeeWithId() {

        searchEmployeeId.sendKeys();
        searchButton.click();
    }

    public void searchEmployeeWithName() {
        searchEmployeeName.sendKeys();
        searchButton.click();

    }

    public void searchWithNameAndId() {
        searchEmployeeId.sendKeys();
        searchEmployeeName.sendKeys();
        searchButton.click();
    }

    public void searchWithJobTitle() {
        searchJobTitle.sendKeys();
        searchButton.click();
    }

}
