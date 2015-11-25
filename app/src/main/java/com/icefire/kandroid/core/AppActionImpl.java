package com.icefire.kandroid.core;

import android.content.Context;
import android.text.TextUtils;

import com.icefire.kandroid.api.ActionCallbackListener;
import com.icefire.kandroid.api.Api;
import com.icefire.kandroid.api.ApiImpl;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public class AppActionImpl implements AppAction{

    private final static int LOGINOS=1;
    private Api api;
    private Context mContext;
    public AppActionImpl(Context context){
        this.api=new ApiImpl(context);
        this.mContext=context;
    }

    @Override
    public void login(String loginName, String password, ActionCallbackListener<Void> listener) {
        // 参数为空检查
        if (TextUtils.isEmpty(loginName)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "登录名为空");
            }
            return;
        }
        //密码为空
        if (TextUtils.isEmpty(password)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "密码为空");
            }
            return;
        }
        api.loginByApp(loginName,password,LOGINOS,listener);
    }
}
