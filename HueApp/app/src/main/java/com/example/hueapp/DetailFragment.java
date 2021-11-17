package com.example.hueapp;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;


public class DetailFragment extends Fragment {

    private LampsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mViewModel = new ViewModelProvider(requireActivity()).get(LampsViewModel.class);
        update();

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(LampsViewModel.class);
        // TODO: Use the ViewModel


    }

    public void update() {
        TextView textView = requireView().findViewById(R.id.basicBitch);
        Lamp lamp = mViewModel.getSelected().getValue();
        if (lamp != null) {
            textView.setText(lamp.getName());
        }
    }
}