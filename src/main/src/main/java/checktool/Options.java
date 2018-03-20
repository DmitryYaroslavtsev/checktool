package checktool;

public class Options {
    private String timeout = null;
    private String messageType = null;
    private String targetExtensionNumber = null;

    private String direction = null;
    private String msgCount = null;

    private String npsHostname = null;
    private String npsPort = null;

    private String n11sEventFilter = null;
    private String channel = null;
    private String encriptionKey = null;

    Options(String timeout, String messageType, String targetExtensionNumber, String direction,
            String msgCount, String npsHostname, String npsPort, String n11sEventFilter,
            String channel, String encriptionKey) {
        this.timeout = timeout;
        this.messageType = messageType;
        this.targetExtensionNumber = targetExtensionNumber;
        this.direction = direction;
        this.msgCount = msgCount;
        this.npsHostname = npsHostname;
        this.npsPort = npsPort;
        this.n11sEventFilter = n11sEventFilter;
        this.channel = channel;
        this.encriptionKey = encriptionKey;
    }
}
