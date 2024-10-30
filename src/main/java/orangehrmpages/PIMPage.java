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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @FindBy(xpath = "//a[text()='Reports']")
    WebElement reports;

    @FindBy(name = "firstName")
    WebElement firstNameEle;

    @FindBy(name = "middleName")
    WebElement middleNameEle;

    @FindBy(name = "lastName")
    WebElement lastNameEle;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement employeeIDEle;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    WebElement loginDetailsRadioButton;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    WebElement userNameEle;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement passwordEle;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPasswordEle;

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

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[2]")
    WebElement tb_empID;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[3]")
    WebElement tb_firstMiddleName;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[4]")
    WebElement tb_lastName;

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeList;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    WebElement personalDetails;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    WebElement table;

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pim;

    public void clickPimPage()
    {
        waitForElementToBeClickable(pim);
        pim.click();
    }

    public String getEmployeeId() {
        return employeeIDEle.getAttribute("value");
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void waitForPersonalDetails()
    {
        waitForElementToAppear(personalDetails);
    }


    public void addEmployee(String firstName, String middleName, String lastName, String userName,
                            String pass, String confirmPass, String empID) throws InterruptedException {

        waitForElementToAppear(addButton);
        addButton.click();
        waitForElementToAppear(firstNameEle);
        firstNameEle.sendKeys(firstName);
        middleNameEle.sendKeys(middleName);
        lastNameEle.sendKeys(lastName);
        loginDetailsRadioButton.click();
        waitForElementToAppear(userNameEle);
        userNameEle.sendKeys(userName);
        passwordEle.sendKeys(pass);
        confirmPasswordEle.sendKeys(confirmPass);
        //saveButton.click();

    }


    //Method to search for an employee by ID
    public void searchEmployeeWithId(String id) throws InterruptedException {

        waitForElementToAppear(searchEmployeeId);
        searchEmployeeId.sendKeys(id);
        searchButton.click();

    }

    //Method to get employee details from the search results
    public Map<String, String> getEmployeeDetailsFromTable() {
        Map<String, String> employeeDetails = new HashMap<>();

        // Split the string by space
        String[] parts = tb_firstMiddleName.getText().split(" ");
        System.out.println(Arrays.toString(parts));
        String tb_firstName = parts[0];
        System.out.println(tb_firstName);

        String tb_middleName = parts[1];
        employeeDetails.put("firstName", tb_firstName);
        employeeDetails.put("middleName", tb_middleName);
        employeeDetails.put("lastName", tb_lastName.getText());
        employeeDetails.put("employeeId", tb_empID.getText());
        return employeeDetails;
    }


    public void searchEmployeeWithName() {

        waitForElementToBeClickable(pim);
        pim.click();
        waitForElementToBeClickable(searchEmployeeName);
        searchEmployeeName.sendKeys();
        searchButton.click();

    }


}
