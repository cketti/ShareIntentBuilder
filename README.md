# Share Intent Builder

[![Build status](https://api.travis-ci.org/cketti/ShareIntentBuilder.svg)](https://travis-ci.org/cketti/ShareIntentBuilder)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.cketti.share/share-intent-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.cketti.share/share-intent-builder)

An Android Library for the type-safe creation of Share Intents.

## Usage

Creating a simple share intent is as easy as this:

```java
Intent shareIntent = ShareIntentBuilder.from(activity)
        .text("Text to share")
        .build();
```

`ShareIntentBuilder` also supports adding optional extras and directly launching the share action:

```java
ShareIntentBuilder.from(activity)
        .text("Sharing is caring!")
        .subject("Very important message")
        .to("everyone@example.com")
        .cc("carebear@example.com")
        .share();
```

Of course sharing one or multiple data streams
(see [EXTRA_STREAM](https://developer.android.com/reference/android/content/Intent.html#EXTRA_STREAM))
is also supported.

```java
ShareIntentBuilder.from(activity)
        // stream(Uri) will fetch the type using the ContentResolver
        // supplied by the activity (calls getType() on the content provider)
        .stream(MyContentProvider.getUriForItem(42))
        // you can avoid that by explicitly specifying the type
        .stream(Uri.parse("content://com.example.provider/23"), "image/png")
        .share();
```

According to the documentation supplying both
[EXTRA_TEXT](https://developer.android.com/reference/android/content/Intent.html#EXTRA_TEXT) and
[EXTRA_STREAM](https://developer.android.com/reference/android/content/Intent.html#EXTRA_STREAM) is not allowed.
However, some receiving apps support this. So if you really have to, you can use `ShareIntentBuilder` to do this by
calling `ignoreSpecification()` first.

```java
ShareIntentBuilder.from(activity)
        .ignoreSpecification()
        .text("EXTRA_TEXT content that may or may not be used by the receiving app")
        .stream(Uri.parse("content://com.example.provider/42"), "application/octet-stream")
        .share();
```


## Include the library

The easiest way to add the library to your project is via Gradle. Add the following lines to your `build.gradle`:

```groovy
dependencies {
    compile 'de.cketti.share:share-intent-builder:0.0.2'
}
```

To tell Gradle where to find the library, make sure `build.gradle` also contains this:

```groovy
repositories {
    mavenCentral()
}
```

Note: `jcenter()` - the new default when creating projects with Android Studio - will work as well.

## License

    Copyright 2015 cketti

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
