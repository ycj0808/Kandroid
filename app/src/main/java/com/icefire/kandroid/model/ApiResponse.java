package com.icefire.kandroid.model;

/**
 * Created by yangchj on 2015/11/24.
 * email:ycj0808@126.com
 */
public class ApiResponse<T> {
    private String event;    // 返回码，0为成功
    private String msg;      // 返回信息
    private T obj;           // 单个对象
    private T objList;       // 数组对象
    private int currentPage; // 当前页数
    private int pageSize;    // 每页显示数量
    private int maxCount;    // 总条数
    private int maxPage;     // 总页数

    // 构造函数，初始化code和msg
    public ApiResponse(String event, String msg) {
        this.event = event;
        this.msg = msg;
    }

    // 判断结果是否成功
    public boolean isSuccess() {
        return event.equals("0");
    }
}
