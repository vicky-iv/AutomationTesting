import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Victoria on 24.11.2016.
 */
public class TestsForPlayer {

    private WebElement userName;
    private WebElement password;
    private WebElement confirmPassword;
    private WebElement email;
    private WebElement firstName;
    private WebElement lastName;
    private WebElement city;
    private WebElement address;
    private WebElement phone;


    public TestsForPlayer(WebDriver driver, boolean isNewPlayer) {
        if (isNewPlayer==true){
            this.password=driver.findElement(By.xpath("//input[contains(@id,'us_password')]"));
            this.confirmPassword=driver.findElement(By.xpath("//input[contains(@id,'confirm_password')]"));
        }
        this.userName = driver.findElement(By.xpath("//input[contains(@id,'login')]"));
        this.email = driver.findElement(By.xpath("//input[contains(@id,'email')]"));
        this.firstName = driver.findElement(By.xpath("//input[contains(@id,'us_fname')]"));
        this.lastName = driver.findElement(By.xpath("//input[contains(@id,'us_lname')]"));
        this.city = driver.findElement(By.xpath("//input[contains(@id,'us_city')]"));
        this.address = driver.findElement(By.xpath("//textarea[contains(@id,'us_address')]"));
        this.phone = driver.findElement(By.xpath("//input[contains(@id,'us_phone')]"));
    }


    public void createPlayer(PokerPlayer pokerPlayer){
        userName.sendKeys(pokerPlayer.getUserName());
        email.sendKeys(pokerPlayer.getEmail());
        password.sendKeys(pokerPlayer.getPassword());
        confirmPassword.sendKeys(pokerPlayer.getPassword());
        firstName.sendKeys(pokerPlayer.getFirstName());
        lastName.sendKeys(pokerPlayer.getLastName());
        city.sendKeys(pokerPlayer.getCity());
        address.sendKeys(pokerPlayer.getAddress());
        phone.sendKeys(pokerPlayer.getPhone().toString());
    }


    public void editPlayer(PokerPlayer pokerPlayer){
        firstName.clear();
        lastName.clear();
        city.clear();
        address.clear();
        phone.clear();

        firstName.sendKeys(pokerPlayer.getFirstName());
        lastName.sendKeys(pokerPlayer.getLastName());
        city.sendKeys(pokerPlayer.getCity());
        address.sendKeys(pokerPlayer.getAddress());
        phone.sendKeys(pokerPlayer.getPhone().toString());
    }

    public void checkContentPlayer(PokerPlayer expectedPlayer){
        System.out.println("Email: ");
        assertString(email.getAttribute("value"),expectedPlayer.getEmail());

        System.out.println("First Name: ");
        assertString(firstName.getAttribute("value"),expectedPlayer.getFirstName());

        System.out.println("Last Name: ");
        assertString(lastName.getAttribute("value"),expectedPlayer.getLastName());

        System.out.println("City: ");
        assertString(city.getAttribute("value"),expectedPlayer.getCity());

        System.out.println("Address: ");
        assertString(address.getAttribute("value"),expectedPlayer.getAddress());

        System.out.println("Phone: ");
        assertString(phone.getAttribute("value"),expectedPlayer.getPhone().toString());
    }

    public static void assertString(String actualResult, String expectedResult) {
        if(expectedResult.equals(actualResult)) {
            System.out.println("Passed.");
        } else {
            System.err.println("Failed. Expected: " + expectedResult
                    + ". Actual: " + actualResult);
        }
    }

    public WebElement getConfirmPassword() {
        return confirmPassword;
    }
}