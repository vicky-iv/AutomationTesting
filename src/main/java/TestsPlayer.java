import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Victoria on 24.11.2016.
 */
public class TestsPlayer {

    private WebElement userName;
    private WebElement password;
    private WebElement confirmPassword;
    private WebElement email;
    private WebElement firstName;
    private WebElement lastName;
    private WebElement city;
    private WebElement address;
    private WebElement phone;


    public TestsPlayer(WebDriver driver) {
        this.userName = driver.findElement(By.xpath("//input[contains(@id,'login')]"));
        this.password=driver.findElement(By.xpath("//input[contains(@id,'us_password')]"));
        this.confirmPassword=driver.findElement(By.xpath("//input[contains(@id,'confirm_password')]"));
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

    public void editPlayer(PokerPlayer pokerPlayer){//попробовать созданием
        userName.sendKeys(pokerPlayer.getUserName());
        firstName.sendKeys(pokerPlayer.getFirstName());
        lastName.sendKeys(pokerPlayer.getLastName());
        city.sendKeys(pokerPlayer.getCity());
        address.sendKeys(pokerPlayer.getAddress());
        phone.sendKeys(pokerPlayer.getPhone().toString());
    }

    public void checkContentPokerPlayer(PokerPlayer expectedPlayer){
        System.out.println("Email: ");
        assertString(email.getText(),expectedPlayer.getEmail());

        System.out.println("First Name: ");
        assertString(firstName.getText(),expectedPlayer.getFirstName());

        System.out.println("Last Name: ");
        assertString(lastName.getText(),expectedPlayer.getLastName());

        System.out.println("City: ");
        assertString(city.getText(),expectedPlayer.getCity());

        System.out.println("Address: ");
        assertString(address.getText(),expectedPlayer.getAddress());

        System.out.println("Phone: ");
        assertString(phone.getText(),expectedPlayer.getPhone().toString());
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