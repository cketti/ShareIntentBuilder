package de.cketti.shareintentbuilder;


import java.util.Collection;

import android.support.annotation.NonNull;


public abstract class OptionalExtraBuilder<T extends OptionalExtraBuilder<T>> {
    protected final ShareIntentBuilder builder;

    public OptionalExtraBuilder(ShareIntentBuilder builder) {
        this.builder = builder;
    }

    @NonNull
    public T subject(@NonNull String subject) {
        builder.subject(subject);
        return getSelf();
    }

    @NonNull
    public T email(@NonNull String emailAddress) {
        builder.to(emailAddress);
        return getSelf();
    }

    @NonNull
    public T email(@NonNull Collection<String> emailAddresses) {
        builder.to(emailAddresses);
        return getSelf();
    }

    @NonNull
    public T to(@NonNull String emailAddress) {
        builder.to(emailAddress);
        return getSelf();
    }

    @NonNull
    public T to(@NonNull Collection<String> emailAddresses) {
        builder.to(emailAddresses);
        return getSelf();
    }

    @NonNull
    public T cc(@NonNull String emailAddress) {
        builder.cc(emailAddress);
        return getSelf();
    }

    @NonNull
    public T cc(@NonNull Collection<String> emailAddresses) {
        builder.cc(emailAddresses);
        return getSelf();
    }

    @NonNull
    public T bcc(@NonNull String emailAddress) {
        builder.bcc(emailAddress);
        return getSelf();
    }

    @NonNull
    public T bcc(@NonNull Collection<String> emailAddresses) {
        builder.bcc(emailAddresses);
        return getSelf();
    }

    protected abstract T getSelf();
}
