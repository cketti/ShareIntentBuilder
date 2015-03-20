package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.net.Uri;
import android.support.annotation.NonNull;


public final class ShareIntentNoBuilder implements AcceptsExtraText, AcceptsExtraStream {

    ShareIntentNoBuilder() {}

    @Override
    public TextBuilder text(@NonNull String text) {
        return new TextBuilder().text(text);
    }

    @Override
    public TextBuilder text(@NonNull Collection<String> texts) {
        return new TextBuilder().text(texts);
    }

    @Override
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new StreamBuilder().stream(stream, type);
    }
}
