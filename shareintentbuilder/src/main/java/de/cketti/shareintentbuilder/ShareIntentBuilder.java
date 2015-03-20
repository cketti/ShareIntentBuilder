package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Intent;


public class ShareIntentBuilder {
    private static final String TYPE_TEXT_PLAIN = "text/plain";

    private List<String> texts = new ArrayList<>();

    public static ShareIntentBuilder newInstance() {
        return new ShareIntentBuilder();
    }

    private ShareIntentBuilder() {
    }

    public ShareIntentBuilder text(String text) {
        texts.add(text);
        return this;
    }

    public ShareIntentBuilder text(Collection<String> texts) {
        this.texts.addAll(texts);
        return this;
    }

    public Intent build() {
        if (texts.isEmpty()) {
            throw new IllegalStateException("You need to call text() at least once.");
        }

        Intent intent = new Intent();
        if (texts.size() > 1) {
            setMultipleText(intent);
        } else {
            setSingleText(intent);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        return intent;
    }

    private void setSingleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        intent.setType(TYPE_TEXT_PLAIN);
        intent.putExtra(Intent.EXTRA_TEXT, texts.get(0));
    }

    private void setMultipleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType(TYPE_TEXT_PLAIN);
        intent.putStringArrayListExtra(Intent.EXTRA_TEXT, new ArrayList<>(texts));
    }
}
