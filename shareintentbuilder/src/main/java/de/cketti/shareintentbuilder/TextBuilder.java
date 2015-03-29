package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.content.Intent;
import android.support.annotation.NonNull;


public class TextBuilder extends OptionalExtraBuilder<TextBuilder> implements AcceptsExtraText<TextBuilder>, Buildable {

    TextBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    @NonNull
    @Override
    public TextBuilder text(@NonNull String text) {
        builder.text(text);
        return this;
    }

    @NonNull
    @Override
    public TextBuilder text(@NonNull Collection<String> texts) {
        builder.text(texts);
        return this;
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
    protected TextBuilder getSelf() {
        return this;
    }
}
