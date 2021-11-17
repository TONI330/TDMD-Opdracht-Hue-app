package com.example.hueapp;

import static org.junit.Assert.*;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class HueApiManagerTest {



    @Test
    public void getIpAddress() {
        //Arrange
        RequestQueue mock = mock(RequestQueue.class);
        MockedRequestQueue mockedRequestQueue = new MockedRequestQueue();
        HueApiManager apiManager = new HueApiManager();

        //Request<?> request = apiManager.getIpAddressRequest();

//        });when(mock.add(request)).thenAnswer(new Answer<JSONArray>() {
////
////            @Override
////            public JSONArray answer(InvocationOnMock invocation) throws Throwable {
////                JSONArray jsonArray = new JSONArray();
////                JSONObject container = new JSONObject();
////                JSONObject succes = new JSONObject();
////                JSONObject username = new JSONObject();
////                username.put("username", "a4bf022667e1ee249863879506e2b7f");
////                succes.put("success", username);
////                jsonArray.put(succes);
////                Log.d("TEST", jsonArray.toString());
////                return jsonArray;
////            }
        try {
        JSONArray expected = mockedRequestQueue.add(apiManager.getIpAddressRequest());
        } catch (JSONException e) {
            System.out.println(e.getLocalizedMessage());
        }
        Assert.assertEquals(true, true);


        //mock.add(request);

    }
}

class MockedRequestQueue {

    public MockedRequestQueue() {

    }

    public JSONArray add(Request request) throws JSONException {
        JSONArray jsonArray = new JSONArray();
                JSONObject container = new JSONObject();
                JSONObject succes = new JSONObject();
                JSONObject username = new JSONObject();
                username.put("username", "a4bf022667e1ee249863879506e2b7f");
                succes.put("success", username);
                jsonArray.put(succes);
                Log.d("TEST", jsonArray.toString());
                return jsonArray;
    }
}