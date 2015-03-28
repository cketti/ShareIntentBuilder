package de.cketti.shareintentbuilder;


import android.net.Uri;
import android.support.annotation.NonNull;


public class TextAndStreamNoBuilder extends OptionalExtraBuilder<TextAndStreamNoBuilder>
        implements AcceptsSingleExtraText<StreamBuilder>, AcceptsExtraStream<TextAndStreamBuilder> {

    TextAndStreamNoBuilder(ShareIntentBuilder builder) {
        super(builder);
        builder.ignoreSpecification();
    }

    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream) {
        return new TextAndStreamBuilder(builder).stream(stream);
    }

    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new TextAndStreamBuilder(builder).stream(stream, type);
    }

    @NonNull
    @Override
    public StreamBuilder text(@NonNull String text) {
        builder.text(text);
        return new StreamBuilder(builder);
    }

    @Override
    protected TextAndStreamNoBuilder getSelf() {
        return this;
    }
}
