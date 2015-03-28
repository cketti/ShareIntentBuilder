package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


public class StreamBuilder extends OptionalExtraBuilder<StreamBuilder> implements AcceptsExtraStream<StreamBuilder>,
        Buildable {

    StreamBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    @NonNull
    @Override
    public StreamBuilder stream(@NonNull Uri stream) {
        builder.stream(stream);
        return this;
    }

    @NonNull
    @Override
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        builder.stream(stream, type);
        return this;
    }

    @NonNull
    @Override
    public Intent build() {
        return builder.build();
    }

    @Override
    protected StreamBuilder getSelf() {
        return this;
    }
}
