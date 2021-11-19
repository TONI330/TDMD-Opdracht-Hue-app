package com.example.hueapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

interface ItemAddedListener {
    void onListUpdate(int index);
}

public class LampsViewModel extends ViewModel implements LampRecyclerViewAdapter.OnItemClickListener {
    // TODO: Implement the ViewModel
    private final MutableLiveData<Lamp> selected = new MutableLiveData<>();
    private static final String LOGTAG = LampsViewModel.class.getName();
    private Bridge bridge = new Bridge();
    private List<ItemAddedListener> listeners = new LinkedList<>();

    private void triggerListeners(int index) {
        listeners.forEach(listener -> listener.onListUpdate(index));
    }

    public boolean addListUpdateListener(ItemAddedListener listener) {
        if (!listeners.contains(listener)) { listeners.add(listener);
            return true;
        }
        return false;
    }


    public LiveData<Lamp> getSelected() {
        return selected;
    }

    public Lamp getSelectedLamp() {
        return selected.getValue();
    }

    public List<Lamp> getItems() {
        return bridge.getLamps();
    }


    public boolean addItem(Lamp lamp) {
        List<Lamp> items = getItems();
        if (!items.contains(lamp)) {
            items.add(lamp);
            triggerListeners(items.size());
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(int clickedPosition) {
        selected.setValue(getItems().get(clickedPosition));
        Log.i(LOGTAG, "onItemClick: " + Objects.requireNonNull(selected.getValue()).toString());
    }
}