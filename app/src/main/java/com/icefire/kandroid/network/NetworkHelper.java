package com.icefire.kandroid.network;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.orhanobut.logger.Logger;
import java.util.Map;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 * implements Listener<T>, Response.ErrorListener
 */
public class NetworkHelper<T>  {

    private static NetworkHelper instance=null;
    private Context mContext;

    public NetworkHelper(Context context) {
        this.mContext=context;
    }

    public static NetworkHelper getInstance(Context context){
        if(instance==null){
            instance=new NetworkHelper(context);
        }
        return instance;
    }
    /**
     * post 请求
     * @author yangchj
     * @param url
     * @param params
     * @return
     */
    protected BaseRequest getPostRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener){
        return new BaseRequest(url, params, listener, errorListener);
    }

    protected BaseRequest getPostRequest(String url, Map<String, String> params,int requestCode,Listener<T> listener,Response.ErrorListener errorListener){
        BaseRequest request=new BaseRequest(url, params, listener, errorListener);
        request.setRequestCode(requestCode);
        return request;
    }

    /**
     * get 请求
     * @author yangchj
     * @param url
     * @param params
     * @return
     */
    protected BaseRequest getGetRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener){
        return new BaseRequest(Request.Method.GET,url, params, listener, errorListener);
    }

    protected BaseRequest getGetRequest(String url, Map<String, String> params,int requestCode,Listener<T> listener,Response.ErrorListener errorListener){
        BaseRequest request=new BaseRequest(Request.Method.GET,url, params, listener, errorListener);
        request.setRequestCode(requestCode);
        return request;
    }

    /**
     * 发送Post请求
     * @author yangchj
     * @param url
     * @param params
     * @param tag
     */
    public void sendPostRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener,String tag) {
        if(params!=null){
            Logger.i("URL:%s,参数:%s", url, params.toString());
        }
        HttpControl.getInstance(mContext).addToRequestQueue(getPostRequest(url, params,listener, errorListener), tag);
    }

    public void sendPostRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener,String tag,int requestCode) {
        if(params!=null){
            Logger.i("URL:%s,参数:%s,requestCode:%s", url, params.toString(),requestCode);
        }
        HttpControl.getInstance(mContext).addToRequestQueue(getPostRequest(url, params,requestCode,listener, errorListener), tag);
    }
    /**
     * 发送Post请求
     * @author yangchj
     * @param url
     * @param params
     */
    public void sendPostRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener) {
        if(params!=null){
            Logger.i("URL:%s,参数:%s", url, params.toString());
        }
        HttpControl.getInstance(mContext).addToRequestQueue(getPostRequest(url, params, listener, errorListener));
    }

    public void sendPostRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener,int requestCode) {
        if(params!=null){
            Logger.i("URL:%s,参数:%s,requestCode:%s", url, params.toString(),requestCode);
        }
        HttpControl.getInstance(mContext).addToRequestQueue(getPostRequest(url, params, requestCode, listener, errorListener));
    }

    /**
     * 发送Get请求
     * @author yangchj
     * @param url
     * @param params
     * @param tag
     */
    public void sendGetRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener,String tag){
        HttpControl.getInstance(mContext).addToRequestQueue(getGetRequest(url, params,listener, errorListener), tag);
    }

    public void sendGetRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener,String tag,int requestCode){
        HttpControl.getInstance(mContext).addToRequestQueue(getGetRequest(url, params,requestCode,listener, errorListener), tag);
    }

    /**
     * 发送Get请求
     * @author yangchj
     * @param url
     * @param params
     */
    public void sendGetRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener){
        HttpControl.getInstance(mContext).addToRequestQueue(getGetRequest(url, params,listener, errorListener));
    }

    public void sendGetRequest(String url, Map<String, String> params,Listener<T> listener,Response.ErrorListener errorListener,int requestCode){
        HttpControl.getInstance(mContext).addToRequestQueue(getGetRequest(url, params,requestCode,listener,errorListener));
    }
}
