package com.mztalk.loginservice.user.repository.entity.util;

public enum Provider {

    LOCAL("LOCAL"), GOOGLE("GOOGLE"), KAKAO("KAKAO"), NAVER("NAVER");

    private final String provider;

    Provider(String provider){
        this.provider = provider;
    }

    public String getProvider(){
        return provider;
    }

}
