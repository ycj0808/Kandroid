package com.icefire.kandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icefire.kandroid.ApplicationController;
import com.icefire.kandroid.R;
import com.icefire.kandroid.core.AppAction;

/**
 * 基类
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;
    public ApplicationController appController;
    public AppAction appAction;

    protected Toolbar toolbar;
    protected TextView toolbarTitle;
    protected LinearLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        init();
        initToolbar();
    }

    /**
     * 初始化
     */
    private void init(){
        mContext=this;
        appController= (ApplicationController) getApplication();
        appAction=appController.getAppAction();
    }

    /**
     * Toolbar初始化
     */
    private void initToolbar(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            toolbarTitle= (TextView) toolbar.findViewById(R.id.toolbar_title);
            if(toolbarTitle!=null){
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

    protected abstract void initView();

    protected abstract void setListener();

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout= (LinearLayout) findViewById(R.id.root_layout);
        if(rootLayout==null) return;
        rootLayout.addView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
        initView();
        setListener();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }
}
