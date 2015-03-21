package de.cketti.shareintentbuilder;


import android.net.Uri;
import android.support.annotation.NonNull;


interface AcceptsExtraStream {
    StreamBuilder stream(@NonNull Uri stream);
    StreamBuilder stream(@NonNull Uri stream, @NonNull String type);
}
