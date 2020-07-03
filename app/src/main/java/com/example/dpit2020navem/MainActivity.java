//mlc
package com.example.dpit2020navem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dpit2020navem.AddAnObject.Activity.ObjectTypeMenuActivity;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    Button buttonChangeBoxState;
    TextView boxState;
    boolean open;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpSideMenu();
        openSideMenu();
        changeBoxState();

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
                    drawerLayout.closeDrawer(sideMenu);
                }else if(id == R.id.addAnObject){
                    Intent intent = new Intent(MainActivity.this, ObjectTypeMenuActivity.class);
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

    private void changeBoxState(){
        buttonChangeBoxState = findViewById(R.id.buttonChangeBoxState);
        boxState = findViewById(R.id.boxState);
        open = false;
        boxState.setText("box closed");

        buttonChangeBoxState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(open == false) {
                    open = true;
                    boxState.setText("box opened");
                }
                else {
                    open = false;
                    boxState.setText("box closed");
                }
            }
        });
    }
}
