package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Intent;
import android.support.annotation.NonNull;

public class TextBuilder extends ShareIntentBuilder<TextBuilder> implements AcceptsExtraText {
    private final List<String> texts = new ArrayList<>();

    TextBuilder() {}

    @Override
    public TextBuilder text(@NonNull String text) {
        checkNotNull(text);

        texts.add(text);
        return this;
    }

    @Override
    public TextBuilder text(@NonNull Collection<String> texts) {
        checkNotNull(texts);

        this.texts.addAll(texts);
        return this;
    }

    @Override
    protected Intent buildTypeSpecificIntent() {
        Intent intent = new Intent();
        intent.setType("text/plain");

        if (texts.size() > 1) {
            setMultipleText(intent);
        } else {
            setSingleText(intent);
        }

        return intent;
    }

    private void setSingleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, texts.get(0));
    }

    private void setMultipleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putStringArrayListExtra(Intent.EXTRA_TEXT, new ArrayList<>(texts));
    }

    @Override
    protected TextBuilder getSelf() {
        return this;
    }
}
