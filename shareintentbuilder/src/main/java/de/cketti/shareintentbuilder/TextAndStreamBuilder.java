package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


public class TextAndStreamBuilder extends OptionalExtraBuilder<TextAndStreamBuilder>
        implements AcceptsSingleExtraText<StreamBuilder>, AcceptsExtraStream<TextAndStreamBuilder>, Buildable {

    TextAndStreamBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream) {
        builder.stream(stream);
        return this;
    }

    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        builder.stream(stream, type);
        return this;
    }

    @NonNull
    @Override
    public StreamBuilder text(@NonNull String text) {
        builder.text(text);
        return new StreamBuilder(builder);
    }

    @NonNull
    @Override
    public Intent build() {
        return builder.build();
    }

    @Override
    public void share() {
        builder.share();
    }

    @Override
    public void share(@NonNull CharSequence title) {
        builder.share(title);
    }

    @Override
    protected TextAndStreamBuilder getSelf() {
        return this;
    }
}
