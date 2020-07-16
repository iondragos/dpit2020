package com.example.dpit2020navem.AddAnObject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dpit2020navem.AddAnObject.Adapter.ObjectTypeListAdapter;
import com.example.dpit2020navem.AddAnObject.Model.Object;
import com.example.dpit2020navem.AddAnObject.Model.ObjectType;
import com.example.dpit2020navem.Help.HelpActivity;
import com.example.dpit2020navem.HomePage.MainActivity;
import com.example.dpit2020navem.ObjectTypeDetailes.ObjectTypeDetailesActivity;
import com.example.dpit2020navem.OwnedObjectsList.OwnedObjectsListActivity;
import com.example.dpit2020navem.Database.OwnedObjectsDatabase;
import com.example.dpit2020navem.MainActivity;
import com.example.dpit2020navem.OwnedObject;
import com.example.dpit2020navem.R;
import com.example.dpit2020navem.Settings.SettingsActivity;
import com.example.dpit2020navem.UvcInfo.UvcInfoActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectTypeMenuActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    List<OwnedObject> ownedObjectList;
    List<Object> objectList;
    List<ObjectType> objectTypeList;
    Context context = this;
    ListView objectTypesListView;
    OwnedObjectsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_type_menu);

        setUpSideMenu();
        openSideMenu();
        finishActivityFromAnotherActivity();
        createObjectListMenu();
        setUpObjectTypeListAdapter();

    }

    private void setUpSideMenu(){
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        sideMenu = (NavigationView)findViewById(R.id.sideMenu);
        sideMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public  boolean onNavigationItemSelected(@NonNull MenuItem item){
                int id = item.getItemId();

                if(id == R.id.homePage){
                    Intent intent = new Intent(ObjectTypeMenuActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.addAnObject){
                    drawerLayout.closeDrawer(sideMenu);
                }else if(id == R.id.ownedObjectList) {
                    Intent intent = new Intent(ObjectTypeMenuActivity.this, OwnedObjectsListActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.objectTypeDetailes) {
                    Intent intent = new Intent(ObjectTypeMenuActivity.this, ObjectTypeDetailesActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.UVCinfo) {
                    Intent intent = new Intent(ObjectTypeMenuActivity.this, UvcInfoActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.settings) {
                    Intent intent = new Intent(ObjectTypeMenuActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.help) {
                    Intent intent = new Intent(ObjectTypeMenuActivity.this, HelpActivity.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    private void openSideMenu(){
        buttonSideMenu = findViewById(R.id.buttonSideMenu);
        buttonSideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(sideMenu);
            }
        });
    }

    private void finishActivityFromAnotherActivity(){
        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish"));
    }

    private void createObjectListMenu(){
        objectList = new ArrayList<>();
        database = new OwnedObjectsDatabase(this);

        /*
        boolean isInserted = database.insertObjectToOwnedObjectsDatabase(1L, "fsssfv", "DSS",324 );

        if(isInserted == true)
            Toast.makeText(ObjectTypeMenuActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ObjectTypeMenuActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

        Cursor c = database.getOwnedObjects();
        ownedObjectList = new ArrayList<>();
        while (c.moveToNext()) {
            objectList.add(new Object(c.getString(3),c.getInt(4)));
        }*/


        OwnedObject test = new OwnedObject();
        test.setOwnedObjectId(1L);
        test.setOwnedObjectName("fsssfv");
        test.setOwnedObjectType("DSS");
        test.setOwnedObjectDisinfectionTime(3453);
        database.addToOwnedObjectsDatabase(test);

        ownedObjectList = new ArrayList<>();
        ownedObjectList = database.getOwnedObjects();


        for (int i = 0; i < ownedObjectList.size(); i++) {
            objectList.add(new Object(ownedObjectList.get(i).getOwnedObjectName(),ownedObjectList.get(i).getOwnedObjectDisinfectionTime()));
        }

        /*objectList.add(new Object("Corona 1",3000));
        objectList.add(new Object("Corona 2",3000));
        objectList.add(new Object("Corona 3",3000));
        objectList.add(new Object("Corona 4",3000));
        objectList.add(new Object("Corona 5",3000));
        objectList.add(new Object("Corona 6",3000));
        objectList.add(new Object("Corona 7",3000));
        objectList.add(new Object("Corona 8",3000));
        objectList.add(new Object("Corona 9",3000));
        objectList.add(new Object("Corona 10",3000));*/

        objectTypeList = new ArrayList<>();
        objectTypeList.add(new ObjectType("Corona I",R.drawable.test,3000,objectList));
        objectTypeList.add(new ObjectType("Corona II",R.drawable.test,3000,objectList));
        objectTypeList.add(new ObjectType("Corona III",R.drawable.test,3000,objectList));
        objectTypeList.add(new ObjectType("Corona IV",R.drawable.test,3000,objectList));
        objectTypeList.add(new ObjectType("Corona V",R.drawable.test,3000,objectList));
        objectTypeList.add(new ObjectType("Corona VI",R.drawable.test,3000,objectList));
        objectTypeList.add(new ObjectType("Corona VII",R.drawable.test,3000,objectList));
    }

    private void setUpObjectTypeListAdapter(){
        ObjectTypeListAdapter adapter = new ObjectTypeListAdapter(context, R.layout.layout_object_type_menu, objectTypeList);
        objectTypesListView = findViewById(R.id.lvObjectTypeList);
        objectTypesListView.setAdapter(adapter);

        objectTypesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ObjectTypeMenuActivity.this, ObjectMenuActivity.class);
                ObjectType selectedItem = (ObjectType) objectTypesListView.getItemAtPosition(position);

                intent.putExtra("Object", (Serializable) selectedItem);
                startActivity(intent);
            }
        });
    }


}