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


/**
 * A {@link ShareIntentBuilder} wrapper that does not allow creating or launching a share intent. It supports adding
 * a text or a stream and will return new wrappers in those cases.
 */
public class TextAndStreamNoBuilder extends OptionalExtraBuilder<TextAndStreamNoBuilder>
        implements AcceptsSingleExtraText<StreamBuilder>, AcceptsExtraStream<TextAndStreamBuilder> {

    TextAndStreamNoBuilder(ShareIntentBuilder builder) {
        super(builder);
        builder.ignoreSpecification();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream) {
        return new TextAndStreamBuilder(builder).stream(stream);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public TextAndStreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        return new TextAndStreamBuilder(builder).stream(stream, type);
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

    @Override
    protected TextAndStreamNoBuilder getSelf() {
        return this;
    }
}
