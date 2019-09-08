package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tvP,tvCorrect,tvWrong,tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvSkip = findViewById(R.id.tvSkip);
        tvP = findViewById(R.id.tvPercentage);

        Intent intent = getIntent();

        String cr = intent.getStringExtra(QuestionActivity.CORRECT);
        String wr = intent.getStringExtra(QuestionActivity.WRONG);
        String tot = intent.getStringExtra(QuestionActivity.TOTAL);

        int correct = Integer.parseInt(cr);
        int wrong = Integer.parseInt(wr);
        int total = Integer.parseInt(tot);
        int skip = total-correct-wrong;

        tvCorrect.setText(cr);
        tvWrong.setText(wr);
        tvSkip.setText(String.valueOf(skip));

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tvP.setText(pStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
