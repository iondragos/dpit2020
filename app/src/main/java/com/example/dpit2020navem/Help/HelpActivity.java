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

        listQuestions.add("What should I do if the UVC light is not working?");
        listQuestions.add("What should I do if the box is not closing properly?");
        listQuestions.add("What can I do if an object type i need is not in the list?");
        listQuestions.add("Who worked on this project?");
        listQuestions.add("How can I contact you?");

        List<String> list1 = new ArrayList<>();
        list1.add("If the light is not working, try resetting the box multiple times and check that the led is not burnt. Otherwise, contact us.");

        List<String> list2 = new ArrayList<>();
        list2.add("Check on the app that the box is opened and make sure the switch is not broken, in which case you should call us.");

        List<String> list3 = new ArrayList<>();
        list3.add("Check the object type details, you can add your own object type with time.");

        List<String> list4 = new ArrayList<>();
        list4.add("This app was made by Team Navem formed by Ignat Alex, Ion Dragos, Abdallah Laith, Vaduva Mara, Hantig Lorena and Ordean Mihnea within the DPIT Association.");

        List<String> list5 = new ArrayList<>();
        list5.add("You can contact us on the phone or email:");
        list5.add("navemdpit@gmail.com");
        list5.add("+40746551087");

        listAnswers.put(listQuestions.get(0),list1);
        listAnswers.put(listQuestions.get(1),list2);
        listAnswers.put(listQuestions.get(2),list3);
        listAnswers.put(listQuestions.get(3),list4);
        listAnswers.put(listQuestions.get(4),list5);
    }

}