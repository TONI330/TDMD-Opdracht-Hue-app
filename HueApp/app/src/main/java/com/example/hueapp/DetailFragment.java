package com.example.hueapp;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hueapp.util.ColorConverter;
import com.madrapps.pikolo.ColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

import java.util.Arrays;
import java.util.Objects;


public class DetailFragment extends Fragment {

    private LampsViewModel mViewModel;
    private static final String LOGTAG = DetailFragment.class.getName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mViewModel = new ViewModelProvider(requireActivity()).get(LampsViewModel.class);
        update();
        final boolean[] done = {false};
        ImageView imageView = view.findViewById(R.id.colorPickerCircle);
        ColorPicker colorPicker = view.findViewById(R.id.colorPicker);
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                // Do whatever you want with the color
                imageView.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                float[] hsv = new float[3];
                Color.colorToHSV(color, hsv);

                mViewModel.getSelectedLamp().setColor(hsv);
//                if (!done[0]) {
//                    mViewModel.getSelectedLamp().setColor(hsv);
//                    done[0] = true;
//                }

                Log.i(LOGTAG, "onColorSelected: " + color);
            }
        });
        float[] selectedLampColor = mViewModel.getSelectedLamp().getColor();
        Log.d("color", Arrays.toString(selectedLampColor));

        int color = Color.HSVToColor(selectedLampColor);
        Log.d("color", color+"");
        colorPicker.setColor(color);
        imageView.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        update();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(LampsViewModel.class);
        // TODO: Use the ViewModel


    }

    public void update() {
        TextView lampName = requireView().findViewById(R.id.lampName);
        TextView detailInfo = requireView().findViewById(R.id.detailInfo);

        Lamp lamp = mViewModel.getSelected().getValue();

        String detailInfoText = "Id: " + (lamp != null ? lamp.getID() : null);
        detailInfoText += "\nState: " + (lamp != null && lamp.getState());
        detailInfoText += "\nColor: " + (lamp != null ? Arrays.toString(lamp.getColor()) : null);

        if (lamp != null && lampName != null) lampName.setText(lamp.getName());
        if (lamp != null && detailInfo != null) detailInfo.setText(detailInfoText);

    }
}