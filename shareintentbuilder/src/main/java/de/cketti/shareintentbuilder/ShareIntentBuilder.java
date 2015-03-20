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

    protected void checkNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument may not be null");
        }
    }
}
