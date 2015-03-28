package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.support.annotation.NonNull;


interface AcceptsExtraText<T extends AcceptsExtraText<T>> {
    @NonNull T text(@NonNull String text);
    @NonNull T text(@NonNull Collection<String> texts);
}
