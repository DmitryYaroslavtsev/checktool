package checktool;

import org.apache.commons.codec.binary.Base64;

public class Application {
    private String key;
    private String secret;

    Application(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String httpBasicAuth() {
        return "Basic " + Base64.encodeBase64String((key + ":" + secret).getBytes());
    }
}
