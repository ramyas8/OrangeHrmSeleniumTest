package orangehrmabstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForUrlToContain(String urlPart) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public void selectMenu(String menuName) {
        // Dynamic XPath based on OrangeHRM sidebar structure
        WebElement menuOption = driver.findElement(By.xpath("//span[text()='" + menuName + "']"));
        waitForElementToAppear(menuOption);
        menuOption.click();
    }

    public void waitForSpinnerToDisappear() {

        By spinner = By.className("oxd-form-loader");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // Wait for spinner to vanish if it's there
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        } catch (Exception e) {
            // If the spinner never appeared or already vanished, just continue
        }
    }

}
