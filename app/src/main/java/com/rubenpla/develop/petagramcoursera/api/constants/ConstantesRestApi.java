package com.rubenpla.develop.petagramcoursera.api.constants;

public final class ConstantesRestApi {

    //INSTAGRAM
    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String INSTA_ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5823926203.cf5e43d.3a0cced541a24d568c3cdf164fcb2e2f";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";


    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER
            + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_SELF_USER = "users/self/";
    public static final String URL_GET_USER_SELF = KEY_GET_SELF_USER + KEY_ACCESS_TOKEN +
            ACCESS_TOKEN;


    //https://api.instagram.com/v1/users/self/followed-by?access_token=ACCESS-TOKEN
    public static final String KEY_GET_FOLLOWED_BY = "users/self/followed-by/";
    public static final String URL_GET_FOLLOWED_BY = KEY_GET_FOLLOWED_BY + KEY_ACCESS_TOKEN +
            ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=access_token
    public static final String KEY_GET_RECENT_MEDIA_BY_USER_ID = "users/{user}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_BY_USER_ID = KEY_GET_RECENT_MEDIA_BY_USER_ID
            + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

/*    //https://api.instagram.com/v1/media/{media-id}/likes?access_token=ACCESS-TOKEN
    public static final String KEY_LIKE_PHOTO = "users/{user}/media/recent/";
    public static final String URL_LIKE_PHOTO = KEY_LIKE_PHOTO
            + KEY_ACCESS_TOKEN + ACCESS_TOKEN;*/

    //https://api.instagram.com/v1/media/{media-id}/likes?access_token=ACCESS-TOKEN
    public static final String KEY_LIKE_PHOTO = "media/{mediaId}/likes/";
    public static final String URL_LIKE_PHOTO = KEY_LIKE_PHOTO
            + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //FIREBASE/API STUFF
    public static final String API_ROOT_URL = "https://guarded-coast-64549.herokuapp.com/";
    public static final String KEY_API_POST_REGISTER_USER = "registrar-usuario/";
    public static final String KEY_API_POST_REGISTER_LIKE_MEDIA = "registrar-usuario/";
}
