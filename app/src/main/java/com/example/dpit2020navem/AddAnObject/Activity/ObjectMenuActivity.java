package com.example.dpit2020navem.AddAnObject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dpit2020navem.AddAnObject.Adapter.ObjectListAdapter;
import com.example.dpit2020navem.AddAnObject.Model.ObjectType;
import com.example.dpit2020navem.AddAnObject.Model.OwnedObject;
import com.example.dpit2020navem.Database.OwnedObjectsDatabase;
import com.example.dpit2020navem.Help.HelpActivity;
import com.example.dpit2020navem.HomePage.MainActivity;
import com.example.dpit2020navem.ObjectTypeDetailes.ObjectTypeDetailesActivity;
import com.example.dpit2020navem.OwnedObjectsList.OwnedObjectsListActivity;
import com.example.dpit2020navem.R;
import com.example.dpit2020navem.Settings.SettingsActivity;
import com.example.dpit2020navem.UvcInfo.UvcInfoActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ObjectMenuActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    ListView ownedObjectsListView;
    List<OwnedObject> ownedObjectList;
    ObjectType objectType;
    Bundle bundle;
    Button buttonAddAnObject;
    OwnedObjectsDatabase database;
    OwnedObject ownedObject;
    Context context = this;
    List<OwnedObject> allObjects;
    ConstraintLayout layoutCreateObjectName;
    EditText etObjectName;
    Button buttonAddAnObjectWithName;
    TextView textViewAddObject;
    TextView warning;
    Long newObjectId;
    String newObjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_menu);

        setUpSideMenu();
        openSideMenu();
        setUpObjectListAdapter();
        setUpLayoutCreateObjectName();
        nameTheObject();

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
                    Intent intent1 = new Intent("finish");
                    sendBroadcast(intent1);
                    Intent intent = new Intent(ObjectMenuActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.addAnObject){
                    drawerLayout.closeDrawer(sideMenu);
                }else if(id == R.id.ownedObjectList) {
                    Intent intent1 = new Intent("finish");
                    sendBroadcast(intent1);
                    Intent intent = new Intent(ObjectMenuActivity.this, OwnedObjectsListActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.objectTypeDetailes) {
                    Intent intent1 = new Intent("finish");
                    sendBroadcast(intent1);
                    Intent intent = new Intent(ObjectMenuActivity.this, ObjectTypeDetailesActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.UVCinfo) {
                    Intent intent1 = new Intent("finish");
                    sendBroadcast(intent1);
                    Intent intent = new Intent(ObjectMenuActivity.this, UvcInfoActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.settings) {
                    Intent intent1 = new Intent("finish");
                    sendBroadcast(intent1);
                    Intent intent = new Intent(ObjectMenuActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.help) {
                    Intent intent1 = new Intent("finish");
                    sendBroadcast(intent1);
                    Intent intent = new Intent(ObjectMenuActivity.this, HelpActivity.class);
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

    private void setUpObjectListAdapter(){
        database = new OwnedObjectsDatabase(this);
        ownedObjectsListView = findViewById(R.id.lvObjectList);
        ownedObjectList = new ArrayList<>();

        bundle = getIntent().getExtras();
        if (bundle != null) {
            objectType = (ObjectType) bundle.get("Object");
            ownedObjectList = database.getObjectsByObjectType(objectType.getObjectTypeName());
        }

        ObjectListAdapter adapter = new ObjectListAdapter(this, R.layout.layout_object_menu, ownedObjectList);
        ownedObjectsListView.setAdapter(adapter);
    }

    private void setUpLayoutCreateObjectName(){
        layoutCreateObjectName = findViewById(R.id.layoutCreateObjectName);
        layoutCreateObjectName.setVisibility(View.INVISIBLE);
    }

    private Long createNewObjectId(){
        allObjects = new ArrayList<>();
        allObjects = database.getOwnedObjects();

        Long objectId = Long.valueOf(allObjects.size() + 1);
        return  objectId;
    }

    private String createNewObjectName(){
        etObjectName = findViewById(R.id.etObjectName);

        String objectName = etObjectName.getText().toString();
        etObjectName.setText("");
        return objectName;
    }


    private void nameTheObject(){
        buttonAddAnObject = findViewById(R.id.buttonAddAnObject);
        buttonAddAnObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewAddObject = findViewById(R.id.textViewAddObject);
                warning = findViewById(R.id.warning);

                layoutCreateObjectName.setVisibility(View.VISIBLE);
                buttonAddAnObject.setVisibility(View.INVISIBLE);
                textViewAddObject.setVisibility(View.INVISIBLE);

                buttonAddAnObjectWithName = findViewById(R.id.buttonAddAnObjectWithName);

                addAnObject();
            }
        });
    }

    private void addAnObject(){
        buttonAddAnObjectWithName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newObjectId = createNewObjectId();
                newObjectName = createNewObjectName();

                warning.setText("");

                if(newObjectName.equals("")){
                    warning.setText("Invalid name");
                }else{
                    warning.setText("");

                    ownedObject = new OwnedObject();
                    ownedObject.setOwnedObjectId(newObjectId);
                    ownedObject.setOwnedObjectType(objectType.getObjectTypeName());
                    ownedObject.setOwnedObjectName(newObjectName);
                    ownedObject.setOwnedObjectDisinfectionTime(3000);

                    database.addToOwnedObjectsDatabase(ownedObject);

                    ownedObjectList = database.getObjectsByObjectType(objectType.getObjectTypeName());
                    ObjectListAdapter adapter = new ObjectListAdapter(context, R.layout.layout_object_menu, ownedObjectList);
                    ownedObjectsListView.setAdapter(adapter);

                    layoutCreateObjectName.setVisibility(View.INVISIBLE);
                    buttonAddAnObject.setVisibility(View.VISIBLE);
                    textViewAddObject.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void onBackPressed() {
        if(layoutCreateObjectName.getVisibility() == View.VISIBLE){
            layoutCreateObjectName.setVisibility(View.INVISIBLE);
            buttonAddAnObject.setVisibility(View.VISIBLE);
            textViewAddObject.setVisibility(View.VISIBLE);
        }else{
            finish();
        }
    }

}