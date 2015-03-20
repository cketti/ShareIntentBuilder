package de.cketti.shareintentbuilder;


import android.content.Intent;


public abstract class ShareIntentBuilder {

    ShareIntentBuilder() {}

    public static ShareIntentNoBuilder newInstance() {
        return new ShareIntentNoBuilder();
    }

    public final Intent build() {
        Intent intent = buildTypeSpecificIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        return intent;
    }

    protected abstract Intent buildTypeSpecificIntent();
}
