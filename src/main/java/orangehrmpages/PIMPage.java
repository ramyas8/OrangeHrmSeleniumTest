package orangehrmpages;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import orangehrmabstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> allRows;

    @FindBy(xpath = "//p[text()='Time at Work']")
    WebElement time;
//    @FindBy(xpath = "//span[text()='PIM']")
//    WebElement pim;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[2]")
    WebElement pim;

    public void addEmployee(String fn, String mn, String ln, String userName,
                            String pass, String confirmPass) throws InterruptedException {

        System.out.println("Reached the method");

        if (driver == null) {
            System.out.println("WebDriver is null!");
            return;
        } else {
            System.out.println("WebDriver is initialized properly.");
        }


        // Wait for the 'Time at Work' element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Check if the element is found and clickable
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Time at Work']")));
        if (element != null) {
            System.out.println("Time at Work element found");
            element.click();
        } else {
            System.out.println("Element not found or null");
            return; // Exit the method if element is null
        }

        // Check if 'time' is initialized properly
        if (time == null) {
            System.out.println("Time element is null");
        } else {
            waitForElementToAppear(time);
            System.out.println("Time element text: " + time.getText());
        }
        waitForElementToAppear(time);
        System.out.println(time.getText());
        waitForElementToBeClickable(pim);
        pim.click();
        waitForElementToAppear(addButton);
        addButton.click();
        waitForElementToAppear(firstName);
        firstName.sendKeys(fn);
        middleName.sendKeys(mn);
        lastName.sendKeys(ln);
        loginDetailsRadioButton.click();
        waitForElementToAppear(username);
        username.sendKeys(userName);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirmPass);
        saveButton.click();

    }

    public void searchEmployeeWithId(String id) throws InterruptedException {

        waitForElementToBeClickable(pim);
        pim.click();
        waitForElementToAppear(searchEmployeeId);
        searchEmployeeId.sendKeys(id);
        searchButton.click();
        Thread.sleep(5000);

    }

    public void searchEmployeeWithName() {

        waitForElementToBeClickable(pim);
        pim.click();
        waitForElementToBeClickable(searchEmployeeName);
        searchEmployeeName.sendKeys();
        searchButton.click();

    }


}
