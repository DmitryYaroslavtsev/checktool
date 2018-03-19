package checktool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class OAuthToken {
    @JsonProperty("access_token")
    String token;

    @JsonProperty("expires_in")
    long expiresAt;

    long issuedAt;
}
