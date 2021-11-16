package com.example.hueapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HueApiManager {
    private static final String LOGTAG = HueApiManager.class.getName();
    private static final int port = 80;

    private Context appContext;
    private RequestQueue queue;

    public HueApiManager(Context appContext) {
        this.appContext = appContext;
        // Create the RequestQueue for Volley requests
        this.queue = Volley.newRequestQueue(this.appContext);
    }

    public HueApiManager() {

    }

    public void getIpAddress() {
        this.queue.add(getIpAddressRequest());
    }

    public Request getIpAddressRequest() {
        final String url = "http://145.49.51.2/api";
        final Request request = new JsonRequest(Request.Method.POST,
                url,
                getBody(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {
                            //TODO parse received JSON

                            //JSONObject object = response.getJSONObject(0);
                            Log.d("Volley", response.toString());
                            throw new JSONException("gg");
                        } catch (JSONException exception) {
                            Log.e(LOGTAG, "Error while parsing JSON: " + exception.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                    }
                }
        ) {
            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
                return null;
            }

            @Override
            protected void deliverResponse(Object response) {

            }

            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
        return request;
    }

    private String getBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("devicetype", "MijnDevice#Toin");
            Log.d("Volley", "Object:" + object.toString());
        } catch (JSONException e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
        return object.toString();
        }

}
