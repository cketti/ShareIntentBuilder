package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.support.annotation.NonNull;


interface AcceptsExtraText<T extends AcceptsExtraText<T>> extends AcceptsSingleExtraText<T> {
    /**
     * Add text data to be sent as part of the share.
     *
     * @param texts
     *         texts to add to the share intent
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_TEXT
     */
    @NonNull T text(@NonNull Collection<String> texts);
}
