package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


public class ShareIntentBuilder {
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";

    private final Activity activity;
    final MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();
    private boolean ignoreSpecification = false;
    final List<String> texts = new ArrayList<>();
    final List<Uri> streams = new ArrayList<>();
    private String subject;
    private final List<String> recipientsTo = new ArrayList<>();
    private final List<String> recipientsCc = new ArrayList<>();
    private final List<String> recipientsBcc = new ArrayList<>();

    private ShareIntentBuilder(Activity activity) {
        this.activity = activity;
    }

    public static ShareIntentNoBuilder from(@NonNull Activity activity) {
        checkNotNull(activity);

        ShareIntentBuilder builder = new ShareIntentBuilder(activity);
        return new ShareIntentNoBuilder(builder);
    }

    void ignoreSpecification() {
        ignoreSpecification = true;
    }

    void text(@NonNull String text) {
        checkNotNull(text);

        texts.add(text);
    }

    void text(@NonNull Collection<String> texts) {
        checkNotNull(texts);
        for (String text : texts) {
            checkNotNull(text);
        }

        this.texts.addAll(texts);
    }

    void stream(@NonNull Uri stream) {
        checkNotNull(stream);

        String type = getTypeViaContentResolver(stream);
        addStream(stream, type);
    }

    void stream(@NonNull Uri stream, @NonNull String type) {
        checkNotNull(stream);
        checkNotNull(type);

        addStream(stream, type);
    }

    private String getTypeViaContentResolver(Uri stream) {
        String type = activity.getContentResolver().getType(stream);
        if (type == null) {
            throw new IllegalStateException("Content provider needs to provide a type");
        }
        return type;
    }

    private void addStream(Uri stream, String type) {
        mimeTypeAggregator.add(type);
        streams.add(stream);
    }

    void subject(@NonNull String subject) {
        checkNotNull(subject);

        this.subject = subject;
    }

    void to(@NonNull String email) {
        checkNotNull(email);

        recipientsTo.add(email);
    }

    void to(@NonNull Collection<String> emails) {
        checkNotNull(emails);
        for (String email : emails) {
            checkNotNull(email);
        }

        recipientsTo.addAll(emails);
    }

    void cc(@NonNull String email) {
        checkNotNull(email);

        recipientsCc.add(email);
    }

    void cc(@NonNull Collection<String> emails) {
        checkNotNull(emails);
        for (String email : emails) {
            checkNotNull(email);
        }

        recipientsCc.addAll(emails);
    }

    void bcc(@NonNull String email) {
        checkNotNull(email);

        recipientsBcc.add(email);
    }

    void bcc(@NonNull Collection<String> emails) {
        checkNotNull(emails);
        for (String email : emails) {
            checkNotNull(email);
        }

        recipientsBcc.addAll(emails);
    }

    Intent build() {
        Intent intent = new Intent();
        if (texts.isEmpty()) {
            buildShareIntentWithStream(intent);
        } else if (streams.isEmpty()) {
            buildShareIntentWithText(intent);
        } else if (ignoreSpecification) {
            buildShareIntentWithTextAndStream(intent);
        } else {
            throw new AssertionError("Text and stream supplied despite 'ignoreSpecification' being false");
        }

        addOptionalExtras(intent);
        addCallingPackageAndActivity(intent);
        addActivityFlags(intent);

        return intent;
    }

    void share() {
        shareWithOptionalTitle(null);
    }

    void share(CharSequence title) {
        checkNotNull(title);
        shareWithOptionalTitle(title);
    }

    private void shareWithOptionalTitle(CharSequence title) {
        Intent shareIntent = build();
        Intent chooserIntent = Intent.createChooser(shareIntent, title);

        activity.startActivity(chooserIntent);
    }

    private void buildShareIntentWithText(Intent intent) {
        intent.setType("text/plain");

        boolean hasMoreThanOneText = texts.size() > 1;
        if (hasMoreThanOneText) {
            setMultipleText(intent);
        } else {
            setSingleText(intent);
        }
    }

    private void setSingleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        setSingleTextExtra(intent);
    }

    private void setSingleTextExtra(Intent intent) {
        String text = texts.get(0);
        intent.putExtra(Intent.EXTRA_TEXT, text);
    }

    private void setMultipleText(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putStringArrayListExtra(Intent.EXTRA_TEXT, new ArrayList<>(texts));
    }

    private void buildShareIntentWithStream(Intent intent) {
        intent.setType(mimeTypeAggregator.getType());

        boolean hasMoreThanOneStream = streams.size() > 1;
        if (hasMoreThanOneStream) {
            setMultipleStreams(intent);
        } else {
            setSingleStream(intent);
        }
    }

    private void buildShareIntentWithTextAndStream(Intent intent) {
        buildShareIntentWithStream(intent);
        setSingleTextExtra(intent);
    }

    private void setSingleStream(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, streams.get(0));
    }

    private void setMultipleStreams(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(streams));
    }

    void addOptionalExtras(Intent intent) {
        addSubject(intent);
        addEmailRecipients(intent, Intent.EXTRA_EMAIL, recipientsTo);
        addEmailRecipients(intent, Intent.EXTRA_CC, recipientsCc);
        addEmailRecipients(intent, Intent.EXTRA_BCC, recipientsBcc);
    }

    private void addCallingPackageAndActivity(Intent intent) {
        intent.putExtra(EXTRA_CALLING_PACKAGE, activity.getPackageName());
        intent.putExtra(EXTRA_CALLING_ACTIVITY, activity.getComponentName());
    }

    private void addActivityFlags(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
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

    protected static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument may not be null");
        }
    }
}
