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
public class TextAndStreamNoBuilderTest {
    private static final String DEMO_TEXT = "Sharing is caring!";
    private static final String DEMO_TYPE = "image/png";
    private static final Uri DEMO_STREAM = Uri.parse("content://dummy/42");

    private final Intent dummyIntent = new Intent();
    private ShareIntentBuilder builder;
    private TextAndStreamNoBuilder textAndStreamNoBuilder;

    @Before
    public void createTextAndStreamBuilderWithMockedShareIntentBuilder() {
        builder = mock(ShareIntentBuilder.class);
        when(builder.build()).thenReturn(dummyIntent);

        textAndStreamNoBuilder = new TextAndStreamNoBuilder(builder);
    }

    @Test
    public void constructorShouldCallIgnoreSpecification() {
        verify(builder).ignoreSpecification();
    }

    @Test
    public void streamShouldCreateTextAndStreamBuilder() {
        TextAndStreamBuilder returnedBuilder = textAndStreamNoBuilder.stream(DEMO_STREAM);

        verify(builder).stream(DEMO_STREAM);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void streamWithTypeShouldCreateTextAndStreamBuilder() {
        TextAndStreamBuilder returnedBuilder = textAndStreamNoBuilder.stream(DEMO_STREAM, DEMO_TYPE);

        verify(builder).stream(DEMO_STREAM, DEMO_TYPE);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void textShouldCreateStreamBuilder() {
        StreamBuilder returnedBuilder = textAndStreamNoBuilder.text(DEMO_TEXT);

        verify(builder).text(DEMO_TEXT);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void getSelfShouldReturnThis() {
        TextAndStreamNoBuilder returnedSelf = textAndStreamNoBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(textAndStreamNoBuilder);
    }
}
