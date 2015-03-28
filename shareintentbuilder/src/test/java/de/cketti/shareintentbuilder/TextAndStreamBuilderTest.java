package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class TextAndStreamBuilderTest {
    private static final String DEMO_TEXT = "Sharing is caring!";
    private static final String DEMO_TYPE = "image/png";
    private static final Uri DEMO_STREAM = Uri.parse("content://dummy/42");

    private final Intent dummyIntent = new Intent();
    private ShareIntentBuilder builder;
    private TextAndStreamBuilder textAndStreamBuilder;

    @Before
    public void createTextAndStreamBuilderWithMockedShareIntentBuilder() {
        builder = mock(ShareIntentBuilder.class);
        when(builder.build()).thenReturn(dummyIntent);

        textAndStreamBuilder = new TextAndStreamBuilder(builder);
    }

    @Test
    public void streamShouldCallThroughToShareIntentBuilder() {
        TextAndStreamBuilder returnedBuilder = textAndStreamBuilder.stream(DEMO_STREAM);

        verify(builder).stream(DEMO_STREAM);
        assertThat(returnedBuilder).isSameAs(textAndStreamBuilder);
    }

    @Test
    public void streamWithTypeShouldCallThroughToShareIntentBuilder() {
        TextAndStreamBuilder returnedBuilder = textAndStreamBuilder.stream(DEMO_STREAM, DEMO_TYPE);

        verify(builder).stream(DEMO_STREAM, DEMO_TYPE);
        assertThat(returnedBuilder).isSameAs(textAndStreamBuilder);
    }

    @Test
    public void textShouldCallThroughToShareIntentBuilder() {
        StreamBuilder returnedBuilder = textAndStreamBuilder.text(DEMO_TEXT);

        verify(builder).text(DEMO_TEXT);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void buildShouldCallThroughToShareIntentBuilder() {
        Intent intent = textAndStreamBuilder.build();

        verify(builder).build();
        assertThat(intent).isSameAs(dummyIntent);
    }

    @Test
    public void getSelfShouldReturnThis() {
        TextAndStreamBuilder returnedSelf = textAndStreamBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(textAndStreamBuilder);
    }
}
