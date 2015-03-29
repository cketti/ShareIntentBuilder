/*
 * Copyright 2015 cketti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cketti.shareintentbuilder;


import android.net.Uri;
import android.support.annotation.NonNull;


interface AcceptsExtraStream<T extends AcceptsExtraStream<T>> {
    /**
     * Add a stream URI to be sent as part of the share.
     *
     * <p>The MIME type of the content will be fetched from the content provider pointed to by the stream URI.</p>
     *
     * @param stream
     *         a {@code content:} URI pointing to the content to share
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_STREAM
     */
    @NonNull T stream(@NonNull Uri stream);

    /**
     * Add a stream URI to be sent as part of the share.
     *
     * @param stream
     *         a {@code content:} URI pointing to the content to share
     * @param type
     *         the MIME type of the content
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_STREAM
     */
    @NonNull T stream(@NonNull Uri stream, @NonNull String type);
}
