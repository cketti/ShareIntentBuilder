package de.cketti.shareintentbuilder;


import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class ShareIntentBuilderTest {
    private static final String TYPE_TEXT_PLAIN = "text/plain";

    @Test
    public void testShareText() {
        String demoText = "Simple text";
        Intent intent = ShareIntentBuilder.newInstance()
                .text(demoText)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringExtra(Intent.EXTRA_STREAM)).isNull();
    }
}
