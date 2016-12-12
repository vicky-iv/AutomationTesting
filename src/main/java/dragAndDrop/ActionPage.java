package dragAndDrop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

/**
 * Created by Victoria on 11.12.2016.
 */
public class ActionPage {
    private WebDriver driver;
    private RemoteWebDriver remoteWebDriver;
    private final static String URL = new File("src/main/java/dragAndDrop/source/index.html").getAbsolutePath();
    private Actions actions;

    @FindBys(@FindBy(xpath = ".//ul[@id='sortable']/li"))
    private List<WebElement> draggableElements;

    @FindBy(id = "drop")
    private WebElement trash;

    public ActionPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);

    }

    public void open() {
        driver.get(URL);
    }

    public void dragAndDropToTrash(int numberOfElement) {
        for (int i = 0; i < draggableElements.size(); i++) {
            if (draggableElements.get(i).getText().equals(new Integer(numberOfElement).toString())) {
                actions.dragAndDrop(draggableElements.get(i),trash).perform();
                break;
            }
        }
    }


    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public boolean isPresentDraggableElement(int numberOfElement) {
        boolean result = false;
        for (int i = 0; i < draggableElements.size(); i++) {
            if (draggableElements.get(i).getText().equals(new Integer(numberOfElement).toString())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void sortAZ() {
        for (int i = 0; i < draggableElements.size(); i++) {
            int min = Integer.parseInt(draggableElements.get(i).getText());
            int index = i;

            for (int j = i + 1; j < draggableElements.size(); j++) {
                int tmp = Integer.parseInt(draggableElements.get(j).getText());
                if (tmp < min) {
                    min = tmp;
                    index = j;
                }
            }

            actions.clickAndHold(draggableElements.get(index)).moveToElement(draggableElements.get(i)).release().build().perform();
        }
    }

    public void sortZA() {
        for (int i = 0; i < draggableElements.size(); i++) {
            int max = Integer.parseInt(draggableElements.get(i).getText());
            int index = i;

            for (int j = i + 1; j < draggableElements.size(); j++) {
                int tmp = Integer.parseInt(draggableElements.get(j).getText());
                if (tmp > max) {
                    max = tmp;
                    index = j;
                }
            }

            actions.clickAndHold(draggableElements.get(index)).moveToElement(draggableElements.get(i)).release().build().perform();
        }
    }

    public String getOrderOfElements() {
        String order=Integer.parseInt(draggableElements.get(0).getText())+" ";

        for (int i = 1; i < draggableElements.size(); i++) {
            order=order.concat(Integer.parseInt(draggableElements.get(i).getText()) + " ");
        }

        return order;
    }
}
