package com.aim.sliitquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aim.sliitquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionActivity extends AppCompatActivity {

    Button b5;
    TextView qus,timer,qCount;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4,rb;
    int correct = 0;
    int wrong = 0;
    int skip = 0;
    int total = 1;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        b5 = findViewById(R.id.button5);

        rb1 =findViewById(R.id.radioButton);
        rb2 =findViewById(R.id.radioButton2);
        rb3 =findViewById(R.id.radioButton3);
        rb4 =findViewById(R.id.radioButton4);

        radioGroup = findViewById(R.id.radioGroup);

        qus = findViewById(R.id.textView);
        timer = findViewById(R.id.textTimer);
        qCount = findViewById(R.id.textNum);

        next();
        reverseTimer(90,timer);
    }

    /*
    private void updateQuestion(){

        if(total > 5){

        }else{
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);

                    qus.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());


                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b1.getText().toString().equals(question.getAnswer())){

                                        correct++;
                                        updateQuestion();


                            }else{
                                wrong++;
                                updateQuestion();
                            }
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b2.getText().toString().equals(question.getAnswer())){

                                        correct++;
                                        updateQuestion();


                            }else{
                                wrong++;
                                updateQuestion();
                            }
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b1.getText().toString().equals(question.getAnswer())){

                                        correct++;
                                        updateQuestion();



                            }else{
                                wrong++;
                                updateQuestion();
                            }
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b1.getText().toString().equals(question.getAnswer())){
                                        correct++;
                                        updateQuestion();

                            }else{
                                wrong++;
                                updateQuestion();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        total++;
    }
    */

    public void next(){
        if(total > 5){

        }else{
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);

                    qus.setText(" "+(total-1)+" . "+question.getQuestion());
                    rb1.setText(question.getOption1());
                    rb2.setText(question.getOption2());
                    rb3.setText(question.getOption3());
                    rb4.setText(question.getOption4());
                    qCount.setText((total-1)+"/"+5);

                    b5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final int s = radioGroup.getCheckedRadioButtonId();
                            rb = findViewById(s);
                            if(s == -1) {
                                skip++;
                                next();
                            }else if(rb.getText().toString().equals(question.getAnswer())){
                                correct++;
                                next();
                            }else{
                                wrong++;
                                next();
                            }
                            radioGroup.clearCheck();
                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        total++;

    }

    public void reverseTimer(int seconds,final TextView tv){
        new CountDownTimer(seconds*1000,1000){

            public void onTick(long millisUntilFinished){
                int seconds = (int)(millisUntilFinished/1000);
                int minutes = seconds/60;
                seconds = seconds % 60;
                if(minutes == 0) {
                    tv.setTextColor(getResources().getColor(R.color.timerRed));
                }
                tv.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds));
            }
            public void onFinish(){
                tv.setText("Finished");

            }
        }.start();
    }



}
