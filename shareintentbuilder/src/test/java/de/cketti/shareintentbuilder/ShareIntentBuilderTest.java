package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    public void testShareMultipleTexts() {
        String[] demoTexts = { "One text", "Another one" };
        Intent intent = ShareIntentBuilder.newInstance()
                .text(demoTexts[0])
                .text(demoTexts[1])
                .build();

        assertThatMultipleTextsWorkAsExpected(intent, demoTexts);
    }

    @Test
    public void testShareMultipleTextsAsListInput() {
        String[] demoTexts = { "one", "two", "three" };
        Intent intent = ShareIntentBuilder.newInstance()
                .text(Arrays.asList(demoTexts))
                .build();

        assertThatMultipleTextsWorkAsExpected(intent, demoTexts);
    }

    @Test(expected = IllegalStateException.class)
    public void testShareWithoutText() {
        ShareIntentBuilder.newInstance().build();
    }

    private void assertThatMultipleTextsWorkAsExpected(Intent intent, String[] demoText) {
        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND_MULTIPLE);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringArrayListExtra(Intent.EXTRA_TEXT).getClass()).isEqualTo(ArrayList.class);
        assertThat(intent.getStringExtra(Intent.EXTRA_STREAM)).isNull();
        assertThat(intent.getStringArrayListExtra(Intent.EXTRA_TEXT)).isEqualTo(Arrays.asList(demoText));
    }
}
