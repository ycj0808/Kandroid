package com.icefire.kandroid;

import android.app.Application;

import com.icefire.kandroid.core.AppAction;
import com.icefire.kandroid.core.AppActionImpl;
import com.orhanobut.logger.Logger;

/**
 *
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public class ApplicationController extends Application{
    public static final String TAG = "VolleyPatterns";

    private AppAction appAction;
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG);
        appAction=new AppActionImpl(this);
    }

    public AppAction getAppAction(){
        return appAction;
    }
}
