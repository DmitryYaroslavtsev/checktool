package checktool;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.Random;

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
                    return response.getStatusLine().getReasonPhrase();
                }
                else {
                    return response.getStatusLine().getReasonPhrase();
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
                    return response.getStatusLine().getReasonPhrase();
                }
                else {
                    return response.getStatusLine().getReasonPhrase();
                }
            }
        }
    }

    static String makeRingOut(RCApi api, String from, String to) throws IOException {
        try (CloseableHttpClient client = HttpClients.createMinimal()){
            HttpPost httpPost = new HttpPost("http://" + api.hostname + "/restapi/v1.0/account/~/extension/~/ringout");

            httpPost.addHeader("Authorization", "Bearer " + api.accessToken.token);
            httpPost.addHeader("Content-Type", "application/json");

            String entity = "{\"from\": {\"phoneNumber\": \"" + from+ "\"}," +
                    "\"to\": {\"phoneNumber\": \"" + to + "\"}," +
                    "\"callerId\": {\"phoneNumber\": \"" + from + "\"}," +
                    "\"playPrompt\": \"true\"}";

            httpPost.setEntity(new StringEntity(entity));
            try (CloseableHttpResponse response = client.execute(httpPost)){
                if (response.getStatusLine().getStatusCode() != 200) {
                    return response.getStatusLine().getReasonPhrase();
                }
                else {
                    return response.getStatusLine().getReasonPhrase();
                }
            }
        }
    }

    static String randomWord(int length) {
        StringBuilder sb = new StringBuilder();
        String AB = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    static String setFirstName(RCApi api, String firstname) throws IOException{
        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpPut httpPut = new HttpPut("http://" + api.hostname + "/restapi/v1.0/account/~/extension/~/");

            httpPut.addHeader("Authorization", "Bearer " + api.accessToken.token);
            httpPut.addHeader("Content-Type", "application/json");

            String entity = "{\"contact\": {\"firstName\": \""
                    + firstname + "\"}}";
            httpPut.setEntity(new StringEntity(entity));

            try (CloseableHttpResponse response = client.execute(httpPut)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    return response.getStatusLine().getReasonPhrase();
                }
                else {
                    return response.getStatusLine().getReasonPhrase();
                }
            }
        }
    }

    static String changeExtension(RCApi api) throws IOException {
        return setFirstName(api, randomWord(10));
    }
}
