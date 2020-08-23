//mlc
package com.example.dpit2020navem.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dpit2020navem.AddAnObject.Activity.ObjectTypeMenuActivity;
import com.example.dpit2020navem.AddAnObject.Adapter.ObjectListAdapter;
import com.example.dpit2020navem.AddAnObject.Model.ObjectType;
import com.example.dpit2020navem.AddAnObject.Model.OwnedObject;
import com.example.dpit2020navem.Bluetooth.BluetoothService;
import com.example.dpit2020navem.Database.OwnedObjectsDatabase;
import com.example.dpit2020navem.Help.HelpActivity;
import com.example.dpit2020navem.ObjectTypeDetailes.ObjectTypeDetailesActivity;
import com.example.dpit2020navem.OwnedObjectsList.OwnedObjectsListActivity;
import com.example.dpit2020navem.R;
import com.example.dpit2020navem.Settings.SettingsActivity;
import com.example.dpit2020navem.UvcInfo.UvcInfoActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OwnedObjectsListMainPageAdapter.AddButtonListener, ObjectsThatWillBeDisinfectedListMainPageAdapter.RemoveButtonListener{

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    OwnedObjectsDatabase database;
    ListView ownedObjectsMainPageListView;
    List<OwnedObject> ownedObjectMainPageList;
    OwnedObjectsListMainPageAdapter ownedObjectsListMainPageAdapter;
    ListView objectsThatWillBeDisinfectedListView;
    List<OwnedObject> objectsThatWillBeDisinfectedList;
    ObjectsThatWillBeDisinfectedListMainPageAdapter objectsThatWillBeDisinfectedListMainPageAdapter;
    Button buttonChangeBoxState;
    ImageView boxStatePicture;
    boolean open;
    TextView timeRemaining;
    Button startButton;
    CountDownTimer countDownTimer;
    long timeLeftMilliseconds = 600000;
    boolean timerRunning;
    BluetoothSocket btSocket = null;
    BluetoothService bluetoothService;
    boolean mBounded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpSideMenu();
        openSideMenu();
        setUpOwnedObjectsListAdapter();
        openCloseOwnedObjectsListAdapter();
        setUpObjectsThatWillBeDisinfectedListAdapter();
        openCloseObjectsThatWillBeDisinfectedListAdapter();
        changeBoxState();
        appTimer();
        //setUpBluetooth();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, BluetoothService.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service is disconnected", Toast.LENGTH_SHORT).show();
            mBounded = false;
            bluetoothService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service is connected", Toast.LENGTH_SHORT).show();
            mBounded = true;
            BluetoothService.LocalBinder mLocalBinder = (BluetoothService.LocalBinder)service;
            bluetoothService = mLocalBinder.getBluetoothService();
            setUpBluetooth();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    };

    private void setUpBluetooth(){
        bluetoothService.bluetoothConnection(MainActivity.this);

        bluetoothService.connectionBT(MainActivity.this);
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
                }else if(id == R.id.ownedObjectList) {
                    Intent intent = new Intent(MainActivity.this, OwnedObjectsListActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.objectTypeDetailes) {
                    Intent intent = new Intent(MainActivity.this, ObjectTypeDetailesActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.UVCinfo) {
                    Intent intent = new Intent(MainActivity.this, UvcInfoActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.settings) {
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.help) {
                    Intent intent = new Intent(MainActivity.this, HelpActivity.class);
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

    private void setUpOwnedObjectsListAdapter(){
        database = new OwnedObjectsDatabase(this);
        ownedObjectsMainPageListView = findViewById(R.id.ownedObjectsListMainPage);
        ownedObjectMainPageList = new ArrayList<>();

        ownedObjectMainPageList = database.getObjectsByIsObjectInBox(0);

        ownedObjectsListMainPageAdapter = new OwnedObjectsListMainPageAdapter(this, R.layout.layout_home_page_owned_objects_list, ownedObjectMainPageList);
        ownedObjectsListMainPageAdapter.setAddButtonListener(MainActivity.this);
        ownedObjectsMainPageListView.setAdapter(ownedObjectsListMainPageAdapter);
    }

    private void openCloseOwnedObjectsListAdapter(){
        TextView openOwnedObjectList = findViewById(R.id.openOwnedObjectList);
        ownedObjectsMainPageListView = findViewById(R.id.ownedObjectsListMainPage);


        openOwnedObjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ownedObjectsMainPageListView.getVisibility() == View.INVISIBLE) {
                    ownedObjectsMainPageListView.setVisibility(View.VISIBLE);
                    objectsThatWillBeDisinfectedListView.setVisibility(View.INVISIBLE);
                }
                else {
                    ownedObjectsMainPageListView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void OnButtonAddClickListener(int position, OwnedObject ownedObjectAdded) {
        ownedObjectsListMainPageAdapter.clear();
        objectsThatWillBeDisinfectedListMainPageAdapter.clear();

        Long ownedObjectId = ownedObjectAdded.getOwnedObjectId();
        OwnedObject ownedObject = database.getObjectsByObjectId(ownedObjectId);
        database.removeObjectFromOwnedObjectsDatabase(ownedObjectId);
        ownedObject.setIsOwnedObjectInBox(1);
        database.addToOwnedObjectsDatabase(ownedObject);

        ownedObjectsListMainPageAdapter.addAll(database.getObjectsByIsObjectInBox(0));
        ownedObjectsListMainPageAdapter.notifyDataSetChanged();
        objectsThatWillBeDisinfectedListMainPageAdapter.addAll(database.getObjectsByIsObjectInBox(1));
        objectsThatWillBeDisinfectedListMainPageAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Object added to box.", Toast.LENGTH_LONG).show();
    }

    private void setUpObjectsThatWillBeDisinfectedListAdapter(){
        database = new OwnedObjectsDatabase(this);
        objectsThatWillBeDisinfectedListView = findViewById(R.id.objectsThatWillBeDisinfectedListMainPage);
        objectsThatWillBeDisinfectedList = new ArrayList<>();

        objectsThatWillBeDisinfectedList = database.getObjectsByIsObjectInBox(1);

        objectsThatWillBeDisinfectedListMainPageAdapter = new ObjectsThatWillBeDisinfectedListMainPageAdapter(this, R.layout.layout_home_page_objects_that_will_be_disinfected_list, objectsThatWillBeDisinfectedList);
        objectsThatWillBeDisinfectedListMainPageAdapter.setRemoveButtonListener(MainActivity.this);
        objectsThatWillBeDisinfectedListView.setAdapter(objectsThatWillBeDisinfectedListMainPageAdapter);
    }

    private void openCloseObjectsThatWillBeDisinfectedListAdapter(){
        TextView openObjectsThatWillBeDisinfectedList = findViewById(R.id.openObjectsThatWillBeDisinfectedList);
        objectsThatWillBeDisinfectedListView = findViewById(R.id.objectsThatWillBeDisinfectedListMainPage);


        openObjectsThatWillBeDisinfectedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objectsThatWillBeDisinfectedListView.getVisibility() == View.INVISIBLE) {
                    objectsThatWillBeDisinfectedListView.setVisibility(View.VISIBLE);
                }
                else {
                    objectsThatWillBeDisinfectedListView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void OnButtonRemoveClickListener(int position, OwnedObject ownedObjectRemoved) {
        ownedObjectsListMainPageAdapter.clear();
        objectsThatWillBeDisinfectedListMainPageAdapter.clear();

        Long ownedObjectId = ownedObjectRemoved.getOwnedObjectId();
        OwnedObject ownedObject = database.getObjectsByObjectId(ownedObjectId);
        database.removeObjectFromOwnedObjectsDatabase(ownedObjectId);
        ownedObject.setIsOwnedObjectInBox(0);
        database.addToOwnedObjectsDatabase(ownedObject);

        ownedObjectsListMainPageAdapter.addAll(database.getObjectsByIsObjectInBox(0));
        ownedObjectsListMainPageAdapter.notifyDataSetChanged();
        objectsThatWillBeDisinfectedListMainPageAdapter.addAll(database.getObjectsByIsObjectInBox(1));
        objectsThatWillBeDisinfectedListMainPageAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Object removed from box.", Toast.LENGTH_LONG).show();
    }

    private void changeBoxState(){
        buttonChangeBoxState = findViewById(R.id.buttonChangeBoxState);
        boxStatePicture = findViewById(R.id.boxStatePicture);
        open = false;
        boxStatePicture.setImageResource(R.drawable.closed_case);

        buttonChangeBoxState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(open == false) {
                    open = true;
                    boxStatePicture.setImageResource(R.drawable.opened_case);
                    turnOnBox();
                }
                else {
                    open = false;
                    boxStatePicture.setImageResource(R.drawable.closed_case);
                    turnOffBox();
                }
            }
        });
    }
    private void turnOffBox()
    {
        bluetoothService.writeBluetooth("1951off");
    }

    private void turnOnBox()
    {
        bluetoothService.writeBluetooth("1951on");
    }

    private long boxDisinfectionTime(){
        database = new OwnedObjectsDatabase(this);
        List<OwnedObject> objectsCurentlyDisinfeted = database.getObjectsByIsObjectInBox(1);

        long disinfectionTime = 0;
        for(int i = 0 ; i < objectsCurentlyDisinfeted.size() ; i++){
            if(objectsCurentlyDisinfeted.get(i).getOwnedObjectDisinfectionTime() > disinfectionTime){
                disinfectionTime = objectsCurentlyDisinfeted.get(i).getOwnedObjectDisinfectionTime();
            }
        }

        return  disinfectionTime;
    }

    private void appTimer(){
        timeRemaining = findViewById(R.id.timeRemaining);
        startButton = findViewById(R.id.startButton);
        timerRunning = true;


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startStop();
            }
        });

    }
    public void startStop() {
        if (timerRunning) {
            startTimer();
        } else {
            stopTimer();
        }
    }
    public void startTimer() {
        timeLeftMilliseconds = boxDisinfectionTime();
        countDownTimer = new CountDownTimer(timeLeftMilliseconds ,1000) {
            @Override
            public void onTick(long l) {
                timeLeftMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        startButton.setText("PAUSE");
        timerRunning = false;

    }
    public void stopTimer() {
        countDownTimer.cancel();
        startButton.setText("START");
        timerRunning = true;
    }
    public void updateTimer() {
        int minutes = (int) timeLeftMilliseconds / 60000;
        int seconds = (int) timeLeftMilliseconds % 60000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += " : ";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        timeRemaining.setText(timeLeftText);
    }

}
