package orangehrmpages;

import orangehrmabstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PIMPage extends AbstractComponents {

    WebDriver driver;

    public PIMPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pim;

    @FindBy(xpath = "//button[text()=' Add ']")
    WebElement addButton;

    @FindBy(name = "firstName")
    WebElement firstNameEle;

    @FindBy(xpath = "//input[@name='firstName']/parent::div/following-sibling::span")
    WebElement firstNameRequireMessage;

    @FindBy(name = "middleName")
    WebElement middleNameEle;

    @FindBy(name = "lastName")
    WebElement lastNameEle;

    @FindBy(xpath = "//input[@name='lastName']/parent::div/following-sibling::span")
    WebElement lastNameRequiredMessage;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div")
    WebElement employeeIdEle;

    @FindBy(xpath = "//span[text()='Employee Id already exists']")
    WebElement employeeIdExistsMessage;

    @FindBy(xpath = "//p[text()='Create Login Details']/following-sibling::div")
    WebElement loginDetailsRadioButton;

    @FindBy(xpath = "(//label[text()='Username']/parent::div/following-sibling::div")
    WebElement userNameEle;

    @FindBy (xpath = "//label[text()='Username']/parent::div/following-sibling::span")
    WebElement usernameRequiredMessage;

    @FindBy(xpath = "(//label[text()='Password']/parent::div/following-sibling::div")
    WebElement passwordEle;

    @FindBy(xpath = "//label[text()='Password']/parent::div/following-sibling::span")
    WebElement passwordRequiredMessage;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::div")
    WebElement confirmPasswordEle;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::span")
    WebElement confirmPasswordRequiredMessage;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div")
    WebElement search_EmployeeId;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchButton;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-pencil-fill'])[1]")
    WebElement editIcon;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    WebElement personalDetails;

    @FindBy(xpath = "//h6[text()='Job Details']")
    WebElement jobDetails;

    @FindBy(xpath = "//a[text()='Job']")
    WebElement lp_job;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement jobTitleDropdown;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[5]")
    WebElement employmentStatusDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement job_SaveButton;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[2]")
    WebElement tb_empID;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[3]")
    WebElement tb_firstAndMiddleName;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[4]")
    WebElement tb_lastName;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[5]")
    WebElement tb_jobTitle;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[6]")
    WebElement tb_employmentStatus;

    @FindBy(xpath = "(//button[@title='Terminate Employment'])")
    WebElement terminateEmploymentButton;

    @FindBy(xpath = "//p[text()='Terminate Employment']")
    WebElement terminateEmploymentPopup;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
    WebElement terminationDateEle;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[6]")
    WebElement terminationReasonEle;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    WebElement terminationSaveButton;


    public void clickPimPage()
    {
        waitForElementToBeClickable(pim);
        pim.click();
    }

    public void clickAddButton()
    {
        waitForElementToAppear(addButton);
        addButton.click();
    }

    public void enterFirstName(String firstName)
    {
        waitForElementToAppear(firstNameEle);
        firstNameEle.sendKeys(firstName);

    }

    public void enterMiddleName(String middleName)
    {
        waitForElementToAppear(middleNameEle);
        middleNameEle.sendKeys(middleName);
    }

    public void enterLastName(String lastName)
    {
        waitForElementToAppear(lastNameEle);
        lastNameEle.sendKeys(lastName);
    }

    public void clickLoginDetailsRadioButton()
    {
        waitForElementToAppear(loginDetailsRadioButton);
        loginDetailsRadioButton.click();
    }

    public void enterUsername(String username)
    {
        waitForElementToAppear(userNameEle);
        userNameEle.sendKeys(username);
    }

    public void enterPassword(String password)
    {
        waitForElementToAppear(passwordEle);
        passwordEle.sendKeys(password);

    }

    public void enterConfirmPassword(String confirmPassword)
    {
        waitForElementToAppear(confirmPasswordEle);
        confirmPasswordEle.sendKeys(confirmPassword);
    }

    public String getEmployeeId() {
        return employeeIdEle.getAttribute("value");
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void searchEmployeeWithId(String id) throws InterruptedException {

        waitForElementToAppear(search_EmployeeId);
        search_EmployeeId.sendKeys(id);
    }

    public void clickSearchButton()
    {
        waitForElementToAppear(searchButton);
        searchButton.click();
    }

    public void clickEditIcon()
    {
        waitForElementToAppear(editIcon);
        editIcon.click();
        waitForElementToAppear(personalDetails);
    }

    public void waitForPersonalDetails() //remove this method later after modifying the tests
    {
        waitForElementToAppear(personalDetails);
    }

    public void clickJobPage()
    {
        waitForElementToAppear(lp_job);
        lp_job.click();
        waitForElementToAppear(jobDetails);
    }

    public void enterJobTitle(String jobTitle)
    {
        waitForElementToAppear(jobTitleDropdown);
        jobTitleDropdown.sendKeys(jobTitle);
    }

    public void enterEmploymentStatus(String employmentStatus)
    {
        waitForElementToAppear(employmentStatusDropdown);
        employmentStatusDropdown.sendKeys(employmentStatus);
    }

    public void clickJobSaveButton()
    {
        waitForElementToAppear(job_SaveButton);
        job_SaveButton.click();
    }

    public void clickTerminateEmployeeButton()
    {
        waitForElementToAppear(terminateEmploymentButton);
        terminateEmploymentButton.click();
        waitForElementToAppear(terminateEmploymentPopup);
    }

    public void enterTerminationDate(String terminationDate)
    {
        waitForElementToAppear(terminationDateEle);
        terminationDateEle.sendKeys(terminationDate);
    }

    public void enterTerminationReason(String terminationReason)
    {
        waitForElementToAppear(terminationReasonEle);
        terminationReasonEle.sendKeys(terminationReason);
    }

    public void clickTerminationSaveButton()
    {
        waitForElementToAppear(terminationSaveButton);
        terminationSaveButton.click();
    }

    //Method to get employee details from the search results
    public Map<String, String> getEmployeeDetailsFromTable() {
        Map<String, String> employeeDetails = new HashMap<>();

        // Split the string by space
        String[] parts = tb_firstAndMiddleName.getText().split(" ");
        System.out.println(Arrays.toString(parts));
        String tb_firstName = parts[0];
        String tb_middleName = parts[1];
        employeeDetails.put("firstName", tb_firstName);
        employeeDetails.put("middleName", tb_middleName);
        employeeDetails.put("lastName", tb_lastName.getText());
        employeeDetails.put("employeeId", tb_empID.getText());
        employeeDetails.put("jobTitle", tb_jobTitle.getText());
        employeeDetails.put("employmentStatus", tb_employmentStatus.getText());
        return employeeDetails;
    }


//    public void addEmployee(String firstName, String middleName, String lastName, String userName,
//                            String pass, String confirmPass, String empID, String jobTitle, String employmentStatus,
//                            String terminationDate, String terminationReason) throws InterruptedException {
//
//        waitForElementToAppear(addButton);
//        addButton.click();
//        waitForElementToAppear(firstNameEle);
//        firstNameEle.sendKeys(firstName);
//        middleNameEle.sendKeys(middleName);
//        lastNameEle.sendKeys(lastName);
//        loginDetailsRadioButton.click();
//        waitForElementToAppear(userNameEle);
//        userNameEle.sendKeys(userName);
//        passwordEle.sendKeys(pass);
//        confirmPasswordEle.sendKeys(confirmPass);
//
//
//    }


    //Method to update employee details
    public void updateEmployeeDetails(String jobTitle, String employmentStatus){
        waitForElementToAppear(editIcon);
        editIcon.click();
        waitForElementToAppear(lp_job);
        lp_job.click();
        waitForElementToAppear(jobDetails);
        waitForElementToAppear(jobTitleDropdown);
        jobTitleDropdown.sendKeys(jobTitle);
        waitForElementToAppear(employmentStatusDropdown);
        employmentStatusDropdown.sendKeys(employmentStatus);
        job_SaveButton.click();

    }

    public void terminateEmployee(String terminationDate, String terminationReason)
    {
        waitForElementToAppear(editIcon);
        editIcon.click();
        waitForElementToAppear(lp_job);
        lp_job.click();
        waitForElementToAppear(jobDetails);
        waitForElementToAppear(terminateEmploymentButton);
        terminateEmploymentButton.click();
        waitForElementToAppear(terminateEmploymentPopup);
        waitForElementToAppear(terminationDateEle);
        terminationDateEle.sendKeys(terminationDate);
        waitForElementToAppear(terminationReasonEle);
        terminationReasonEle.sendKeys(terminationReason);
        terminationSaveButton.click();

    }

}
