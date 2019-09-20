package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.aim.sliitquizapp.model.CustomGridViewActivity;

public class SelectquizActivity extends AppCompatActivity {

    GridView androidGridView;
    public static final String SUBJECT = "com.aim.sliitquizapp.SUBJECT";

    String[] gridViewString = {
            "IP", "MC", "SE", "IWT", "OOC", "OOP",
            "MADD", "CN", "DMS",


    } ;
    int[] gridViewImageId = {
            R.drawable.ip, R.drawable.mc, R.drawable.se, R.drawable.iwt, R.drawable.occ, R.drawable.oop,
            R.drawable.madd, R.drawable.cn, R.drawable.dms,


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectquiz);

        final CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(SelectquizActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String sub = gridViewString[+i];
                System.out.println("====="+sub);
                Intent intent = new Intent(SelectquizActivity.this,QuestionActivity.class);
                intent.putExtra(SUBJECT,sub);
                startActivity(intent);
            }
        });
    }
}
