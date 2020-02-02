package com.surveysparrow.ss_android_sdk.views;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.surveysparrow.ss_android_sdk.SurveySparrow;
import com.surveysparrow.ss_android_sdk.helpers.OnSsResponseEventListener;
import com.surveysparrow.ss_android_sdk.models.SsSurvey;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("SetJavaScriptEnabled")
public final class SsSurveyFragment extends Fragment {
    private SsSurvey survey;

    private OnSsResponseEventListener onSsResponseEventListener;

    public SsSurveyFragment(SsSurvey survey) {
        this.survey = survey;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onSsResponseEventListener = (OnSsResponseEventListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WebView ssWebView = new WebView(getActivity());
        ssWebView.getSettings().setJavaScriptEnabled(true);
        ssWebView.getSettings().setDomStorageEnabled(true);
        ssWebView.addJavascriptInterface(new JsObject(getActivity()), "SsAndroidSdk");
        ssWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains(SurveySparrow.SS_THANKYOU_BASE_URL)) {
                    return false;
                }
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }
        });
        ssWebView.loadUrl(this.survey.getSsUrl());
//        ssWebView.loadUrl("https://react-7hjmqi.stackblitz.io");
        return ssWebView;
    }


    private class JsObject {
        private Context context;

        JsObject(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void shareData(String data) {
            onSsResponseEventListener.responseEvent(data);
        }
    }
}
