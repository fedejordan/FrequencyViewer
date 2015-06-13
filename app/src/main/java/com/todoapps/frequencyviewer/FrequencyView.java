package com.todoapps.frequencyviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by federicojordan on 12/6/15.
 */
public class FrequencyView extends View {
    private double []freqValues;
    static final int MAX_FREQ_VALUES = 20;
    private Paint freqPaint;
    private Paint backgroundPaint;
    private Paint freqNumberPaint;
    private float deltaX;
    private Bitmap backgroundBitmap;
    private boolean showLines;
    private boolean showFrequencyNumber;
    private boolean showFrequencies;

    public FrequencyView(Context context) {
        super(context);
        init();
    }

    public FrequencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrequencyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setShowFrequencies(boolean showFrequencies) {
        this.showFrequencies = showFrequencies;
    }

    public void setShowFrequencyNumber(boolean showFrequencyNumber) {
        this.showFrequencyNumber = showFrequencyNumber;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    private void init(){
        showFrequencies = true;
        showFrequencyNumber = true;
        showLines = true;

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.BLACK);

        freqPaint = new Paint();
        freqPaint.setStyle(Paint.Style.STROKE);
        freqPaint.setColor(Color.GREEN);
        freqPaint.setStrokeWidth(8.0f);

        freqNumberPaint = new Paint();
        freqNumberPaint.setStyle(Paint.Style.STROKE);
        freqNumberPaint.setColor(Color.RED);
        freqNumberPaint.setTextSize(50.0f);

        freqValues = new double[MAX_FREQ_VALUES];
        for(int i=0;i<MAX_FREQ_VALUES;i++){
            freqValues[i] = 0.0;
        }
//        deltaX = ((float)getWidth())/MAX_FREQ_VALUES;
//        Log.e("", "deltaX: " + deltaX);
    }

    public void addFreqValue(double value){
        if(value!=Double.NaN) {
            freqValues[MAX_FREQ_VALUES-1] = value;
            for(int i=0;i<MAX_FREQ_VALUES-1;i++){
                freqValues[i] = freqValues[i+1];
            }
            invalidate();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(backgroundBitmap==null){
            createBackgroundBitmap();
        }

        if(deltaX == 0.0f){
            deltaX = ((float)getWidth())/MAX_FREQ_VALUES;
        }

        canvas.drawBitmap(backgroundBitmap, 0, 0, null);

        for(int i=0;i<MAX_FREQ_VALUES-1;i++){
            if(freqValues[i]!=Double.NaN||freqValues[i+1]!=Double.NaN) {
                canvas.drawLine(deltaX * i, getHeight() - (float) freqValues[i], deltaX * (i + 1), getHeight() - (float) freqValues[i + 1], freqPaint);
            }
        }

        if(showFrequencyNumber&&!Double.isNaN(freqValues[MAX_FREQ_VALUES-1])){
            canvas.drawText(freqValues[MAX_FREQ_VALUES-1]+"", getWidth()-150.0f, 80.0f, freqNumberPaint);
        }

    }

    private void createBackgroundBitmap() {
        backgroundBitmap = Bitmap.createBitmap((int) getWidth(), (int) getHeight(), Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(backgroundBitmap);
        bitmapCanvas.drawPaint(backgroundPaint);
        Paint linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5.0f);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40.0f);
        Log.w("", "height: " + getHeight());
        for(int i=0;i<getHeight(); i += 100) {
            if(showLines)
                bitmapCanvas.drawLine(0, i, getWidth(), i, linePaint);
            if(showFrequencies)
                bitmapCanvas.drawText(""+(getHeight()-i), 0, i, textPaint);
        }
    }
}
