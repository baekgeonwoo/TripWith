package com.janggoback.tripwith.auth.oauth2.memberinfo;

import java.util.Map;

public abstract class OAuth2MemberInfo {

    protected Map<String, Object> attributes;

    public OAuth2MemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId(); //소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"

    public abstract String getName();

    public abstract String getImageUrl();
}