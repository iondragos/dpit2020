package com.example.dpit2020navem.AddAnObject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.dpit2020navem.AddAnObject.Adapter.ObjectTypeListAdapter;
import com.example.dpit2020navem.AddAnObject.Model.Object;
import com.example.dpit2020navem.AddAnObject.Model.ObjectType;
import com.example.dpit2020navem.MainActivity;
import com.example.dpit2020navem.R;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectTypeMenuActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    List<Object> objectList;
    List<ObjectType> objectTypeList;
    Context context = this;
    ListView objectTypesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_type_menu);

        setUpSideMenu();
        openSideMenu();
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

    private void createObjectListMenu(){
        objectList = new ArrayList<>();
        objectTypeList = new ArrayList<>();
        objectList.add(new Object("Corona 1",3000));
        objectList.add(new Object("Corona 2",3000));
        objectList.add(new Object("Corona 3",3000));
        objectList.add(new Object("Corona 4",3000));
        objectList.add(new Object("Corona 5",3000));
        objectList.add(new Object("Corona 6",3000));
        objectList.add(new Object("Corona 7",3000));
        objectList.add(new Object("Corona 8",3000));
        objectList.add(new Object("Corona 9",3000));
        objectList.add(new Object("Corona 10",3000));

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