package HW3.tests;

import HW3.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Victoria on 03.12.2016.
 */
public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    /**
     * Default preconditions:
     * 1. Open FireFox browser
     * 2. Set implicit waits to 10 seconds
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    /**
     * Preconditions:
     * Open application Login Page URL
     */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    /**
     * Steps to reproduce:
     * 1. Set username to "admin"
     * 2. Set password to "123"
     * 3. Click on 'Login' button
     * 4. Verify that title of the page equals to "Players"
     */
    @Test
    public void positiveLoginTest() {
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getTitle(), "Players", "Wrong Title after login");

    }

    /**
     * Steps to reproduce:
     * 1. Set username to "badAdmin"
     * 2. Set password to "123"
     * 3. Click on 'Login' button
     * 4. Verify that message error equals to "Invalid username or password"
     */
    @Test
    public void negativeUsernameTest() {
        loginPage.setUsername("badAdmin");
        loginPage.setPassword("123");
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong message error after enter incorrect username");
    }

    /**
     * Steps to reproduce:
     * 1. Set username to "admin"
     * 2. Set password to "1234d"
     * 3. Click on 'Login' button
     * 4. Verify that message error equals to "Invalid username or password"
     */
    @Test
    public void negativePasswordTest() {
        loginPage.setUsername("admin");
        loginPage.setPassword("1234d");
        loginPage.clickOnLogin();

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong message error after enter incorrect password");

    }

    /**
     * Steps to reproduce:
     * 1. Set username to " "(empty)
     * 2. Set password to " "(empty)
     * 3. Click on 'Login' button
     * 4. Verify that first message error equals to "Value is required and can't be empty"
     * 5. Verify that second message error equals to "Value is required and can't be empty"
     */
    @Test
    public void emptyUsernameAndPasswordTest() {
        loginPage.setUsername(" ");
        loginPage.setPassword(" ");
        loginPage.clickOnLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getErrorMessage(1), "Value is required and can't be empty", "Wrong message error after login with empty username field");
        softAssert.assertEquals(loginPage.getErrorMessage(2), "Value is required and can't be empty", "Wrong message error after login with empty password field");
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
