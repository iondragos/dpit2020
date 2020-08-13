package com.example.dpit2020navem.OwnedObjectsList;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpit2020navem.AddAnObject.Model.ObjectType;
import com.example.dpit2020navem.AddAnObject.Model.OwnedObject;
import com.example.dpit2020navem.Database.OwnedObjectsDatabase;
import com.example.dpit2020navem.HomePage.MainActivity;
import com.example.dpit2020navem.HomePage.OwnedObjectsListMainPageAdapter;
import com.example.dpit2020navem.Intro.IntroActivity;
import com.example.dpit2020navem.R;

import java.util.ArrayList;
import java.util.List;

public class OwnedObjectsListSubItemAdapter extends RecyclerView.Adapter<OwnedObjectsListSubItemAdapter.SubItemViewHolder> {

    private List<OwnedObject> ownedObjectsList;
    Context context;
    OwnedObjectsDatabase database;
    DeleteButtonListener deleteButtonListener;


    OwnedObjectsListSubItemAdapter(List<OwnedObject> ownedObjectsList, Context context) {
        this.ownedObjectsList = ownedObjectsList;
        this.context = context;
        this.database = new OwnedObjectsDatabase(context);
    }



    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_owned_objects_list_sub_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubItemViewHolder subItemViewHolder,final int i) {
        final OwnedObject ownedObjectDeleted = ownedObjectsList.get(i);
        subItemViewHolder.ownedObjectName.setText(ownedObjectDeleted.getOwnedObjectName());

        subItemViewHolder.buttonDeleteObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteButtonListener != null){
                    deleteButtonListener.OnButtonDeleteClickListener(i, ownedObjectDeleted);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ownedObjectsList.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder  {
        TextView ownedObjectName;
        Button buttonDeleteObject;
        RecyclerView ownedObjectsListUpdate;

        SubItemViewHolder(View itemView) {
            super(itemView);
            ownedObjectName = itemView.findViewById(R.id.ownedObjectName);
            buttonDeleteObject = itemView.findViewById(R.id.buttonDeleteObject);
            ownedObjectsListUpdate = itemView.findViewById(R.id.ownedObjectsList);
        }

    }

    public interface DeleteButtonListener
    {
        void OnButtonDeleteClickListener(int position, OwnedObject ownedObjectDeleted);
    }

    public void setDeleteButtonListener(OwnedObjectsListSubItemAdapter.DeleteButtonListener listener)
    {
        this.deleteButtonListener = listener;
    }

}