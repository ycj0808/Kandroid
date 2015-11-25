package com.icefire.kandroid.network;

/**
 * Created by Administrator on 2015/10/1.
 */
public interface Listener<T>{
     void onResponse(String response, int requestCode);
}
