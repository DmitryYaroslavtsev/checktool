package checktool;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    void openHttpConnection(String hostname) throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(hostname);
            HttpResponse response = client.execute(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String str = "";
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        }
    }
}
