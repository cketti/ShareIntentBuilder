/*
 * Copyright 2015 cketti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    public void shareShouldCallThroughToShareIntentBuilder() {
        textBuilder.share();

        verify(builder).share();
    }

    @Test
    public void shareWithTitleShouldCallThroughToShareIntentBuilder() {
        textBuilder.share(DEMO_TEXT);

        verify(builder).share(DEMO_TEXT);
    }

    @Test
    public void getSelfShouldReturnThis() {
        TextBuilder returnedSelf = textBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(textBuilder);
    }
}
