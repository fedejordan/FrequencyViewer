# FrequencyViewer
Simple Android listener and frequency plotter


This project uses Simple-Guitar-Tuner to get frequencies value. 
You can see the source <a href="https://github.com/nivwusquorum/Simple-Guitar-Tuner">here</a>

# Usage

* Add a element FrequencyView in your layout:

```xml
<com.todoapps.frequencyviewer.FrequencyView
  android:id="@+id/frequency_view"
  android:layout_width="match_parent"
  android:layout_height="300dp"/>
```

* Add In Activity:
  
```java
  FrequencyView frequencyView = (FrequencyView)findViewById(R.id.frequency_view);
```

* Declare a FrequencyViewerHelper in your activity and init with FrequencyView object:

```java
  FrequencyViewerHelper frequencyViewerHelper;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      
      
      FrequencyView frequencyView = (FrequencyView)findViewById(R.id.frequency_view);
      frequencyViewerHelper = new FrequencyViewerHelper(this, frequencyView);
      frequencyViewerHelper.showElements(true, true, true);
      frequencyViewerHelper.init();
  }
```

```java
  showElements(boolean lines, boolean freqNumber, boolean freqNumbers)
``` 
This method is setted to see frequencies values, lines and last frequency number listened
    
* Override these methods in your activity:
    
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

# License

```
Copyright 2013-2015 Federico Jordan

This file is part of FrequencyViewer.

FrequencyViewer is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

FrequencyViewer is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with FrequencyViewer.  If not, see <http://www.gnu.org/licenses/>.
```
