package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.support.annotation.NonNull;


public interface Buildable {
    /**
     * Create a share intent from the data in this builder.
     *
     * @return the share intent
     */
    @NonNull Intent build();

    /**
     * Start chooser activity for the share intent.
     */
    void share();

    /**
     * Start chooser activity with the specified title for the share intent.
     *
     * @param title
     *         the title to use for the chooser activity
     */
    void share(@NonNull CharSequence title);
}
