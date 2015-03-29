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

import android.support.annotation.NonNull;


/**
 * Base class for all {@link ShareIntentBuilder} wrappers. Supports adding optional extras.
 *
 * @param <T> the type of the concrete wrapper class
 */
public abstract class OptionalExtraBuilder<T extends OptionalExtraBuilder<T>> {
    protected final ShareIntentBuilder builder;

    public OptionalExtraBuilder(ShareIntentBuilder builder) {
        this.builder = builder;
    }

    /**
     * Set the subject line for this share intent.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param subject
     *         the subject line for the share intent
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_SUBJECT
     */
    @NonNull
    public T subject(@NonNull String subject) {
        builder.subject(subject);
        return getSelf();
    }

    /**
     * Add an email address to be used in the "to" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddress
     *         the email address to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_EMAIL
     */
    @NonNull
    public T email(@NonNull String emailAddress) {
        builder.to(emailAddress);
        return getSelf();
    }

    /**
     * Add a collection of email addresses to be used in the "to" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddresses
     *         the email addresses to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_EMAIL
     */
    @NonNull
    public T email(@NonNull Collection<String> emailAddresses) {
        builder.to(emailAddresses);
        return getSelf();
    }

    /**
     * Add an email address to be used in the "to" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddress
     *         the email address to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_EMAIL
     */
    @NonNull
    public T to(@NonNull String emailAddress) {
        builder.to(emailAddress);
        return getSelf();
    }

    /**
     * Add a collection of email addresses to be used in the "to" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddresses
     *         the email addresses to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_EMAIL
     */
    @NonNull
    public T to(@NonNull Collection<String> emailAddresses) {
        builder.to(emailAddresses);
        return getSelf();
    }

    /**
     * Add an email address to be used in the "cc" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddress
     *         the email address to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_CC
     */
    @NonNull
    public T cc(@NonNull String emailAddress) {
        builder.cc(emailAddress);
        return getSelf();
    }

    /**
     * Add a collection of email addresses to be used in the "cc" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddresses
     *         the email addresses to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_CC
     */
    @NonNull
    public T cc(@NonNull Collection<String> emailAddresses) {
        builder.cc(emailAddresses);
        return getSelf();
    }

    /**
     * Add an email address to be used in the "bcc" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddress
     *         the email address to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_BCC
     */
    @NonNull
    public T bcc(@NonNull String emailAddress) {
        builder.bcc(emailAddress);
        return getSelf();
    }

    /**
     * Add a collection of email addresses to be used in the "bcc" field.
     *
     * <p>Receiving apps other than email clients will most likely ignore this information.</p>
     *
     * @param emailAddresses
     *         the email addresses to add
     *
     * @return a suitable builder wrapper for chaining
     *
     * @see android.content.Intent#EXTRA_BCC
     */
    @NonNull
    public T bcc(@NonNull Collection<String> emailAddresses) {
        builder.bcc(emailAddresses);
        return getSelf();
    }

    protected abstract T getSelf();
}
