package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


public class StreamBuilder extends ShareIntentBuilder<StreamBuilder> implements AcceptsExtraStream {
    private final List<Uri> streams = new ArrayList<>();
    private MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();

    StreamBuilder(Activity activity) {
        super(activity);
    }

    @Override
    public StreamBuilder stream(@NonNull Uri stream) {
        checkNotNull(stream);

        String type = getTypeViaContentResolver(stream);
        addStream(stream, type);
        return this;
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

    @Override
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        checkNotNull(stream);
        checkNotNull(type);

        addStream(stream, type);
        return this;
    }

    @Override
    protected Intent buildTypeSpecificIntent() {
        Intent intent = new Intent();
        intent.setType(mimeTypeAggregator.getType());

        if (streams.size() > 1) {
            setMultipleStreams(intent);
        } else {
            setSingleStream(intent);
        }

        return intent;
    }

    private void setSingleStream(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, streams.get(0));
    }

    private void setMultipleStreams(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(streams));
    }

    @Override
    protected StreamBuilder getSelf() {
        return null;
    }
}
