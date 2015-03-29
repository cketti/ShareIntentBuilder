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
