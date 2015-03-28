package de.cketti.shareintentbuilder;


import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class StreamBuilderTest {
    private static final String DEMO_TYPE = "image/png";
    private static final Uri DEMO_STREAM = Uri.parse("content://dummy/42");

    private final Intent dummyIntent = new Intent();
    private ShareIntentBuilder builder;
    private StreamBuilder streamBuilder;

    @Before
    public void createStreamBuilderWithMockedShareIntentBuilder() {
        builder = mock(ShareIntentBuilder.class);
        when(builder.build()).thenReturn(dummyIntent);

        streamBuilder = new StreamBuilder(builder);
    }

    @Test
    public void streamShouldCallThroughToShareIntentBuilder() {
        StreamBuilder returnedBuilder = streamBuilder.stream(DEMO_STREAM);

        verify(builder).stream(DEMO_STREAM);
        assertThat(returnedBuilder).isSameAs(streamBuilder);
    }

    @Test
    public void streamWithTypeShouldCallThroughToShareIntentBuilder() {
        StreamBuilder returnedBuilder = streamBuilder.stream(DEMO_STREAM, DEMO_TYPE);

        verify(builder).stream(DEMO_STREAM, DEMO_TYPE);
        assertThat(returnedBuilder).isSameAs(streamBuilder);
    }

    @Test
    public void buildShouldCallThroughToShareIntentBuilder() {
        Intent intent = streamBuilder.build();

        verify(builder).build();
        assertThat(intent).isSameAs(dummyIntent);
    }

    @Test
    public void getSelfShouldReturnThis() {
        StreamBuilder returnedSelf = streamBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(streamBuilder);
    }
}
