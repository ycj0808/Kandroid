package com.icefire.kandroid.api;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public interface Api {
    public final static String PREFIX_URL="http://";
    public final static String BASE_URL="10.4.123.208/xxsh";
    public final static String BASE_URL_ALL=PREFIX_URL+BASE_URL;
    public final static String LOGIN_URL = "/index.php?m=mobile&c=index&a=rest_login";

    public void loginByApp(String loginName, String password, int loginOS,final ActionCallbackListener<Void> listener);
}
