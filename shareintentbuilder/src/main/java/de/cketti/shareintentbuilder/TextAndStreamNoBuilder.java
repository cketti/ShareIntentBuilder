package de.cketti.shareintentbuilder;


import android.net.Uri;
import android.support.annotation.NonNull;


/**
 * A {@link ShareIntentBuilder} wrapper that does not allow creating or launching a share intent. It supports adding
 * a text or a stream and will return new wrappers in those cases.
 */
public class TextAndStreamNoBuilder extends OptionalExtraBuilder<TextAndStreamNoBuilder>
        implements AcceptsSingleExtraText<StreamBuilder>, AcceptsExtraStream<TextAndStreamBuilder> {

    TextAndStreamNoBuilder(ShareIntentBuilder builder) {
        super(builder);
        builder.ignoreSpecification();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream) {
        return new TextAndStreamBuilder(builder).stream(stream);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new TextAndStreamBuilder(builder).stream(stream, type);
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

    @Override
    protected TextAndStreamNoBuilder getSelf() {
        return this;
    }
}
