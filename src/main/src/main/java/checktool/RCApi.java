package checktool;

public class RCApi {

    String hostname = null;
    String port = null;
    Application application = null;
    UserCredentials userCredentials = null;

    RCApi(Application application, UserCredentials user, String hostname){
        this.application = application;
        this.userCredentials = user;
        this.hostname = hostname;
    }
}
