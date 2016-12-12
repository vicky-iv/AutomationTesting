package dragAndDrop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Victoria on 11.12.2016.
 */
public class ActionTests {
    private WebDriver driver;
    private ActionPage actionPage;
    private SoftAssert softAssert;

    /**
     * Default preconditions:
     * 1. Open FireFox browser
     * 2. Set implicitly wait to 10 seconds
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        actionPage = new ActionPage(driver);
    }

    /**
     * Preconditions:
     * 1. Open application URL
     */
    @BeforeMethod
    public void beforeMethod() {
        actionPage.open();
        softAssert = new SoftAssert();
    }


    @Test
    public void deleteTest() {
        int indexOfElement = 1 + (int) (Math.random() * ((7 - 1) + 1));

        actionPage.dragAndDropToTrash(indexOfElement);
        softAssert.assertEquals(actionPage.getAlertText(), "Are you sure that you want to delete?", "Wrong alert");
        actionPage.dismissAlert();
        softAssert.assertTrue(actionPage.isPresentDraggableElement(indexOfElement), "The element has been deleted!");

        actionPage.dragAndDropToTrash(indexOfElement);
        actionPage.acceptAlert();
        softAssert.assertFalse(actionPage.isPresentDraggableElement(indexOfElement), "The element has not been deleted!");

        softAssert.assertAll();
    }

    @Test
    public void sortTest() {
        actionPage.sortAZ();
        String result = actionPage.getOrderOfElements();
        softAssert.assertEquals(result, "1 2 3 4 5 6 7 ", "After sortAZ: " + result);

        actionPage.sortZA();
        result = actionPage.getOrderOfElements();
        softAssert.assertEquals(result, "7 6 5 4 3 2 1 ", "After sortZA: " + result);

        softAssert.assertAll();
    }

    /**
     * Close the browser
     */
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
