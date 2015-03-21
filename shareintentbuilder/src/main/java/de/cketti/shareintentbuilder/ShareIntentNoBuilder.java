package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;


public final class ShareIntentNoBuilder implements AcceptsExtraText, AcceptsExtraStream {
    private final Activity activity;

    ShareIntentNoBuilder(Activity activity) {
        this.activity = activity;
    }

    @Override
    public TextBuilder text(@NonNull String text) {
        return new TextBuilder(activity).text(text);
    }

    @Override
    public TextBuilder text(@NonNull Collection<String> texts) {
        return new TextBuilder(activity).text(texts);
    }

    @Override
    public StreamBuilder stream(@NonNull Uri stream) {
        return new StreamBuilder(activity).stream(stream);
    }

    @Override
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new StreamBuilder(activity).stream(stream, type);
    }
}
