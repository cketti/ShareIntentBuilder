package de.cketti.shareintentbuilder;


import android.net.Uri;


public interface AcceptsExtraStream {
    StreamBuilder stream(Uri stream, String type);
}
