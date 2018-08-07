package com.nytimes.android.nytimesapp;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nytimes.android.nytimesapp.app.NyTimesApp;
import com.nytimes.android.nytimesapp.utils.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ApiCallVolley {

    private String TAG = "ApiCallVolley";

    public void makeApiCall(String Url, int MethodType, Map<String, String> postParams, boolean oauth, JSONObject jsonRequest, final VolleyCallback callback) {

        final boolean isOauth = oauth;
        final Map<String, String> postParamsforAPI = postParams;
        if (MethodType == 0) {
            MethodType = Method.GET;

        } else if (MethodType == 1) {
            MethodType = Method.POST;
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(MethodType,
                Url, jsonRequest,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put(Constants.ServerKeys.CONTENT_TYPE, Constants.ServerKeys.APPLICATION_URLENCODED);
                headers.put(Constants.ServerKeys.KEY, Constants.ServerKeys.ACCESS_KEY);

                if (isOauth) {
                    //headers.put(Constants.ServerKeys.AUTHORZATION, Constants.ServerKeys.OAUTHTOKEN);
                }

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                return postParamsforAPI;
            }

        };


        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        // Adding request to request queue
        NyTimesApp.getInstance().addToRequestQueue(jsonObjReq,
                Constants.ServerKeys.JSONOBECTREQUSET);
    }


    public interface VolleyCallback {
        void onSuccess(JSONObject result);

        void onFailure(VolleyError error);
    }
}
