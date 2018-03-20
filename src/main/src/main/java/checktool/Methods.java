package checktool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Methods {


    static String sendSms(RCApi api, String text, String from, String to) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createMinimal()) {
            HttpPost httpPost = new HttpPost("http://" + api.hostname + "/restapi/v1.0/account/~/extension/~/sms");

            httpPost.addHeader("Authorization", "Bearer " + api.accessToken.token);
            //httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/json");
            //httpPost.addHeader("ContentEncoding", "charset=UTF-8");




            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("from", from));
            params.add(new BasicNameValuePair("to", to));
            params.add(new BasicNameValuePair("text", text));

            String entity = objectMapper.writeValueAsString(params);
            System.out.println(entity);


            httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(params)));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    return "Failed!";
                }
                else {
                    return "OK";
                }
            }
        }
    }
}
