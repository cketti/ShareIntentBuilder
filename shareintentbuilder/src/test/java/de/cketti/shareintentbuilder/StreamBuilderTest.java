package de.cketti.shareintentbuilder;


import java.util.Arrays;

import android.content.Intent;
import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class StreamBuilderTest {

    @Test
    public void testShareStream() {
        String type = "image/png";
        Uri uri = Uri.parse("content://dummy/42");
        Intent intent = ShareIntentBuilder.newInstance()
                .stream(uri, type)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(type);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isNull();
        assertThat(intent.getParcelableExtra(Intent.EXTRA_STREAM)).isEqualTo(uri);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareStreamWithFirstArgumentNull() {
        ShareIntentBuilder.newInstance().stream(null, "text/plain");
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareStreamWithSecondArgumentNull() {
        Uri uri = Uri.parse("content://dummy/42");
        ShareIntentBuilder.newInstance().stream(uri, null);
    }

    @Test
    public void testShareMultipleStreams() {
        Uri[] uris = { Uri.parse("content://dummy/42"), Uri.parse("content://dummy/23")};
        Intent intent = ShareIntentBuilder.newInstance()
                .stream(uris[0], "image/png")
                .stream(uris[1], "image/jpeg")
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND_MULTIPLE);
        assertThat(intent.getType()).isEqualTo("image/*");
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isNull();
        assertThat(intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM)).isEqualTo(Arrays.asList(uris));
    }
}
