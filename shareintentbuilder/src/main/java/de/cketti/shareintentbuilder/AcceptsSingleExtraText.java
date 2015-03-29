package de.cketti.shareintentbuilder;


import android.support.annotation.NonNull;


interface AcceptsSingleExtraText<T> {
    /**
     * Add text data to be sent as part of the share.
     *
     * @param text
     *         text to add to the share intent
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_TEXT
     */
    @NonNull T text(@NonNull String text);
}
