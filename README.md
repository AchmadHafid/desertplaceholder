DesertPlaceholder
=================

[![Release](https://jitpack.io/v/AchmadHafid/desertplaceholder.svg)](https://jitpack.io/#AchmadHafid/desertplaceholder)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

Animated placeholder in desert style

![image](https://github.com/JetradarMobile/DesertPlaceholder/blob/master/art/desertplaceholder.gif)


Compatibility
-------------

This library is compatible from API 21 (Android 5.0 Lollipop) & AndroidX.


Download
--------

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency

```groovy
dependencies {
    implementation 'com.github.AchmadHafid:desertplaceholder:2.1.0'
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
desertPlaceHolderAction(R.id.placeholder) {
    // do something
}
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
