package com.example.hueapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Lamp}.
 * TODO: Replace the implementation with code for your data type.
 */
public class LampRecyclerViewAdapter extends RecyclerView.Adapter<LampRecyclerViewAdapter.ViewHolder> {
    private static final String LOGTAG = ViewHolder.class.getName();
    private final List<Lamp> mValues;
    private OnItemClickListener clickListener;

    // This interface isolates us from classes that want to listen to item clicks
    // so we don't need to know what those classes are
    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    public LampRecyclerViewAdapter(List<Lamp> items, OnItemClickListener listener) {
        mValues = items;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lamp_list_item, parent, false);
        return new ViewHolder(itemView);


        //return new ViewHolder(FragmentOverviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mLampItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getID());
        //holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mIdView;
        //public final TextView mContentView;
        public Lamp mLampItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mIdView = itemView.findViewById(R.id.item_number);
            itemView.setOnClickListener(this);
        }


        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            // Find out which item in the list was clicked by retrieving the position in the adapter

            int clickedPosition = getBindingAdapterPosition();
            Log.i(LOGTAG, "Item " + clickedPosition + " was clicked");
            // Notify our listener that an item was clicked
            clickListener.onItemClick(clickedPosition);
        }
    }
}