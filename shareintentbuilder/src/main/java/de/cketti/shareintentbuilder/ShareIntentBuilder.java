package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;


public abstract class ShareIntentBuilder<T extends ShareIntentBuilder<T>> {
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";

    protected final Activity activity;
    private String subject;
    private final List<String> recipientsTo = new ArrayList<>();
    private final List<String> recipientsCc = new ArrayList<>();
    private final List<String> recipientsBcc = new ArrayList<>();

    ShareIntentBuilder(Activity activity) {
        this.activity = activity;
    }

    public static ShareIntentNoBuilder from(@NonNull Activity activity) {
        checkNotNull(activity);

        return new ShareIntentNoBuilder(activity);
    }

    public T subject(@NonNull String subject) {
        checkNotNull(subject);

        this.subject = subject;
        return getSelf();
    }

    public T email(@NonNull String email) {
        return to(email);
    }

    public T email(@NonNull List<String> emails) {
        return to(emails);
    }

    public T to(@NonNull String email) {
        checkNotNull(email);

        recipientsTo.add(email);
        return getSelf();
    }

    public T to(@NonNull List<String> emails) {
        checkNotNull(emails);
        for (String email : emails) {
            checkNotNull(email);
        }

        recipientsTo.addAll(emails);
        return getSelf();
    }

    public T cc(@NonNull String email) {
        checkNotNull(email);

        recipientsCc.add(email);
        return getSelf();
    }

    public T cc(@NonNull List<String> emails) {
        checkNotNull(emails);
        for (String email : emails) {
            checkNotNull(email);
        }

        recipientsCc.addAll(emails);
        return getSelf();
    }

    public T bcc(@NonNull String email) {
        checkNotNull(email);

        recipientsBcc.add(email);
        return getSelf();
    }

    public T bcc(@NonNull List<String> emails) {
        checkNotNull(emails);
        for (String email : emails) {
            checkNotNull(email);
        }

        recipientsBcc.addAll(emails);
        return getSelf();
    }

    public final Intent build() {
        Intent intent = buildTypeSpecificIntent();
        addCallingPackageAndActivity(intent);

        addSubject(intent);
        addEmailRecipients(intent, Intent.EXTRA_EMAIL, recipientsTo);
        addEmailRecipients(intent, Intent.EXTRA_CC, recipientsCc);
        addEmailRecipients(intent, Intent.EXTRA_BCC, recipientsBcc);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        return intent;
    }

    private void addCallingPackageAndActivity(Intent intent) {
        intent.putExtra(EXTRA_CALLING_PACKAGE, activity.getPackageName());
        intent.putExtra(EXTRA_CALLING_ACTIVITY, activity.getComponentName());
    }

    private void addSubject(Intent intent) {
        if (subject != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
    }

    private void addEmailRecipients(Intent intent, String extraKey, List<String> to) {
        if (to.isEmpty()) {
            return;
        }

        intent.putExtra(extraKey, to.toArray(new String[to.size()]));
    }

    protected abstract Intent buildTypeSpecificIntent();

    protected static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument may not be null");
        }
    }

    protected abstract T getSelf();
}
