package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.net.Uri;
import android.support.annotation.NonNull;


/**
 * The first {@link ShareIntentBuilder} wrapper that does not allow creating or launching a share intent. It supports
 * adding optional extras and will return specific wrappers when a text or stream was added.
 */
public class ShareIntentNoBuilder extends OptionalExtraBuilder<ShareIntentNoBuilder>
        implements AcceptsExtraText<TextBuilder>, AcceptsExtraStream<StreamBuilder> {

    ShareIntentNoBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    /**
     * Ignore the specification and allow using both streams and a single text extra.
     *
     * @return A suitable builder wrapper for chaining.
     */
    public TextAndStreamNoBuilder ignoreSpecification() {
        return new TextAndStreamNoBuilder(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public TextBuilder text(@NonNull String text) {
        return new TextBuilder(builder).text(text);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public TextBuilder text(@NonNull Collection<String> texts) {
        return new TextBuilder(builder).text(texts);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public StreamBuilder stream(@NonNull Uri stream) {
        return new StreamBuilder(builder).stream(stream);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new StreamBuilder(builder).stream(stream, type);
    }

    @Override
    protected ShareIntentNoBuilder getSelf() {
        return this;
    }
}
