import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Victoria on 24.11.2016.
 */
public class PokerPlayer {
    private final String userName;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String city;
    private final String address;
    private final Long phone;

    public PokerPlayer(){
        RandomStringUtils randomStringUtils=new RandomStringUtils();
        this.userName=randomStringUtils.randomAlphanumeric(7);
        this.email=userName+"@gmail.com";
        this.password=randomStringUtils.randomAlphanumeric(7);
        this.firstName=randomStringUtils.randomAlphanumeric(7);
        this.lastName=randomStringUtils.randomAlphanumeric(7);
        this.city=randomStringUtils.randomAlphanumeric(7);
        this.address=randomStringUtils.randomAlphanumeric(7);
        this.phone=Long.parseLong(randomStringUtils.randomNumeric(12));
    }

    public PokerPlayer(String userName, String password, String firstName, String lastName, String city, String address, long phone) {
        this.userName = userName;
        this.email=userName+"@gmail.com";
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Long getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PokerPlayer{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokerPlayer that = (PokerPlayer) o;

        if (phone != that.phone) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (int) (phone ^ (phone >>> 32));
        return result;
    }

}
