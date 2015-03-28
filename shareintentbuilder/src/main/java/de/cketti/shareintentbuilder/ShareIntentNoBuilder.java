package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.net.Uri;
import android.support.annotation.NonNull;


public class ShareIntentNoBuilder extends OptionalExtraBuilder<ShareIntentNoBuilder>
        implements AcceptsExtraText<TextBuilder>, AcceptsExtraStream<StreamBuilder> {

    ShareIntentNoBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    @NonNull
    public TextBuilder text(@NonNull String text) {
        return new TextBuilder(builder).text(text);
    }

    @NonNull
    public TextBuilder text(@NonNull Collection<String> texts) {
        return new TextBuilder(builder).text(texts);
    }

    @NonNull
    public StreamBuilder stream(@NonNull Uri stream) {
        return new StreamBuilder(builder).stream(stream);
    }

    @NonNull
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new StreamBuilder(builder).stream(stream, type);
    }

    @Override
    protected ShareIntentNoBuilder getSelf() {
        return this;
    }
}
