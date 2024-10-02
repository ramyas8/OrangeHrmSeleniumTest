package orangehrmabstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponents {

    WebDriver driver;
    public AbstractComponents(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
    List<WebElement> menu;

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickPIM() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(menu));

        if (menu.size() > 1) {  // Ensure there is at least 2 elements
            menu.get(1).click();  // Click the second element
            Thread.sleep(3000);  // Use this for debugging, but it's better to replace with a proper wait
        } else {
            System.out.println("Not enough elements in the menu list");
        }


    }
}
