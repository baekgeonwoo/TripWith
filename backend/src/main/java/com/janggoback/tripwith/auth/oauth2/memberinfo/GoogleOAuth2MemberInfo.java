package com.janggoback.tripwith.auth.oauth2.memberinfo;

import java.util.Map;

public class GoogleOAuth2MemberInfo extends OAuth2MemberInfo {

    public GoogleOAuth2MemberInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
