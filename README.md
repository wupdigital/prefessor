# Prefessor

[![Build Status](https://travis-ci.org/wupdigital/prefessor.svg?branch=master)](https://travis-ci.org/wupdigital/prefessor)
[![Sonar Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=digital.wup.prefessor%3Aprefessor-root&metric=alert_status)](https://sonarcloud.io/dashboard?id=digital.wup.prefessor:prefessor-root)

Prefessor is a multiplatform preference library. Supports Android, iOS, Js and MacOS platforms. Under the hood all implementation it use the platform default preference managers.
It uses [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences) on Android,
[UserDefaults](https://developer.apple.com/documentation/foundation/userdefaults) on iOS and MacOs and [LocalStorage](https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage) on Js.

Prefessor supports `Boolean`, `Float`, `Int`, `Long` and `String` preference types.


## Usage

Use following dependencies on your platform projects:

```
// On common projects
implementation 'digital.wup.prefessor:prefessor-common:0.1.0'
// On Android projects
implementation 'digital.wup.prefessor:prefessor-android:0.1.0'
// On iOS or MacOs projects
implementation 'digital.wup.prefessor:prefessor-apple:0.1.0'
// On Js projects
implementation 'digital.wup.prefessor:prefessor-js:0.1.0'
```

### Save values

```
val prefessor = Prefessor.create()

prefessor.edit {
    putBoolean("booleanKey", true)
    putFloat("floatKey", 0.1f)
    putInt("intKey", 1)
    putLong("longKey", 1L)
    putString("stringKey", "stringValue")
    
}
```

### Read values
```
val prefessor = Prefessor.create()

val booleanValue = prefessor.getBoolean("booleanKey")
val floatValue = prefessor.getFloat("floatKey")
val intValue = prefessor.getInt("intKey")
val longValue = prefessor.getLong("longKey")
val stringValue = prefessor.getString("stringKey", "defaultValue")
```

## Remove values

```
val prefessor = Prefessor.create()

prefessor.edit {
    // remove a value on key
    remove("booleanKey")
    // remove all value
    clear()
}
```

## Known issues
* It does not throw `ClassCastException` on Js platform, when we will try to get an incompatible type from `Prefessor`. For example: When we save value as `Boolean` and try to get as `String`.

## License

    Copyright 2018 W.UP, Ltd.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.