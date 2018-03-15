package checktool;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

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

    static void openHttpConnection(String hostname) throws IOException {
        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpGet httpGet = new HttpGet("http://" + hostname);
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new AssertionError(response.getStatusLine());
                }
            }
        }
    }

    void authenticate(Application application, UserCredentials userCredentials) throws IOException {
        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpGet httpGet = new HttpGet("http://" + hostname + "/restapi/oauth/token");
            httpGet.addHeader("Authorization", application.httpBasicAuth());
            httpGet.addHeader("Accept", "application/json");
            httpGet.addHeader("Content-type", "application/x-www-form-urlencoded");

            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new AssertionError(response.getStatusLine());
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line  = "";
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }

                System.out.println(result.toString());
            }



        }

    }


}
