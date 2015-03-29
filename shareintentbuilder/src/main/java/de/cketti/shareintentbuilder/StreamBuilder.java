package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


/**
 * A {@link ShareIntentBuilder} wrapper that besides optional extras only allows adding streams.
 */
public class StreamBuilder extends OptionalExtraBuilder<StreamBuilder> implements AcceptsExtraStream<StreamBuilder>,
        Buildable {

    StreamBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public StreamBuilder stream(@NonNull Uri stream) {
        builder.stream(stream);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        builder.stream(stream, type);
        return this;
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
    protected StreamBuilder getSelf() {
        return this;
    }
}
