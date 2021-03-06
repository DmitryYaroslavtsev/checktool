package checktool;

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

        Application application = new Application(args[2],args[3]);
        UserCredentials userCredentials = new UserCredentials(args[0], args[1]);

        RCApi rcApi = new RCApi(application, userCredentials, apiAddress);

        String testNumber = "12069604586";

        try {
            //System.out.println("rcApi.get: " + rcApi.get("/restapi/v1.0/account/~/extension").toString());
            //System.out.println("sendSms: " + Methods.sendSms(rcApi, "test", userCredentials.phoneNumber, userCredentials.phoneNumber));
            //System.out.println("sendPager: " + Methods.sendPager(rcApi, "test", userCredentials.extension, userCredentials.extension));
            //System.out.println("makeRingOut: " + Methods.makeRingOut(rcApi,userCredentials.phoneNumber,testNumber));
            //System.out.println("changeExtension: " + Methods.changeExtension(rcApi));
            //rcApi.refreshToken();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
