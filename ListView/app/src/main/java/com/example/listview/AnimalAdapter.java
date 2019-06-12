package com.example.listview;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 振丿Love on 2018/3/26.
 */

public class AnimalAdapter extends ArrayAdapter<Animal>{
    private int resourceId;

    public AnimalAdapter(Context context, int textViewResourceId, List<Animal> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Animal animal = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }
        else {
            view = convertView;
        }

        ImageView picture = (ImageView) view.findViewById(R.id.Picture);
        TextView name = (TextView) view.findViewById(R.id.Name);
        picture.setImageResource(animal.getImageId());
        name.setText(animal.getName());

        return view;
    }
}
