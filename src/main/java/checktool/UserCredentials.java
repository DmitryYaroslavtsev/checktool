package checktool;

public class UserCredentials {
    String phoneNumber = null;
    String extension = null;
    String password = null;

    UserCredentials(String phoneNumber, String extension, String password) {
        this.phoneNumber = phoneNumber;
        this.extension = extension;
        this.password = password;
    }

    UserCredentials(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.extension = "101";
        this.password = password;
    }
}
