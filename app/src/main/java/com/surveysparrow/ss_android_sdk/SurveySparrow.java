package com.surveysparrow.ss_android_sdk;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.surveysparrow.ss_android_sdk.helpers.SsHelper;
import com.surveysparrow.ss_android_sdk.models.SsSurvey;
import com.surveysparrow.ss_android_sdk.views.SsSurveyActivity;

public class SurveySparrow {
    private SsSurvey survey;
    public static final String SURVEY_URL = "SURVEY_URL";

    SurveySparrow(SsSurvey survey) {
        this.survey = survey;
    }

    public void startSurvey(Context context) {
        if(!SsHelper.getNetworkState(context)) {
            Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context, SsSurveyActivity.class);
        intent.putExtra(SURVEY_URL, this.survey.getSsUrl());
        context.startActivity(intent);
    }
}