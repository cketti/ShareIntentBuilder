package de.cketti.shareintentbuilder;


import android.content.Intent;


public class ShareIntentBuilder {
    private static final String TYPE_TEXT_PLAIN = "text/plain";

    private String text;

    public static ShareIntentBuilder newInstance() {
        return new ShareIntentBuilder();
    }

    private ShareIntentBuilder() {
    }

    public ShareIntentBuilder text(String text) {
        this.text = text;
        return this;
    }

    public Intent build() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(TYPE_TEXT_PLAIN);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        return intent;
    }
}
