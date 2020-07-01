package com.example.dpit2020navem.AddAnObject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dpit2020navem.AddAnObject.Model.Object;
import com.example.dpit2020navem.R;

import java.util.List;

public class ObjectListAdapter extends ArrayAdapter<Object> {
    private Context mContext;
    int mResource;

    public ObjectListAdapter(Context context, int resource, List<Object> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String objectName = getItem(position).getObjectName();
        Integer objectRemainingDisinfectionTime  = getItem(position).getObjectRemainingDisinfectionTime();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        TextView tvObjectName = (TextView) convertView.findViewById(R.id.tvObjectName);

        tvObjectName.setText(objectName);

        return convertView;
    }
}
