package de.cketti.shareintentbuilder;


import java.util.Arrays;
import java.util.List;

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
public class ShareIntentNoBuilderTest {
    private static final String DEMO_TEXT = "Sharing is caring!";
    private static final List<String> DEMO_TEXTS = Arrays.asList(DEMO_TEXT, "Share, but do it right!");
    private static final String DEMO_TYPE = "image/png";
    private static final Uri DEMO_STREAM = Uri.parse("content://dummy/42");

    private final Intent dummyIntent = new Intent();
    private ShareIntentBuilder builder;
    private ShareIntentNoBuilder shareIntentNoBuilder;

    @Before
    public void createShareIntentNoBuilderWithMockedShareIntentBuilder() {
        builder = mock(ShareIntentBuilder.class);
        when(builder.build()).thenReturn(dummyIntent);

        shareIntentNoBuilder = new ShareIntentNoBuilder(builder);
    }

    @Test
    public void ignoreSpecificationShouldCreateTextAndStreamNoBuilder() {
        TextAndStreamNoBuilder returnedBuilder = shareIntentNoBuilder.ignoreSpecification();

        verify(builder).ignoreSpecification();
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void textShouldCreateTextBuilder() {
        TextBuilder returnedBuilder = shareIntentNoBuilder.text(DEMO_TEXT);

        verify(builder).text(DEMO_TEXT);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void textWithCollectionShouldCreateTextBuilder() {
        TextBuilder returnedBuilder = shareIntentNoBuilder.text(DEMO_TEXTS);

        verify(builder).text(DEMO_TEXTS);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void streamShouldCallThroughToShareIntentBuilder() {
        StreamBuilder returnedBuilder = shareIntentNoBuilder.stream(DEMO_STREAM);

        verify(builder).stream(DEMO_STREAM);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void streamWithTypeShouldCallThroughToShareIntentBuilder() {
        StreamBuilder returnedBuilder = shareIntentNoBuilder.stream(DEMO_STREAM, DEMO_TYPE);

        verify(builder).stream(DEMO_STREAM, DEMO_TYPE);
        assertThat(returnedBuilder.builder).isSameAs(builder);
    }

    @Test
    public void getSelfShouldReturnThis() {
        ShareIntentNoBuilder returnedSelf = shareIntentNoBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(shareIntentNoBuilder);
    }
}
