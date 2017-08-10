package com.rubenpla.develop.petagramcoursera.api.constants;

public final class ConstantesRestApi {

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5823926203.cf5e43d.3a0cced541a24d568c3cdf164fcb2e2f";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";


    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER
            + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN

    public static final String KEY_GET_SELF_USER_INFO = "users/self/info"; //TODO not real endpoint
     public static final String URL_GET_SELF_USER_INFO = KEY_GET_SELF_USER_INFO
             + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
