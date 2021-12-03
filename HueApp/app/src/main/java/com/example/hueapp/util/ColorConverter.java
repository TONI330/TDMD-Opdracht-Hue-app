package com.example.hueapp.util;

public class ColorConverter {


    public static float[] hsvFromHueColor(int b, int h,int s)
    {
        float[] selectedLampColor = new float[3];
        selectedLampColor[0] = (h / 65535f) * 365f;
        selectedLampColor[1] = s / 254f;
        selectedLampColor[2] = b / 254f;
        return selectedLampColor;
    }

    public static float[] hsvToHueColor(float[] hueColor){
        float[] hsv = new float[3];
        hsv[0] = hueColor[0] / 365f * 65535f;
        hsv[1] = hueColor[1] * 254f;
        hsv[2] = hueColor[2] * 254f;
        return  hsv;
    }




}
