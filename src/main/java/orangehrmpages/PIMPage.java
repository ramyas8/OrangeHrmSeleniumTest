package orangehrmpages;

import orangehrmabstractcomponents.AbstractComponents;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    WebElement employeeIdEle;

    @FindBy(xpath = "//span[text()='Employee Id already exists']")
    WebElement employeeIdExistsMessage;

    @FindBy(xpath = "//p[text()='Create Login Details']/following-sibling::div")
    WebElement loginDetailsRadioButton;

    @FindBy(xpath = "//label[text()='Username']/parent::div/following-sibling::div/input")
    WebElement userNameEle;

    @FindBy(xpath = "//label[text()='Username']/parent::div/following-sibling::span")
    WebElement usernameRequiredMessage;

    @FindBy(xpath = "//label[text()='Password']/parent::div/following-sibling::div/input")
    WebElement passwordEle;

    @FindBy(xpath = "//label[text()='Password']/parent::div/following-sibling::span")
    WebElement passwordRequiredMessage;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input")
    WebElement confirmPasswordEle;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::span")
    WebElement confirmPasswordRequiredMessage;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    WebElement search_EmployeeId;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchButton;

//    @FindBy(xpath = "(//i[@class='oxd-icon bi-pencil-fill'])[1]")
//    WebElement editIcon;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    WebElement personalDetails;

    @FindBy(xpath = "//h6[text()='Job Details']")
    WebElement jobDetails;

    @FindBy(xpath = "//a[text()='Job']")
    WebElement lp_jobTab;

    @FindBy(xpath = "//label[text()='Job Title']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]")
    WebElement jobTitleDropdown;


    @FindBy(xpath = "//label[text()='Employment Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]")
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

    @FindBy(xpath = "//label[text()='Termination Date']/parent::div/following-sibling::div//input")
    WebElement terminationDateEle;

    @FindBy(xpath = "//label[text()='Termination Reason']/parent::div/following-sibling::div/textarea")
    WebElement terminationReasonEle;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    WebElement terminationSaveButton;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    WebElement employeeTable;

    // --- Actions ---
    public void clickPimPage() {
        waitForElementToBeClickable(pim);
        pim.click();
    }

    public void clickAddButton() {
        waitForElementToAppear(addButton);
        addButton.click();
    }

    public void enterFirstName(String firstName) {
        waitForElementToAppear(firstNameEle);
        firstNameEle.sendKeys(firstName);

    }

    public void enterMiddleName(String middleName) {
        waitForElementToAppear(middleNameEle);
        middleNameEle.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        waitForElementToAppear(lastNameEle);
        lastNameEle.sendKeys(lastName);
    }

    public void clickLoginDetailsRadioButton() {
        waitForElementToAppear(loginDetailsRadioButton);
        loginDetailsRadioButton.click();
    }

    public void enterUsername(String username) {
        waitForElementToAppear(userNameEle);
        userNameEle.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElementToAppear(passwordEle);
        passwordEle.sendKeys(password);

    }

    public void enterConfirmPassword(String confirmPassword) {
        waitForElementToAppear(confirmPasswordEle);
        confirmPasswordEle.sendKeys(confirmPassword);
    }

    public String getEmployeeId() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the input field to be visible
        wait.until(ExpectedConditions.visibilityOf(employeeIdEle));

        // Wait until the value attribute is not empty
        wait.until(d -> !employeeIdEle.getAttribute("value").isEmpty());

        return employeeIdEle.getAttribute("value");
    }

    public void clickSaveButton() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        saveButton.click();
    }


    public void clickSearchButton() {

        waitForElementToAppear(searchButton);
        searchButton.click();
    }

    public void clickEditIcon() {
        // waitForElementToAppear(editIcon);
        // editIcon.click();
        waitForElementToAppear(personalDetails);
    }

    public void waitForPersonalDetails() //remove this method later after modifying the tests
    {
        waitForElementToAppear(personalDetails);
    }

    public void clickJobPage() {
        waitForElementToAppear(lp_jobTab);
        lp_jobTab.click();
        waitForElementToAppear(jobDetails);
    }

    public void enterJobTitle(String jobTitle) {
        waitForElementToAppear(jobTitleDropdown);
        jobTitleDropdown.sendKeys(jobTitle);
    }

    public void enterEmploymentStatus(String employmentStatus) {
        waitForElementToAppear(employmentStatusDropdown);
        employmentStatusDropdown.sendKeys(employmentStatus);
    }

    public void clickJobSaveButton() {
        waitForElementToAppear(job_SaveButton);
        job_SaveButton.click();
    }

    public void clickTerminateEmployeeButton() {
        waitForElementToAppear(terminateEmploymentButton);
        terminateEmploymentButton.click();
        waitForElementToAppear(terminateEmploymentPopup);
    }

    public void enterTerminationDate(String terminationDate) {
        waitForElementToAppear(terminationDateEle);
        terminationDateEle.sendKeys(terminationDate);
    }

    public void enterTerminationReason(String terminationReason) {
        waitForElementToAppear(terminationReasonEle);
        terminationReasonEle.sendKeys(terminationReason);
    }

    public void clickTerminationSaveButton() {
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


    public void addEmployee(String firstName, String middleName, String lastName, String userName,
                            String pass, String confirmPass, String empID, String jobTitle, String employmentStatus,
                            String terminationDate, String terminationReason) throws InterruptedException {

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
        //waitForPersonalDetails();

    }

    private void waitForJobSectionToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for Job container to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h6[text()='Job Details']/ancestor::div[contains(@class,'orangehrm-card-container')]")
        ));

        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")
        ));
    }


    public String getCreatedEmployeeId() {
        return employeeIdEle.getAttribute("value");
    }

    public void searchEmployeeWithId(String id) throws InterruptedException {

        clickPimPage();
        waitForElementToAppear(search_EmployeeId);
        System.out.println("the id is:" + id);
        search_EmployeeId.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
        search_EmployeeId.sendKeys(id);
        clickSearchButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));


    }


    //Method to update employee details
    public void updateEmployeeDetails(String jobTitle, String employmentStatus) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(d -> {
            try {
                // Find the pencil icon again inside the loop to get a 'fresh' reference
                WebElement editIcon = d.findElement(By.xpath("//div[@class='oxd-table-card']//i[contains(@class,'bi-pencil-fill')]"));
                editIcon.click();
                return true;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                return false; // Loop will try again until timeout
            }
        });


        waitForPersonalDetails();
        wait.until(ExpectedConditions.elementToBeClickable(lp_jobTab)).click();
        waitForJobSectionToLoad();
        selectFromOrangeDropdown(jobTitleDropdown, jobTitle);
        selectFromOrangeDropdown(employmentStatusDropdown, employmentStatus);
        job_SaveButton.click();

    }

    public void terminateEmployee(String terminationDate, String terminationReason) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        wait.until(d -> {
            try {
                // Find the pencil icon again inside the loop to get a 'fresh' reference
                WebElement editIcon = d.findElement(By.xpath("//div[@class='oxd-table-card']//i[contains(@class,'bi-pencil-fill')]"));
                editIcon.click();
                return true;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                return false; // Loop will try again until timeout
            }
        });
        waitForPersonalDetails();

        wait.until(ExpectedConditions.elementToBeClickable(lp_jobTab)).click();
        waitForJobSectionToLoad();
        waitForElementToAppear(jobDetails);
        waitForElementToAppear(terminateEmploymentButton);
        terminateEmploymentButton.click();
        waitForElementToAppear(terminateEmploymentPopup);

        waitForElementToAppear(terminationDateEle);
        terminationDateEle.sendKeys(terminationDate);

        WebElement modalReasonDropdown = driver.findElement(By.xpath("//div[@role='document']//div[contains(@class,'oxd-select-text')]"));
        selectFromOrangeDropdown(modalReasonDropdown, terminationReason);
        terminationSaveButton.click();

    }

    private void selectFromOrangeDropdown(WebElement dropdown, String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Wait until dropdown is present in DOM
        wait.until(ExpectedConditions.visibilityOf(dropdown));

        // 2. Scroll into view (React sometimes renders off-screen nodes)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", dropdown
        );

        // 1. Click the dropdown to open it
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));

        try {
            dropdown.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
        }

        // 2. Wait for the listbox options to be present in the DOM
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//div[@role='option']")));

        // 3. Capture all options
        List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//div[@role='option']"));

        boolean isFound = false;
        for (WebElement option : options) {
            String actualText = option.getText().trim();

            if (actualText.equalsIgnoreCase(valueToSelect)) {
                // Using Javascript click as a backup if a normal click is intercepted
                try {
                    option.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                }
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            throw new RuntimeException("Option '" + valueToSelect + "' not found in dropdown!");
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
    }

}
