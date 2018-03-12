package checktool;

public class Checktool {
    public static void main(String[] args) {
        System.out.println("Init");

        String apiAddress = "api.ops.ringcentral.com";

        //User Credentials
        if (args.length == 0) {
            System.out.println("No arguments");
            System.exit(0);
        }
        System.out.println("After verification of args");

        Application application = new Application(args[0],args[1]);

    }
}
