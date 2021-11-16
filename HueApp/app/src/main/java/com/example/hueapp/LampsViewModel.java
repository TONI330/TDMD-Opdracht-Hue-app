package com.example.hueapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Objects;

public class LampsViewModel extends ViewModel implements LampRecyclerViewAdapter.OnItemClickListener {
    // TODO: Implement the ViewModel
    private final MutableLiveData<Lamp> selected = new MutableLiveData<>();
    private static final String LOGTAG = LampsViewModel.class.getName();
    private Bridge bridge = new Bridge();



    public LiveData<Lamp> getSelected() {
        return selected;
    }
    public Lamp getSelectedLamp() {
        return selected.getValue();
    }

    public List<Lamp> getItems()
    {
        return bridge.getLamps();
    }

    @Override
    public void onItemClick(int clickedPosition) {
        selected.setValue(getItems().get(clickedPosition));
        Log.i(LOGTAG, "onItemClick: " + Objects.requireNonNull(selected.getValue()).toString());
    }
}