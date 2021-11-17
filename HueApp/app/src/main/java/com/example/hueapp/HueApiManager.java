package com.example.hueapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HueApiManager {
    private static final String LOGTAG = HueApiManager.class.getName();
    private static final int port = 80;
    private static final String IP_ADDRESS = "192.168.178.81";
    private static final String USERNAME = "29a75a76037f028174b65b877409680";

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

    public void getLights() {
        this.queue.add(getLightsRequest());
    }

    public void setLight(Lamp lamp, boolean state) {
        this.queue.add(setLightsRequest(lamp, state));
    }

    public Request getIpAddressRequest() {
        final String url = "http://" + IP_ADDRESS + "/api";
        final Request request = new JsonRequest(Request.Method.POST,
                url,
                getBodyIpAddress(), new Response.Listener<JSONArray>() {
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
                }, new Response.ErrorListener() {
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

    public JsonObjectRequest getLightsRequest() {
        final String url = "http://" + IP_ADDRESS + "/api/" + USERNAME;
        return new JsonObjectRequest(
                url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject lights = response.getJSONObject("lights");
                            printLights(lights);
                        } catch (JSONException e) {
                            Log.d(LOGTAG, e.getLocalizedMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                    }
                });
    }

    public JsonObjectRequest setLightsRequest(Lamp lamp, boolean state) {
        final String url = "http://" + IP_ADDRESS + "/api/" + USERNAME + "/lights/" + lamp.getName() + "/state";
        Log.d(LOGTAG, "SetlightsUrl: " + url);
        return new JsonObjectRequest(Request.Method.PUT,
                url,
                getBodySetLights(state), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(LOGTAG, "Response: " + response.toString());
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOGTAG, error.getLocalizedMessage());
            }
        });
    }

    private JSONObject getBodySetLights(boolean state) {
        JSONObject body = new JSONObject();
        try {
        body.put("on", state);
        } catch(JSONException e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
        return body;
    }

    private String getBodyIpAddress() {
        JSONObject object = new JSONObject();
        try {
            object.put("devicetype", "MijnDevice#Toin");
            Log.d("Volley", "Object:" + object.toString());
        } catch (JSONException e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
        return object.toString();
    }

    private void printLights(JSONObject lights) {
        for (int i = 1; i < lights.length() + 1; i++) {
            try {
             JSONObject light = lights.getJSONObject(i + "");
             Object name = light.get("name");
             Log.d("light", name.toString());
            } catch(JSONException e) {
                Log.e(LOGTAG, e.getLocalizedMessage());
            }
        }
    }
}
