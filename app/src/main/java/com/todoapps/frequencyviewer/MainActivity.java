package com.todoapps.frequencyviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

    FrequencyViewerHelper frequencyViewerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrequencyView frequencyView = (FrequencyView)findViewById(R.id.frequency_view);
        this.frequencyViewerHelper = new FrequencyViewerHelper(this, frequencyView);
        frequencyViewerHelper.showElements(true, true, true);
        frequencyViewerHelper.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        frequencyViewerHelper.resume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        frequencyViewerHelper.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        frequencyViewerHelper.stop();
    }

}


