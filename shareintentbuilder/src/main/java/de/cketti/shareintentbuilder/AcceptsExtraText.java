package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.support.annotation.NonNull;


interface AcceptsExtraText {
    TextBuilder text(@NonNull String text);
    TextBuilder text(@NonNull Collection<String> texts);
}
