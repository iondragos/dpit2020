package com.example.dpit2020navem.Help;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dpit2020navem.AddAnObject.Activity.ObjectTypeMenuActivity;
import com.example.dpit2020navem.HomePage.MainActivity;
import com.example.dpit2020navem.ObjectTypeDetailes.ObjectTypeDetailesActivity;
import com.example.dpit2020navem.OwnedObjectsList.OwnedObjectsListActivity;
import com.example.dpit2020navem.R;
import com.example.dpit2020navem.Settings.SettingsActivity;
import com.example.dpit2020navem.UvcInfo.UvcInfoActivity;
import com.google.android.material.navigation.NavigationView;

public class HelpActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setUpSideMenu();
        openSideMenu();

    }


    private void setUpSideMenu() {
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        sideMenu = (NavigationView) findViewById(R.id.sideMenu);
        sideMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.homePage) {
                    Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.addAnObject) {
                    Intent intent = new Intent(HelpActivity.this, ObjectTypeMenuActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.ownedObjectList) {
                    Intent intent = new Intent(HelpActivity.this, OwnedObjectsListActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.objectTypeDetailes) {
                    Intent intent = new Intent(HelpActivity.this, ObjectTypeDetailesActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.UVCinfo) {
                    Intent intent = new Intent(HelpActivity.this, UvcInfoActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.settings) {
                    Intent intent = new Intent(HelpActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.help) {
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

    private void openSideMenu() {
        buttonSideMenu = findViewById(R.id.buttonSideMenu);
        buttonSideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(sideMenu);
            }
        });

    }
}