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


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


/**
 * A {@link ShareIntentBuilder} wrapper that supports adding a text or an arbitrary number of streams. Once a text was
 * added a new builder wrapper will be returned.
 */
public class TextAndStreamBuilder extends OptionalExtraBuilder<TextAndStreamBuilder>
        implements AcceptsSingleExtraText<StreamBuilder>, AcceptsExtraStream<TextAndStreamBuilder>, Buildable {

    TextAndStreamBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream) {
        builder.stream(stream);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        builder.stream(stream, type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public StreamBuilder text(@NonNull String text) {
        builder.text(text);
        return new StreamBuilder(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Intent build() {
        return builder.build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void share() {
        builder.share();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void share(@NonNull CharSequence title) {
        builder.share(title);
    }

    @Override
    protected TextAndStreamBuilder getSelf() {
        return this;
    }
}
