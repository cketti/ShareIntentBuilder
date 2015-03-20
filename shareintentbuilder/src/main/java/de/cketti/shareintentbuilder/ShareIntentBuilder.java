package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.annotation.NonNull;


public abstract class ShareIntentBuilder<T extends ShareIntentBuilder<T>> {

    private String subject;
    private final List<String> recipientsTo = new ArrayList<>();
    private final List<String> recipientsCc = new ArrayList<>();
    private final List<String> recipientsBcc = new ArrayList<>();

    ShareIntentBuilder() {}

    public static ShareIntentNoBuilder newInstance() {
        return new ShareIntentNoBuilder();
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
            to(email);
        }
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
            cc(email);
        }
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
            bcc(email);
        }
        return getSelf();
    }

    public final Intent build() {
        Intent intent = buildTypeSpecificIntent();

        addSubject(intent);
        addEmailRecipients(intent, Intent.EXTRA_EMAIL, recipientsTo);
        addEmailRecipients(intent, Intent.EXTRA_CC, recipientsCc);
        addEmailRecipients(intent, Intent.EXTRA_BCC, recipientsBcc);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        return intent;
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

    protected void checkNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument may not be null");
        }
    }

    protected abstract T getSelf();
}
