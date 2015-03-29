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
    public void shareShouldCallThroughToShareIntentBuilder() {
        streamBuilder.share();

        verify(builder).share();
    }

    @Test
    public void shareWithTitleShouldCallThroughToShareIntentBuilder() {
        String title = "Share with…";
        streamBuilder.share(title);

        verify(builder).share(title);
    }

    @Test
    public void getSelfShouldReturnThis() {
        StreamBuilder returnedSelf = streamBuilder.getSelf();

        assertThat(returnedSelf).isSameAs(streamBuilder);
    }
}
