package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.net.Uri;


public final class ShareIntentNoBuilder implements AcceptsExtraText, AcceptsExtraStream {

    ShareIntentNoBuilder() {}

    @Override
    public TextBuilder text(String text) {
        return new TextBuilder().text(text);
    }

    @Override
    public TextBuilder text(Collection<String> texts) {
        return new TextBuilder().text(texts);
    }

    @Override
    public StreamBuilder stream(Uri stream, String type) {
        return new StreamBuilder().stream(stream, type);
    }
}
