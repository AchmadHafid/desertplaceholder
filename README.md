DesertPlaceholder
=================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-DesertPlaceholder-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5065)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

Animated placeholder in desert style

![image](https://github.com/JetradarMobile/DesertPlaceholder/blob/master/art/desertplaceholder.gif)


Compatibility
-------------

This library is compatible from API 21 (Android 5.0 Lollipop) & AndroidX.


Download
--------

Add the dependency

```groovy
dependencies {
    compile 'com.github.jetradarmobile.desertplaceholder:desertplaceholder:1.2.4'
}
```


Usage
-----

Add view to your layout

``` xml
  <com.jetradar.desertplaceholder.DesertPlaceholder
      android:id="@+id/placeholder"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:dp_buttonText="retry"
      app:dp_message="Use this nice placeholder if you have nothing to show"/>
```

Set up listener to button click

``` kotlin
findViewById<DesertPlaceholder>(R.id.placeholder)
    .setOnButtonClickListener(View.OnClickListener {
        // do something
    })
```


Credentials
-----------

Designed by [Max Klimchuk](https://dribbble.com/maxklimchuk).


License
-------

    Copyright 2016 JetRadar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
