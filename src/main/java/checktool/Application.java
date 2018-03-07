package checktool;

public class Application {
    String key = null;
    String secret = null;

    Application(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }
}
