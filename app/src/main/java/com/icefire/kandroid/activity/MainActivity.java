package com.icefire.kandroid.activity;

import android.os.Bundle;
import com.icefire.kandroid.R;
import com.icefire.kandroid.api.ActionCallbackListener;
import com.icefire.kandroid.base.BaseActivity;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity {

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        appAction.login("15898123050", "123456", new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data, int reponseCode) {
                Logger.i("登录成功");
            }
            @Override
            public void onFailure(String errorEvent, String message) {
                Logger.i("errorEvent:%s,message:%s",errorEvent,message);
            }
        });
        appAction.login("15898123050", "543210", new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data, int reponseCode) {
                Logger.i("登录shibai");
            }
            @Override
            public void onFailure(String errorEvent, String message) {
                Logger.i("errorEvent:%s,message:%s",errorEvent,message);
            }
        });
    }

    @Override
    protected void setListener() {

    }
}
