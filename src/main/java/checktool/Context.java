package checktool;

import com.fasterxml.jackson.databind.JsonNode;

public class Context {
    private RCApi api = null;
    private JsonNode extensionInfo = null;
    private Options options = null;

    Context(RCApi api, JsonNode extensionInfo, Options options) {
        this.api = api;
        this.extensionInfo = extensionInfo;
        this.options = options;
    }
}
