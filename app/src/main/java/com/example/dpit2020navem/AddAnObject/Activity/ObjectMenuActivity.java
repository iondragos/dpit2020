package com.example.dpit2020navem.AddAnObject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.dpit2020navem.AddAnObject.Adapter.ObjectListAdapter;
import com.example.dpit2020navem.AddAnObject.Model.Object;
import com.example.dpit2020navem.AddAnObject.Model.ObjectType;
import com.example.dpit2020navem.MainActivity;
import com.example.dpit2020navem.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ObjectMenuActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    ListView objectsListView;
    List<Object> objectList;
    Bundle bundle;
    Button buttonAddAnObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_menu);

        setUpSideMenu();
        openSideMenu();
        setUpObjectListAdapter();
        buttonAddAnObject = findViewById(R.id.addAnObject);

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
                    Intent intent = new Intent(ObjectMenuActivity.this, MainActivity.class);
                    startActivity(intent);
                }else if(id == R.id.addAnObject){
                    drawerLayout.closeDrawer(sideMenu);
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
        objectsListView = findViewById(R.id.lvObjectList);
        objectList = new ArrayList<>();

        bundle = getIntent().getExtras();
        if (bundle != null) {
            ObjectType objectType = (ObjectType) bundle.get("Object");
            objectList = objectType.getObjectList();
        }

        ObjectListAdapter adapter = new ObjectListAdapter(this, R.layout.layout_object_menu, objectList);
        objectsListView.setAdapter(adapter);
    }

}