package de.cketti.shareintentbuilder;


import java.util.Collection;


public interface AcceptsExtraText {
    TextBuilder text(String text);
    TextBuilder text(Collection<String> texts);
}
