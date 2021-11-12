package com.example.hueapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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

    public void getIpAddress() {
        final String url = "http://192.168.178.81/api";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                url,
                getBody(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {
                            //TODO parse received JSON
                            JSONObject object = response.getJSONObject(0);
                            Log.d("Volley", object.toString());
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
        );

        this.queue.add(request);
    }

    private JSONArray getBody() {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            object.put("devicetype", "MijnDevice#Toin");
            Log.d("Volley", "Object:" + object.toString());
            array.put(object);
            Log.d("Volley", "Array:" + array.toString());
        } catch (JSONException e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
        return array;
    }

}
