package com.example.dpit2020navem.HomePage;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dpit2020navem.AddAnObject.Model.OwnedObject;
import com.example.dpit2020navem.Database.OwnedObjectsDatabase;
import com.example.dpit2020navem.R;

import java.util.List;

public class OwnedObjectsListMainPageAdapter extends ArrayAdapter<OwnedObject> {

    private Context mContext;
    int mResource;
    OwnedObjectsDatabase database;

    public OwnedObjectsListMainPageAdapter(Context context, int resource, List<OwnedObject> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        database = new OwnedObjectsDatabase(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String ownedObjectName = getItem(position).getOwnedObjectName();
        final Long ownedObjectId = getItem(position).getOwnedObjectId();
        Integer ownedObjectDisinfectionTime  = getItem(position).getOwnedObjectDisinfectionTime();
        Integer isOwnedObjectInBox  = getItem(position).getIsOwnedObjectInBox();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        TextView ownedObjectsListMainPageName = (TextView) convertView.findViewById(R.id.ownedObjectsListMainPageName);
        TextView ownedObjectsListMainPageDetailes = (TextView) convertView.findViewById(R.id.ownedObjectsListMainPageDetailes);
        Button ownedObjectsListMainPageAdd = convertView.findViewById(R.id.ownedObjectsListMainPageAdd);

        ownedObjectsListMainPageName.setText(ownedObjectName);
        ownedObjectsListMainPageDetailes.setText("Disinfection time: " + ownedObjectDisinfectionTime);

        ownedObjectsListMainPageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();

                OwnedObject ownedObject = database.getObjectsByObjectId(ownedObjectId);
                database.removeObjectFromOwnedObjectsDatabase(ownedObjectId);
                ownedObject.setIsOwnedObjectInBox(1);
                database.addToOwnedObjectsDatabase(ownedObject);

                addAll(database.getObjectsByIsObjectInBox(0));
                notifyDataSetChanged();

                Toast.makeText(mContext, "Object added to box.", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
