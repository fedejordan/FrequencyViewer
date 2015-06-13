# FrequencyViewer
Simple Android listener and frequency plotter


This project uses Simple-Guitar-Tuner to get frequencies value. 
You can see the source <a href="https://github.com/nivwusquorum/Simple-Guitar-Tuner">here</a>

# Usage

1. Add a element FrequencyView in your layout:

```xml
<com.todoapps.frequencyviewer.FrequencyView
  android:id="@+id/frequency_view"
  android:layout_width="match_parent"
  android:layout_height="300dp"/>
```

* In Activity:
  
```java
  FrequencyView frequencyView = (FrequencyView)findViewById(R.id.frequency_view);
```

2. Declare a FrequencyViewerHelper in your activity and init:

```java
  FrequencyViewerHelper frequencyViewerHelper;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      
      frequencyViewerHelper = new FrequencyViewerHelper(this, frequencyView);
      frequencyViewerHelper.showElements(true, true, true);
      frequencyViewerHelper.init();
  }
```
```java
  showElements(boolean lines, boolean freqNumber, boolean freqNumbers)
``` 
This method is setted to see frequencies values, lines and last frequency number listened
    
  3. Override these methods in your activity:
    
```java
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
```
