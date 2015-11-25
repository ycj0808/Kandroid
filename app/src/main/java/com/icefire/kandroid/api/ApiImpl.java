package com.icefire.kandroid.api;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.icefire.kandroid.network.Listener;
import com.icefire.kandroid.network.NetworkHelper;
import com.icefire.kandroid.utils.CryptUtils;
import com.icefire.kandroid.utils.ExceptionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public class ApiImpl implements Api{

    private final static String TIME_OUT_EVENT = "CONNECT_TIME_OUT";
    private final static String TIME_OUT_EVENT_MSG = "连接服务器失败";
    private NetworkHelper networkHelper;

    public ApiImpl(Context context){
        networkHelper=NetworkHelper.getInstance(context);
    }

    @Override
    public void loginByApp(String loginName, String password,int loginOS, final ActionCallbackListener<Void> listener) {
        Map<String,String> paramMap=new ConcurrentHashMap<>();
        paramMap.put("mobile",loginName);
        paramMap.put("password", CryptUtils.md5(password));
        networkHelper.sendPostRequest(Api.BASE_URL_ALL+Api.LOGIN_URL,paramMap, new Listener<Void>() {
            @Override
            public void onResponse(String response, int requestCode) {
                if(listener!=null){
                    listener.onSuccess(null,requestCode);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(listener!=null){
                    listener.onFailure(error.getMessage(), ExceptionUtils.getErrorInfo(error));
                }
            }
        });
    }
}
