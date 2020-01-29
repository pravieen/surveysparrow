package com.surveysparrow.ss_android_sdk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.surveysparrow.ss_android_sdk.models.SsSurvey;

public class MainActivity extends AppCompatActivity {
    private final int SURVEY_REQUEST_CODE = 55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSurvey(View v) {
        SsSurvey survey = new SsSurvey("some-company.surveysparrow.com", "tt-b6a21f");
        SurveySparrow ss = new SurveySparrow(survey);
        ss.startSurveyForResult(this, SURVEY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SURVEY_REQUEST_CODE) {
            Log.v("EEE", data.getData().toString());
        }
    }
}
