package checktool;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.util.Pair;

import java.util.HashMap;

public class Checktool {
    public static void main(String[] args) {
        System.out.println("Init");

        String apiAddress = "api.ops.ringcentral.com";
        //apiAddress = "api.ringcentral.com";

        //User Credentials
        if (args.length == 0) {
            System.out.println("No arguments");
            System.exit(0);
        }
        System.out.println("After verification of args");

        Application application = new Application(args[0],args[1]);
        UserCredentials userCredentials = new UserCredentials(args[2], args[3]);

        RCApi rcApi = new RCApi(application, userCredentials, apiAddress);

        try {
            JsonNode node  = rcApi.get("/restapi/v1.0/account/~/extension");
            System.out.println(node.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
