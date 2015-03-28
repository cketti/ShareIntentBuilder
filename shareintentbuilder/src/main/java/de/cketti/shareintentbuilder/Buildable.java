package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.support.annotation.NonNull;


public interface Buildable {
    @NonNull Intent build();
}
