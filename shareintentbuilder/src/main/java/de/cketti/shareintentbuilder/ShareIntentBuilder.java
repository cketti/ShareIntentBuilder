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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


/**
 * A helper to create share intents, i.e. {@link Intent#ACTION_SEND} and {@link Intent#ACTION_SEND_MULTIPLE} intents.
 *
 * <p>This builder can only be used indirectly through a couple of wrapper classes. This allows for the type-safe
 * generation of share intents that do not contain invalid combinations of intent extras.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * <code>
 * ShareIntentBuilder.from(activity)
 *         .text("Sharing is caring!")
 *         .to("everyone@example.com")
 *         .cc("carebear@example.com")
 *         .share("Share this important message with…");
 * </code>
 * </pre>
 *
 * @see #from(Context)
 */
public class ShareIntentBuilder {
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";

    private final Context context;
    final MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();
    private boolean ignoreSpecification = false;
    final List<String> texts = new ArrayList<>();
    final List<Uri> streams = new ArrayList<>();
    private String subject;
    private final List<String> recipientsTo = new ArrayList<>();
    private final List<String> recipientsCc = new ArrayList<>();
    private final List<String> recipientsBcc = new ArrayList<>();

    private ShareIntentBuilder(Context context) {
        this.context = context;
    }

    /**
     * Create the first in a series of type-safe builder wrappers to create a share intent or to launch a share using
     * that intent.
     *
     * @param context
     *         the {@code Context} that will be used to launch the share
     *
     * @return a share intent builder wrapper
     */
    public static ShareIntentNoBuilder from(@NonNull Context context) {
        checkNotNull(context);

        ShareIntentBuilder builder = new ShareIntentBuilder(context);
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
        String type = context.getContentResolver().getType(stream);
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
        addCallingPackageExtra(intent);
        addCallingActivityExtraIfAvailable(intent);
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

        startActivity(chooserIntent);
    }

    private void startActivity(Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        context.startActivity(intent);
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

    private void addCallingPackageExtra(Intent intent) {
        intent.putExtra(EXTRA_CALLING_PACKAGE, context.getPackageName());
    }

    private void addCallingActivityExtraIfAvailable(Intent intent) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            intent.putExtra(EXTRA_CALLING_ACTIVITY, activity.getComponentName());
        }
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
