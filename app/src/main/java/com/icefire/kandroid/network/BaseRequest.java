package com.icefire.kandroid.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

//import com.android.volley.Response.Listener;

public class BaseRequest<T> extends StringRequest {

	Map<String,String> paramMap=null;
	/** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";
	private Listener<T> listener;
    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
        String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    
    private final String mRequestBody;
    
    private int request_method;
	private int requestCode=9999;
	public void setRequestCode(int code){
		this.requestCode=code;
	}
	public BaseRequest(int method, String url, Map<String, String> params, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, null, errorListener);
		this.listener=listener;
		this.request_method=method;
		if(method== Method.POST){
			this.paramMap=params;
		}
		setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestBody=paramstoString(params);
	}
	
	
	
	public BaseRequest(String url, Map<String, String> params, Listener<T> listener, ErrorListener errorListener) {
		this(Method.POST, url, params,listener, errorListener);
	}
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return paramMap;
	}
	
//	@Override
//	public String getBodyContentType() {
//		return PROTOCOL_CONTENT_TYPE;
//	}
	
	@Override
	public byte[] getBody() throws AuthFailureError {
		if(request_method!=Method.POST){
			try {
	            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
	        } catch (UnsupportedEncodingException uee) {
	            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
	                    mRequestBody, PROTOCOL_CHARSET);
	            return null;
	        }
		}else{
			return super.getBody();
		}
	}
	
	private static String paramstoString(Map<String, String> params) {
		if (params != null && params.size() > 0) {
			String paramsEncoding = "utf-8";
			StringBuilder encodedParams = new StringBuilder();
			try {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
					encodedParams.append('=');
					encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
					encodedParams.append('&');
				}
				return encodedParams.toString();
			} catch (UnsupportedEncodingException uee) {
				throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
			}
		}
		return null;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		try {
			String res=new String(response.data, PROTOCOL_CHARSET);
			return Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(String response) {
		if(listener!=null){
			listener.onResponse(response,requestCode);
		}
	}
}
