package com.example.administrator.zjlc.userExercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class UserExercise extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private ImageView exercise_image;
    private TextView exercise_title;
    private TextView exercise_contnet;
    private LinearLayout exercise_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_exercise);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("活动中心");

        Glide.with(UserExercise.this).load("http://zhuojin.petope.com/Static/Uploads/Events/20160923173543663.gif").into(exercise_image);

        exercise_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserExercise.this,ExerciseDetail.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        exercise_image = (ImageView) findViewById(R.id.exercise_image);
        exercise_title = (TextView) findViewById(R.id.exercise_title);
        exercise_contnet = (TextView) findViewById(R.id.exercise_contnet);
        exercise_linearLayout = (LinearLayout) findViewById(R.id.exercise_linearLayout);
    }
}
