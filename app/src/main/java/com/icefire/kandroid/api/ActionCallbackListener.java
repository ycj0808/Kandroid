package com.icefire.kandroid.api;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public interface ActionCallbackListener<T> {
    /**
     * 成功回调
     * @param data
     * @param reponseCode
     */
    void onSuccess(T data,int reponseCode);

    /**
     * 失败时回调
     * @param errorEvent
     * @param message
     */
    void onFailure(String errorEvent, String message);
}
