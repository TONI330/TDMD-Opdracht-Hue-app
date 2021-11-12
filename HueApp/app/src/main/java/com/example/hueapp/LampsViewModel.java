package com.example.hueapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LampsViewModel extends ViewModel implements LampRecyclerViewAdapter.OnItemClickListener {
    // TODO: Implement the ViewModel
    private final MutableLiveData<Lamp> selected = new MutableLiveData<>();
    private static final String LOGTAG = LampsViewModel.class.getName();

    public LiveData<Lamp> getSelected() {
        return selected;
    }

    public List<Lamp> getItems()
    {
        return new ArrayList<>();
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.i(LOGTAG, "onItemClick: " + clickedPosition);
        selected.setValue(getItems().get(clickedPosition));
    }
}