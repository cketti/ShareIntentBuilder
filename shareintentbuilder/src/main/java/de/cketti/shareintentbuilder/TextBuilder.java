package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.content.Intent;
import android.support.annotation.NonNull;


/**
 * A {@link ShareIntentBuilder} wrapper that besides optional extras only allows adding text data.
 */
public class TextBuilder extends OptionalExtraBuilder<TextBuilder> implements AcceptsExtraText<TextBuilder>, Buildable {

    TextBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextBuilder text(@NonNull String text) {
        builder.text(text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextBuilder text(@NonNull Collection<String> texts) {
        builder.text(texts);
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
    protected TextBuilder getSelf() {
        return this;
    }
}
