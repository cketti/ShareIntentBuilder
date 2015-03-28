package de.cketti.shareintentbuilder;


import java.util.Arrays;
import java.util.List;

import android.content.Intent;

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
public class TextBuilderTest {
    private static final String DEMO_TEXT = "Sharing is caring!";
    private static final List<String> DEMO_TEXTS = Arrays.asList(DEMO_TEXT, "Share, but do it right!");

    private final Intent dummyIntent = new Intent();
    private ShareIntentBuilder builder;
    private TextBuilder textBuilder;

    @Before
    public void createTextBuilderWithMockedShareIntentBuilder() {
        builder = mock(ShareIntentBuilder.class);
        when(builder.build()).thenReturn(dummyIntent);

        textBuilder = new TextBuilder(builder);
    }

    @Test
    public void textShouldCallThroughToShareIntentBuilder() {
        TextBuilder returnedBuilder = textBuilder.text(DEMO_TEXT);

        verify(builder).text(DEMO_TEXT);
        assertThat(returnedBuilder).isSameAs(textBuilder);
    }

    @Test
    public void textWithCollectionShouldCallThroughToShareIntentBuilder() {
        TextBuilder returnedBuilder = textBuilder.text(DEMO_TEXTS);

        verify(builder).text(DEMO_TEXTS);
        assertThat(returnedBuilder).isSameAs(textBuilder);
    }

    @Test
    public void buildShouldCallThroughToShareIntentBuilder() {
        Intent intent = textBuilder.build();

        verify(builder).build();
        assertThat(intent).isSameAs(dummyIntent);
    }

    @Test
    public void getSelfShouldReturnThis() {
        TextBuilder returnedSelf = textBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(textBuilder);
    }
}
