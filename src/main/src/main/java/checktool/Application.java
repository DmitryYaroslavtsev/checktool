package checktool;

import org.apache.commons.codec.binary.Base64;

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

    public String httpBasicAuth() {
        return "Basic " + Base64.encodeBase64String((key + ":" + secret).getBytes());
    }
}
