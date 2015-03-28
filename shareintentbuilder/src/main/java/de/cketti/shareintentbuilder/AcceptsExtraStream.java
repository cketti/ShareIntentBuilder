package de.cketti.shareintentbuilder;


import android.net.Uri;
import android.support.annotation.NonNull;


interface AcceptsExtraStream<T extends AcceptsExtraStream<T>> {
    @NonNull T stream(@NonNull Uri stream);
    @NonNull T stream(@NonNull Uri stream, @NonNull String type);
}
