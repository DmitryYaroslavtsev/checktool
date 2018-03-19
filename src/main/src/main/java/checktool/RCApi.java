package checktool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RCApi {

    private String hostname;
    private Application application;
    private UserCredentials userCredentials;

    private Date date = new Date();
    private ObjectMapper objectMapper = new ObjectMapper();
    AccessToken accessToken = new AccessToken();

    private JsonNode jsonNode;


    RCApi(Application application, UserCredentials userCredentials, String hostname){
        this.application = application;
        this.userCredentials = userCredentials;
        this.hostname = hostname;

        try {
            authenticate(application, userCredentials);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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

                accessToken = objectMapper.readValue(
                        response.getEntity().getContent(),AccessToken.class);
                accessToken.issuedAt = date.getTime();

                accessToken.expiresAt = accessToken.expiresAt*1000 + accessToken.issuedAt;

            }
        }
    }

//    there is obsolete grant_type
    void refreshToken() throws IOException {
        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpPost httpPost = new HttpPost("http://" + hostname + "/restapi/oauth/token");

            httpPost.addHeader("Authorization", application.httpBasicAuth());
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", "refresh_token"));
            params.add(new BasicNameValuePair("refresh_token", accessToken.refreshToken));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new AssertionError(response.getStatusLine());
                }

                accessToken = objectMapper.readValue(
                        response.getEntity().getContent(),AccessToken.class);
                accessToken.issuedAt = date.getTime();

                accessToken.expiresAt = accessToken.expiresAt*1000 + accessToken.issuedAt;

            }
        }
    }

    JsonNode get(String path) throws IOException {
        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpGet httpGet = new HttpGet("http://" + hostname + path + "?");

            httpGet.addHeader("Authorization", "Bearer " + accessToken.token);
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new AssertionError(response.getStatusLine());
                }

                jsonNode = objectMapper.readTree(response.getEntity().getContent());
            }
        }
        return jsonNode;
    }
}
