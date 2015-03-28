package de.cketti.shareintentbuilder;


import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;


public class StreamBuilder extends OptionalExtraBuilder<StreamBuilder> implements AcceptsExtraStream<StreamBuilder> {

    StreamBuilder(ShareIntentBuilder builder) {
        super(builder);
    }

    @NonNull
    public StreamBuilder stream(@NonNull Uri stream) {
        builder.stream(stream);
        return this;
    }

    @NonNull
    public StreamBuilder stream(@NonNull Uri stream, @NonNull String type) {
        builder.stream(stream, type);
        return this;
    }

    @NonNull
    public Intent build() {
        Intent intent = new Intent();
        intent.setType(builder.mimeTypeAggregator.getType());

        if (builder.streams.size() > 1) {
            setMultipleStreams(intent);
        } else {
            setSingleStream(intent);
        }

        builder.addExtrasAndFlagsToIntent(intent);
        return intent;
    }

    private void setSingleStream(Intent intent) {
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, builder.streams.get(0));
    }

    private void setMultipleStreams(Intent intent) {
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(builder.streams));
    }

    @Override
    protected StreamBuilder getSelf() {
        return this;
    }
}
