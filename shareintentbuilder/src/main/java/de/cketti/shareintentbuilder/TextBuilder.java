package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.Collection;

import android.content.Intent;
import android.support.annotation.NonNull;

public class TextBuilder extends OptionalExtraBuilder<TextBuilder> implements AcceptsExtraText<TextBuilder> {

    TextBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    @NonNull
    public TextBuilder text(@NonNull String text) {
        builder.text(text);
        return this;
    }

    @NonNull
    public TextBuilder text(@NonNull Collection<String> texts) {
        builder.text(texts);
        return this;
    }

    @NonNull
    public Intent build() {
        Intent intent = new Intent();
        intent.setType("text/plain");

        if (builder.texts.size() > 1) {
            setMultipleText(intent);
        } else {
            setSingleText(intent);
        }

        builder.addExtrasAndFlagsToIntent(intent);
        return intent;
    }

    private void setSingleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, builder.texts.get(0));
    }

    private void setMultipleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putStringArrayListExtra(Intent.EXTRA_TEXT, new ArrayList<>(builder.texts));
    }

    @Override
    protected TextBuilder getSelf() {
        return this;
    }
}
