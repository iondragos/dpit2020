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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.dpit2020navem.AddAnObject.Activity.ObjectTypeMenuActivity;
import com.example.dpit2020navem.HomePage.MainActivity;
import com.example.dpit2020navem.ObjectTypeDetailes.ObjectTypeDetailesActivity;
import com.example.dpit2020navem.OwnedObjectsList.OwnedObjectsListActivity;
import com.example.dpit2020navem.R;
import com.example.dpit2020navem.Settings.SettingsActivity;
import com.example.dpit2020navem.UvcInfo.UvcInfoActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    Button buttonSideMenu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sideMenu;
    private ExpandableListView questionAnswerListView;
    private QuestionsAnswearsListAdapter questionAnswerListAdapter;
    private List<String> listQuestions;
    private HashMap<String,List<String>> listAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setUpSideMenu();
        openSideMenu();
        setUpQuestionsAnswersListView();

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

    private void setUpQuestionsAnswersListView(){
        questionAnswerListView = (ExpandableListView)findViewById(R.id.questionsAnswersList);
        initializeQuestionsAndAnswers();
        questionAnswerListAdapter = new QuestionsAnswearsListAdapter(this,listQuestions,listAnswers);
        questionAnswerListView.setAdapter(questionAnswerListAdapter);
    }

    private void initializeQuestionsAndAnswers(){
        listQuestions = new ArrayList<>();
        listAnswers = new HashMap<>();

        listQuestions.add("Cati cai pot fi inhamati la caruta?");
        listQuestions.add("Cine bate la fereastra mea?");
        listQuestions.add("Is this a kind of message you want to send out?");
        listQuestions.add("Ce-ai baut?");

        List<String> list1 = new ArrayList<>();
        list1.add("minim doi cai");
        list1.add("maxim sasa cai");

        List<String> list2 = new ArrayList<>();
        list2.add("o gagica cu politia");

        List<String> list3 = new ArrayList<>();
        list3.add("yes");

        List<String> list4 = new ArrayList<>();
        list4.add("tat ce s-o putut inafara de");
        list4.add("5 beri");
        list4.add("2 cidruri");
        list4.add("si cateva shoturi");

        listAnswers.put(listQuestions.get(0),list1);
        listAnswers.put(listQuestions.get(1),list2);
        listAnswers.put(listQuestions.get(2),list3);
        listAnswers.put(listQuestions.get(3),list4);
    }

}