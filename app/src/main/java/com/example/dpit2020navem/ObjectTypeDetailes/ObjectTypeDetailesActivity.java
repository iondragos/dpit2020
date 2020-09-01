package com.example.dpit2020navem.ObjectTypeDetailes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.dpit2020navem.AddAnObject.Activity.ObjectTypeMenuActivity;
import com.example.dpit2020navem.Help.HelpActivity;
import com.example.dpit2020navem.Help.QuestionsAnswearsListAdapter;
import com.example.dpit2020navem.HomePage.MainActivity;
import com.example.dpit2020navem.OwnedObjectsList.OwnedObjectsListActivity;
import com.example.dpit2020navem.R;
import com.example.dpit2020navem.Settings.SettingsActivity;
import com.example.dpit2020navem.UvcInfo.UvcInfoActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectTypeDetailesActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    private ExpandableListView objectTypeDetailesListView;
    private ObjectTypeDetailesListAdapter objectTypeDetailesListAdapter;
    private List<ListHeader> listType;
    private HashMap< ListHeader , List<String> > listDetailes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_type_detailes);

        setUpSideMenu();
        openSideMenu();
        setUpObjectTypeDetailesListView();

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
                    Intent intent = new Intent(ObjectTypeDetailesActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.addAnObject){
                    Intent intent = new Intent(ObjectTypeDetailesActivity.this, ObjectTypeMenuActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.ownedObjectList) {
                    Intent intent = new Intent(ObjectTypeDetailesActivity.this, OwnedObjectsListActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.objectTypeDetailes) {
                    drawerLayout.closeDrawer(sideMenu);
                }else if(id == R.id.UVCinfo) {
                    Intent intent = new Intent(ObjectTypeDetailesActivity.this, UvcInfoActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.settings) {
                    Intent intent = new Intent(ObjectTypeDetailesActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.help) {
                    Intent intent = new Intent(ObjectTypeDetailesActivity.this, HelpActivity.class);
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

    private void setUpObjectTypeDetailesListView(){
        objectTypeDetailesListView = (ExpandableListView)findViewById(R.id.objectTypeDetailesList);
        initializeObjectTypeDetailesList();
        objectTypeDetailesListAdapter = new ObjectTypeDetailesListAdapter(this,listType,listDetailes);
        objectTypeDetailesListView.setAdapter(objectTypeDetailesListAdapter);
    }

    private void initializeObjectTypeDetailesList(){
        listType = new ArrayList<>();
        listDetailes = new HashMap<>();

        listType.add(new ListHeader("Phones",R.drawable.phone));
        listType.add(new ListHeader("Wallets",R.drawable.wallet));
        listType.add(new ListHeader("Keys",R.drawable.keys));
        listType.add(new ListHeader("Glasses",R.drawable.glasses));
        listType.add(new ListHeader("Watches",R.drawable.watch));
        listType.add(new ListHeader("Laptops",R.drawable.laptops));
        listType.add(new ListHeader("Cameras",R.drawable.cameras));
        listType.add(new ListHeader("Headphones",R.drawable.headphones));
        listType.add(new ListHeader("Mice",R.drawable.mice));
        listType.add(new ListHeader("Chargers/Cables",R.drawable.chargers_cables));
        listType.add(new ListHeader("Remotes/Joysticks",R.drawable.remotes_joysticks));
        listType.add(new ListHeader("Accesories",R.drawable.accesories));
        listType.add(new ListHeader("Books",R.drawable.books));
        listType.add(new ListHeader("Pens",R.drawable.pens));

        List<String> list1 = new ArrayList<>();
        list1.add("phone detailes");

        List<String> list2 = new ArrayList<>();
        list2.add("wallet detailes");

        List<String> list3 = new ArrayList<>();
        list3.add("key detailes");

        List<String> list4 = new ArrayList<>();
        list4.add("glasses detailes");

        List<String> list5 = new ArrayList<>();
        list5.add("watch detailes");

        List<String> list6 = new ArrayList<>();
        list6.add("laptops detailes");

        List<String> list7 = new ArrayList<>();
        list7.add("cameras detailes");

        List<String> list8 = new ArrayList<>();
        list8.add("headphones detailes");

        List<String> list9 = new ArrayList<>();
        list9.add("mice detailes");

        List<String> list10 = new ArrayList<>();
        list10.add("chargers/cables detailes");

        List<String> list11 = new ArrayList<>();
        list11.add("remotes/joysticks detailes");

        List<String> list12 = new ArrayList<>();
        list12.add("accesories detailes");

        List<String> list13 = new ArrayList<>();
        list13.add("books detailes");

        List<String> list14 = new ArrayList<>();
        list14.add("pens detailes");

        listDetailes.put(listType.get(0),list1);
        listDetailes.put(listType.get(1),list2);
        listDetailes.put(listType.get(2),list3);
        listDetailes.put(listType.get(3),list4);
        listDetailes.put(listType.get(4),list5);
        listDetailes.put(listType.get(5),list6);
        listDetailes.put(listType.get(6),list7);
        listDetailes.put(listType.get(7),list8);
        listDetailes.put(listType.get(8),list9);
        listDetailes.put(listType.get(9),list10);
        listDetailes.put(listType.get(10),list11);
        listDetailes.put(listType.get(11),list12);
        listDetailes.put(listType.get(12),list13);
        listDetailes.put(listType.get(13),list14);

    }
}