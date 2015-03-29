package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


/**
 * A {@link ShareIntentBuilder} wrapper that supports adding a text or an arbitrary number of streams. Once a text was
 * added a new builder wrapper will be returned.
 */
public class TextAndStreamBuilder extends OptionalExtraBuilder<TextAndStreamBuilder>
        implements AcceptsSingleExtraText<StreamBuilder>, AcceptsExtraStream<TextAndStreamBuilder>, Buildable {

    TextAndStreamBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream) {
        builder.stream(stream);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        builder.stream(stream, type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public StreamBuilder text(@NonNull String text) {
        builder.text(text);
        return new StreamBuilder(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Intent build() {
        return builder.build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void share() {
        builder.share();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void share(@NonNull CharSequence title) {
        builder.share(title);
    }

    @Override
    protected TextAndStreamBuilder getSelf() {
        return this;
    }
}
