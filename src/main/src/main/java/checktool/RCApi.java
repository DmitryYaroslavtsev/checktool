package checktool;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
            HttpPost httpPost = new HttpPost("http://" + hostname + "/restapi/oauth/token");

            httpPost.addHeader("Authorization", application.httpBasicAuth());
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", "password"));
            params.add(new BasicNameValuePair("username", userCredentials.phoneNumber));
            params.add(new BasicNameValuePair("password", userCredentials.password));

            httpPost.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
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
