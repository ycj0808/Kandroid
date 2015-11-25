package com.icefire.kandroid.core;

import com.icefire.kandroid.api.ActionCallbackListener;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public interface AppAction {

    void login(String loginName, String password, ActionCallbackListener<Void> listener);
}
