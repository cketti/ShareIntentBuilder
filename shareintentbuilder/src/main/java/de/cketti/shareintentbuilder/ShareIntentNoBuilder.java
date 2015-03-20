package de.cketti.shareintentbuilder;


import java.util.Collection;


public final class ShareIntentNoBuilder implements AcceptsExtraText {

    ShareIntentNoBuilder() {}

    @Override
    public TextBuilder text(String text) {
        return new TextBuilder().text(text);
    }

    @Override
    public TextBuilder text(Collection<String> texts) {
        return new TextBuilder().text(texts);
    }
}
