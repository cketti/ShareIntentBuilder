package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;


public class StreamBuilder extends ShareIntentBuilder implements AcceptsExtraStream {
    private List<Uri> streams = new ArrayList<>();
    private String topLevelType;
    private String subType;

    StreamBuilder() {}

    @Override
    public StreamBuilder stream(Uri stream, String type) {
        updateType(type);
        streams.add(stream);
        return this;
    }

    @Override
    protected Intent buildTypeSpecificIntent() {
        Intent intent = new Intent();
        intent.setType(buildType());

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

    private String buildType() {
        return topLevelType + "/" + subType;
    }

    private void updateType(String type) {
        if ("*".equals(topLevelType)) {
            return;
        }

        String[] parts = type.split("/", 2);
        String newTopLevelType = parts[0].toLowerCase(Locale.US);
        String newSubType = parts[1].toLowerCase(Locale.US);

        if (topLevelType == null) {
            topLevelType = newTopLevelType;
            subType = newSubType;
        } else if (!topLevelType.equals(newTopLevelType)) {
            topLevelType = "*";
            subType = "*";
        } else if (!subType.equals(newSubType)) {
            subType = "*";
        }
    }
}
