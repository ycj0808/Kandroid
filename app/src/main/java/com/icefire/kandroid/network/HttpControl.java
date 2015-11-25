package com.icefire.kandroid.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.icefire.kandroid.ApplicationController;
import com.orhanobut.logger.Logger;

/**
 * Http队列控制类
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public class HttpControl {
    public static final String TAG = "HttpControl";
    private static HttpControl httpControl;
    private RequestQueue mRequestQueue;
    private Context mContext;
    public HttpControl(Context context){
        this.mContext=context;
    }

    public static synchronized HttpControl getInstance(Context context){
        if (httpControl==null){
            httpControl=new HttpControl(context);
        }
        return httpControl;
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            synchronized (ApplicationController.class){
                if (mRequestQueue == null) {
                    mRequestQueue = Volley
                            .newRequestQueue(mContext);
                }
            }
        }
        return mRequestQueue;
    }

    /**
     * 添加请求到全局的队列中(添加Tag)
     * @author yangchj
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
        Logger.i("Adding request to queue: %s", req.getUrl());
    }
    /**
     * 添加请求到全局的队列中(默认Tag)
     * @author yangchj
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    /**
     * 取消指定Tag的请求
     * @author yangchj
     * @param tag
     */
    public void cancelPendingRequests(Object tag){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
