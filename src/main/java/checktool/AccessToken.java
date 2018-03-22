package checktool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken extends OAuthToken {
    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("scope")
    String permissions;
}
