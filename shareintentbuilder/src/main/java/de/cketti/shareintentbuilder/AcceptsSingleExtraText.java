package de.cketti.shareintentbuilder;


import android.support.annotation.NonNull;


interface AcceptsSingleExtraText<T> {
    @NonNull T text(@NonNull String text);
}
