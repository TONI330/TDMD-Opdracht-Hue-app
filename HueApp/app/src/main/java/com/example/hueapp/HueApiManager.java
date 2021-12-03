package com.example.hueapp;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


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

import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Map;

interface LightController {
    void setLight(Lamp lamp, boolean state);

    void setLightColor(Lamp lamp, float[] hsv);
}

public class HueApiManager implements LightController {
    private static final String LOGTAG = HueApiManager.class.getName();

    private AppCompatActivity appContext;
    private RequestQueue queue;

    private LampsViewModel mViewModel;

    public HueApiManager(AppCompatActivity appContext) {
        this.appContext = appContext;
        // Create the RequestQueue for Volley requests
        this.queue = Volley.newRequestQueue(this.appContext);
        mViewModel = new ViewModelProvider(appContext).get(LampsViewModel.class);
    }

    public HueApiManager() {
    }

    private String getIpAndPortCombo() {
        String IP_AND_PORT = "192.168.2.38:" + 8000;

        String value = KeyValueStorage.getValue(appContext, R.string.chosenIp);
        if (!value.isEmpty())
            IP_AND_PORT = value;

        return IP_AND_PORT;
    }

    private String getUsername() {
        String IP_AND_PORT = "";

        String value = KeyValueStorage.getValue(appContext, R.string.username);
        if (!value.isEmpty())
            IP_AND_PORT = value;

        return IP_AND_PORT;
    }

    private String getApiEndPoint() {
        return "http://" + getIpAndPortCombo() + "/api/" + getUsername();
    }

    public void getIpAddress() {
        this.queue.add(getIpAddressRequest());
    }

    public void getLights() {
        this.queue.add(getLightsRequest());
    }

    public void setLight(Lamp lamp, boolean state) {
        this.queue.add(genStateRequest(lamp, getBody("on", state)));
    }

    public void setLightColor(Lamp lamp, float[] hsv) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("hue", hsv[0]);
        stringObjectHashMap.put("sat", hsv[1]);
        stringObjectHashMap.put("bri", hsv[2]);
//        this.queue.cancelAll("hue");
        Request<?> request = genStateRequest(lamp, getBody(stringObjectHashMap)).setTag("hue");
        this.queue.add(request);
    }

    public CustomJsonArrayRequest getIpAddressRequest() {
        final String url = "http://" + getIpAndPortCombo() + "/api";
        return new CustomJsonArrayRequest(Request.Method.POST,
                url,
                getBodyIpAddress(),
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());
                    try {
                        JSONObject object = response.getJSONObject(0);
                        if (object.toString().contains("success")) {
                            setUsername(object.getJSONObject("success").getString("username"));
                            Toast.makeText(appContext, "Linked successfully!", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("Volley", response.toString());
                    } catch (JSONException exception) {
                        Log.e(LOGTAG, "Error while parsing JSON: " + exception.getLocalizedMessage());
                    }
                    getLights();
                }, error -> Log.e(LOGTAG, error.getLocalizedMessage() != null ? error.getLocalizedMessage() : ""));
    }

    private void setUsername(String username) {
        Log.i(LOGTAG, "Got username: " + username);
        KeyValueStorage.setValue(appContext, R.string.username, username);
    }

    public JsonObjectRequest getLightsRequest() {
        final String url = getApiEndPoint();
        return new JsonObjectRequest(
                url, response -> {
            try {
                Log.i(LOGTAG, "getLightsRequest: " + response.toString());
                JSONObject lights = response.getJSONObject("lights");
                getLampsForViewModel(lights);
                setConnected(true);
            } catch (JSONException e) {
                Log.d(LOGTAG, "1" + e.getLocalizedMessage());
            }
        }, error -> {
            if (error.getLocalizedMessage() != null) {
                setConnected(false);
                Log.e(LOGTAG, error.getLocalizedMessage());
            } else Log.e(LOGTAG, "3");
        });
    }

    public void setConnected(boolean isConnected) {
        this.mViewModel.setIsConnected(isConnected);
    }


//    public JsonObjectRequest setLightsRequest(Lamp lamp, boolean state) {
//        final String url = getApiEndPoint() + "/lights/" + lamp.getID() + "/state";
//        Log.d(LOGTAG, "SetlightsUrl: " + url);
//        return new JsonObjectRequest(Request.Method.PUT,
//                url,
//                getBodySetLights(state), response -> Log.i(LOGTAG, "Response: " + response.toString()), error -> Log.e(LOGTAG, error.getLocalizedMessage()));
//    }

    public JsonRequest<JSONArray> genStateRequest(Lamp lamp, JSONObject requestData) {
        final String url = getApiEndPoint() + "/lights/" + lamp.getID() + "/state";
        Log.d(LOGTAG, "SetlightsUrl: " + url);

        return new CustomJsonArrayRequest(Request.Method.PUT,
                url,
                requestData, response -> Log.i(LOGTAG, "Response1: " + response.toString()), error -> Log.e(LOGTAG, error.getLocalizedMessage()));

    }

//    private JSONObject getBodySetLights(boolean state) {
//        return getBody("on", state);
//    }

    private JSONObject getBody(String param, Object object) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put(param, object);
        return getBody(stringObjectHashMap);
    }

    private JSONObject getBody(Map<String, Object> stringObjectDictionary) {
        JSONObject body = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : stringObjectDictionary.entrySet()) {
                body.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
        return body;
    }


    private JSONObject getBodyIpAddress() {
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        try {
            object.put("devicetype", "MijnDevice#Toin");
            Log.d("Volley", "Object:" + object.toString());
        } catch (JSONException e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
        array.put(array);
        return object;
    }

//    private void printLights(JSONObject lights) {
//        for (int i = 1; i < lights.length() - 1; i++) {
//            try {
//                JSONObject light = lights.getJSONObject(i + "");
//                String name = light.getString("name");
//                Log.d("light", name);
//            } catch (JSONException e) {
//                Log.e(LOGTAG, e.getLocalizedMessage());
//            }
//        }
//    }

    private void getLampsForViewModel(JSONObject lights) {
        for (int i = 1; i < lights.length() + 1; i++) {
            try {
                JSONObject light = lights.getJSONObject(i + "");
                String name = light.getString("name");
                JSONObject state = light.getJSONObject("state");
                boolean on = state.getBoolean("on");
                mViewModel.addItem(new HueLamp(name, i + "", on, this));
                Log.d("light", name);
            } catch (JSONException e) {
                Log.e(LOGTAG, e.getLocalizedMessage());
            }
        }
        for (Lamp lamp : mViewModel.getItems()) {
            Log.i(LOGTAG, lamp.toString());
        }
    }
}
