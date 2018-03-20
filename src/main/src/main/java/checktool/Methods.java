package checktool;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;

public class Methods {


    static String sendSms(RCApi api, String text, String from, String to) throws IOException {

        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpPost httpPost = new HttpPost("http://" + api.hostname + "/restapi/v1.0/account/~/extension/~/sms");

            httpPost.addHeader("Authorization", "Bearer " + api.accessToken.token);
            httpPost.addHeader("Content-Type", "application/json");

            String entity = "{\"to\": [{\"phoneNumber\": \"" + to + "\"}], \"from\": {\"phoneNumber\": \"" + from + "\"}, \"text\": \"" +text + "\"}";

            httpPost.setEntity(new StringEntity(entity));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println(response.getStatusLine().getStatusCode());
                    return "Failed!";
                }
                else {
                    return "OK";
                }
            }
        }
    }

    static String sendPager(RCApi api, String text, String from, String to) throws IOException {

        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpPost httpPost = new HttpPost("http://" + api.hostname + "/restapi/v1.0/account/~/extension/~/company-pager");

            httpPost.addHeader("Authorization", "Bearer " + api.accessToken.token);
            httpPost.addHeader("Content-Type", "application/json");

            String entity = "{\"to\": [{\"extensionNumber\": \"" + to + "\"}], \"from\": {\"extensionNumber\": \"" + from + "\"}, \"text\": \"" +text + "\"}";

            httpPost.setEntity(new StringEntity(entity));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println(response.getStatusLine().getStatusCode());
                    return "Failed!";
                }
                else {
                    return "OK";
                }
            }
        }
    }
}
