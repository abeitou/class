package com.example.contextactionmodetest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by 振丿Love on 2018/4/2.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    private int resourceId;

    private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();

    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }
        else {
            view = convertView;
        }

        ImageView picture = (ImageView) view.findViewById(R.id.pic);
        TextView name = (TextView) view.findViewById(R.id.name);
        picture.setImageResource(item.getImageId());
        name.setText(item.getName());

        view.setBackgroundColor(view.getResources().getColor(android.R.color.background_light));//default color

        if (mSelection.get(position) != null) {
            view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_light));// this is a selected position so make it red
        }

        return view;
    }

    public void setNewSelection(int position, boolean value) {
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public boolean isPositionChecked(int position) {
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }

    public Set<Integer> getCurrentCheckedPosition() {
        return mSelection.keySet();
    }

    public void removeSelection(int position) {
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }

    public HashMap getMap() {
        return  mSelection;
    }
}
