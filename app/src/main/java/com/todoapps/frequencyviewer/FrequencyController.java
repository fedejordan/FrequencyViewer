package com.todoapps.frequencyviewer;

import android.util.Log;

import org.sidor.androidapps.simpletuner.ConfigFlags;
import org.sidor.androidapps.simpletuner.FrequencySmoothener;
import org.sidor.androidapps.simpletuner.SoundAnalyzer;
import org.sidor.androidapps.simpletuner.Tuning;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by federicojordan on 12/6/15.
 */
public class FrequencyController implements Observer {

    public void setFrequencyListener() {
        this.frequencyListener = frequencyListener;
    }

    public void setFrequencyListener(FrequencyListener frequencyListener) {
        this.frequencyListener = frequencyListener;
    }

    FrequencyListener frequencyListener;

    private double frequency;

    public enum MessageClass {
        TUNING_IN_PROGRESS,
        WEIRD_FREQUENCY,
        TOO_QUIET,
        TOO_NOISY,
    }

    MessageClass proposedMessage;

    @Override
    public void update(Observable who, Object obj) {
        if(who instanceof SoundAnalyzer) {
            if(obj instanceof SoundAnalyzer.AnalyzedSound) {
                SoundAnalyzer.AnalyzedSound result = (SoundAnalyzer.AnalyzedSound)obj;
                // result.getDebug();
                frequency = FrequencySmoothener.getSmoothFrequency(result);
                if(result.error== SoundAnalyzer.AnalyzedSound.ReadingType.BIG_FREQUENCY ||
                        result.error== SoundAnalyzer.AnalyzedSound.ReadingType.BIG_VARIANCE ||
                        result.error== SoundAnalyzer.AnalyzedSound.ReadingType.ZERO_SAMPLES)
                    proposedMessage = MessageClass.TOO_NOISY;
                else if(result.error== SoundAnalyzer.AnalyzedSound.ReadingType.TOO_QUIET)
                    proposedMessage = MessageClass.TOO_QUIET;
                else if(result.error== SoundAnalyzer.AnalyzedSound.ReadingType.NO_PROBLEMS)
                    proposedMessage = MessageClass.TUNING_IN_PROGRESS;
                else {
                    proposedMessage=null;
                }
                //Log.e(TAG,"Frequency: " + frequency);

                ((FrequencyListener)frequencyListener).updateFrequency(frequency);
//                frequencyListener.updateInfo(proposedMessage);
            }
        }
    }
}
