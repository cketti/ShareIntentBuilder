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


import java.util.Collection;

import android.content.Intent;
import android.support.annotation.NonNull;


/**
 * A {@link ShareIntentBuilder} wrapper that besides optional extras only allows adding text data.
 */
public class TextBuilder extends OptionalExtraBuilder<TextBuilder> implements AcceptsExtraText<TextBuilder>, Buildable {

    TextBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextBuilder text(@NonNull String text) {
        builder.text(text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextBuilder text(@NonNull Collection<String> texts) {
        builder.text(texts);
        return this;
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
    protected TextBuilder getSelf() {
        return this;
    }
}
