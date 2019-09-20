package com.aim.sliitquizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void selectBtn(View view){
        Intent intent = new Intent(HomeActivity.this,SelectquizActivity.class);
        startActivity(intent);
    }

    public void statBtn(View view){
        Intent intent = new Intent(HomeActivity.this,StatisticsActivity.class);
        startActivity(intent);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }else if(id == R.id.action_clear){
            DatabaseReference dbNode = FirebaseDatabase.getInstance().getReference().getRoot().child("Statistics");
            dbNode.setValue(null);
            Toast.makeText(HomeActivity.this, "Records Cleared Successfully", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about){

        }

        return super.onOptionsItemSelected(item);
    }
}
