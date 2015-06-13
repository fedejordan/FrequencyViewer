package com.todoapps.frequencyviewer;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.sidor.androidapps.simpletuner.SoundAnalyzer;

/**
 * Created by federicojordan on 13/6/15.
 */
public class FrequencyViewerHelper implements FrequencyListener{

    private SoundAnalyzer soundAnalyzer = null ;
    private FrequencyController frequencyController = null;
    public static final String TAG = "FrequencyViewerHelper";
    private FrequencyView frequencyView;
    private Context context;

    public FrequencyViewerHelper (Context ctx, FrequencyView fv){
        this.frequencyView = fv;
        this.context = ctx;
    }

    public void init(){
        frequencyController = new FrequencyController();
        frequencyController.setFrequencyListener(this);
        try {
            soundAnalyzer = new SoundAnalyzer();
        } catch(Exception e) {
            Toast.makeText(context, "The are problems with your microphone :(", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Exception when instantiating SoundAnalyzer: " + e.getMessage());
        }
        soundAnalyzer.addObserver(frequencyController);
    }

    public void showElements(boolean lines, boolean freqNumber, boolean freqNumbers){
        frequencyView.setShowLines(lines);
        frequencyView.setShowFrequencies(freqNumbers);
        frequencyView.setShowFrequencyNumber(freqNumber);
    }

    public void resume(){
        if(soundAnalyzer!=null)
            soundAnalyzer.ensureStarted();
    }

    public void start(){
        if(soundAnalyzer!=null)
            soundAnalyzer.start();
    }

    public void stop(){
        if(soundAnalyzer!=null)
            soundAnalyzer.stop();
    }

    @Override
    public void updateFrequency(double frequency) {

        frequencyView.addFreqValue(frequency);
    }
}
