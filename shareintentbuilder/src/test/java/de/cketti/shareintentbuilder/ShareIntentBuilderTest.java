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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ShareIntentBuilderTest {
    private static final String DEMO_PACKAGE_NAME = "com.example.demo";
    private static final ComponentName DEMO_COMPONENT_NAME =
            new ComponentName(ShareIntentBuilderTest.DEMO_PACKAGE_NAME, "DummyActivity");
    private static final String TYPE_TEXT_PLAIN = "text/plain";
    private static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";

    private Activity activity;

    @Before
    public void createActivity() {
        activity = mock(Activity.class);
        when(activity.getPackageName()).thenReturn(DEMO_PACKAGE_NAME);
        when(activity.getComponentName()).thenReturn(DEMO_COMPONENT_NAME);
    }

    @Test
    public void testShareTextWithEmail() {
        String demoText = "Help, I'm trapped in the basement!";
        String[] to = { "alice@example.com", "bob@example.com", "charlie@example.com" };
        String[] cc = { "joe@example.com", "john@example.com" };
        String[] bcc = { "cketti@gmail.com" };
        Intent intent = ShareIntentBuilder.from(activity)
                .text(demoText)
                .email(to[0]).to(to[1])
                .cc(Arrays.asList(cc))
                .bcc(bcc[0])
                .to(to[2])
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_EMAIL)).isEqualTo(to);
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_CC)).isEqualTo(cc);
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_BCC)).isEqualTo(bcc);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithEmailNullArgument() {
        String email = null;
        ShareIntentBuilder.from(activity).text("text").email(email);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithEmailListNullArgument() {
        List<String> emails = null;
        ShareIntentBuilder.from(activity).text("text").email(emails);
    }

    @Test
    public void testShareTextWithEmailListContainingNullElement() {
        String demoText = "boring.example.com";
        String email = "joe@example.com";
        String[] emails = { "alice@example.com", null, "charlie@example.com" };
        TextBuilder textBuilder = ShareIntentBuilder.from(activity).text(demoText).email(email);
        try {
            textBuilder.email(Arrays.asList(emails));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
        Intent intent = textBuilder.build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_EMAIL)).isEqualTo(new String[] { email });
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithCcNullArgument() {
        String email = null;
        ShareIntentBuilder.from(activity).text("text").cc(email);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithCcListNullArgument() {
        List<String> emails = null;
        ShareIntentBuilder.from(activity).text("text").cc(emails);
    }

    @Test
    public void testShareTextWithCcListContainingNullElement() {
        String demoText = "boring.example.com";
        String email = "joe@example.com";
        String[] emails = { "alice@example.com", null, "charlie@example.com" };
        TextBuilder textBuilder = ShareIntentBuilder.from(activity).text(demoText).cc(email);
        try {
            textBuilder.cc(Arrays.asList(emails));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
        Intent intent = textBuilder.build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_CC)).isEqualTo(new String[] { email });
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithBccNullArgument() {
        String email = null;
        ShareIntentBuilder.from(activity).text("text").bcc(email);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithBccListNullArgument() {
        List<String> emails = null;
        ShareIntentBuilder.from(activity).text("text").bcc(emails);
    }

    @Test
    public void testShareTextWithBccListContainingNullElement() {
        String demoText = "boring.example.com";
        String email = "joe@example.com";
        String[] emails = { "alice@example.com", null, "charlie@example.com" };
        TextBuilder textBuilder = ShareIntentBuilder.from(activity).text(demoText).bcc(email);
        try {
            textBuilder.bcc(Arrays.asList(emails));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
        Intent intent = textBuilder.build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_BCC)).isEqualTo(new String[] { email });
    }

    @Test
    public void testShareTextWithSubject() {
        String demoText = "This goes into the email body";
        String subject = "Hello World";
        Intent intent = ShareIntentBuilder.from(activity)
                .text(demoText)
                .subject(subject)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringExtra(Intent.EXTRA_SUBJECT)).isEqualTo(subject);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithNullSubject() {
        ShareIntentBuilder.from(activity).text("text").subject(null);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void staticFactoryMethodWithNullArgumentShouldThrowException() {
        ShareIntentBuilder.from(null);
    }

    @Test
    public void intentShouldContainExtraCallingPackageAndActivity() {
        Intent intent = ShareIntentBuilder.from(activity).text("text").build();

        assertThat(intent.getStringExtra(EXTRA_CALLING_PACKAGE)).isEqualTo(DEMO_PACKAGE_NAME);
        assertThat(intent.getParcelableExtra(EXTRA_CALLING_ACTIVITY)).isEqualTo(DEMO_COMPONENT_NAME);
    }

    @Test
    public void testShareText() {
        String demoText = "Simple text";
        Intent intent = ShareIntentBuilder.from(activity)
                .text(demoText)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getStringExtra(Intent.EXTRA_STREAM)).isNull();
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithNullArgument() {
        String demoText = null;
        ShareIntentBuilder.from(activity).text(demoText);
    }

    @Test
    public void testShareMultipleTexts() {
        String[] demoTexts = { "One text", "Another one" };
        Intent intent = ShareIntentBuilder.from(activity)
                .text(demoTexts[0])
                .text(demoTexts[1])
                .build();

        assertThatMultipleTextsWorkAsExpected(intent, demoTexts);
    }

    @Test
    public void testShareMultipleTextsAsListInput() {
        String[] demoTexts = { "one", "two", "three" };
        Intent intent = ShareIntentBuilder.from(activity)
                .text(Arrays.asList(demoTexts))
                .build();

        assertThatMultipleTextsWorkAsExpected(intent, demoTexts);
    }

    @Test
    public void testShareMultipleTextsAsListInputWithNullElement() {
        String demoText = "uno";
        String[] demoTexts = { "one", null, "three" };

        TextBuilder textBuilder = ShareIntentBuilder.from(activity).text(demoText);
        try {
            textBuilder.text(Arrays.asList(demoTexts));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
        Intent intent = textBuilder.build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareMultipleTextsWithNullArgument() {
        List<String> demoTexts = null;
        ShareIntentBuilder.from(activity).text(demoTexts);
    }

    @Test
    public void testShareStream() {
        String type = "image/png";
        Uri uri = Uri.parse("content://dummy/42");
        Intent intent = ShareIntentBuilder.from(activity)
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
        ShareIntentBuilder.from(activity).stream(null, "text/plain");
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareStreamWithSecondArgumentNull() {
        Uri uri = Uri.parse("content://dummy/42");
        ShareIntentBuilder.from(activity).stream(uri, null);
    }

    @Test
    public void testShareMultipleStreams() {
        Uri[] uris = { Uri.parse("content://dummy/42"), Uri.parse("content://dummy/23") };
        Intent intent = ShareIntentBuilder.from(activity)
                .stream(uris[0], "image/png")
                .stream(uris[1], "image/jpeg")
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND_MULTIPLE);
        assertThat(intent.getType()).isEqualTo("image/*");
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isNull();
        assertThat(intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM)).isEqualTo(Arrays.asList(uris));
    }

    @Test
    public void testStreamBuilderWithAutomaticTypeResolution() throws InterruptedException {
        final String type = "image/png";
        final Uri uri = Uri.parse("content://dummy/42");
        setUpMockContentResolver(uri, type);

        Intent intent = ShareIntentBuilder.from(activity)
                .stream(uri)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(type);
        assertThat(intent.getParcelableExtra(Intent.EXTRA_STREAM)).isEqualTo(uri);
    }

    @Test(expected = IllegalStateException.class)
    public void testShareStreamWithAutomaticTypeResolutionAndContentProviderReturningNullForType() {
        Uri stream = Uri.parse("content://dummy/42");
        setUpMockContentResolver(stream, null);

        ShareIntentBuilder.from(activity).stream(stream);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shareStreamWithFileUriShouldThrowException() {
        ShareIntentBuilder.from(activity).stream(Uri.parse("file:///dummy.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shareStreamWithFileUriAndTypeShouldThrowException() {
        ShareIntentBuilder.from(activity).stream(Uri.parse("file:///dummy.txt"), "application/octet-stream");
    }

    @Test
    public void testShareStreamWithFileUriAndIgnoreSpecification() {
        Uri stream = Uri.parse("file:///42");
        String streamType = "image/png";

        Intent intent = ShareIntentBuilder.from(activity)
                .ignoreSpecification()
                .stream(stream, streamType)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(streamType);
        assertThat(intent.getParcelableExtra(Intent.EXTRA_STREAM)).isEqualTo(stream);
    }

    @Test
    public void testShareTextAndStream() {
        String demoText = "Share! Because data wants to be free";
        Uri stream = Uri.parse("content://dummy/42");
        String streamType = "image/png";
        Intent intent = ShareIntentBuilder.from(activity)
                .ignoreSpecification()
                .text(demoText)
                .stream(stream, streamType)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(intent.getType()).isEqualTo(streamType);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getParcelableExtra(Intent.EXTRA_STREAM)).isEqualTo(stream);
    }

    @Test
    public void testShareTextAndMultipleStreams() {
        String demoText = "Share! Because data wants to be free";
        Uri streamOne = Uri.parse("content://dummy/42");
        Uri streamTwo = Uri.parse("content://dummy/23");
        String streamType = "image/png";
        Intent intent = ShareIntentBuilder.from(activity)
                .ignoreSpecification()
                .text(demoText)
                .stream(streamOne, streamType)
                .stream(streamTwo, streamType)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND_MULTIPLE);
        assertThat(intent.getType()).isEqualTo(streamType);
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM))
                .isEqualTo(Arrays.asList(streamOne, streamTwo));
    }

    @Test
    public void testShareTextAndMultipleStreamsWithTextSetInBetween() {
        String recipient = "joe@example.com";
        String demoText = "Share! Because data wants to be free";
        Uri streamOne = Uri.parse("content://dummy/42");
        Uri streamTwo = Uri.parse("content://dummy/23");
        Uri streamThree = Uri.parse("content://dummy/-1");
        String streamTypeOne = "image/png";
        String streamTypeTwo = "image/jpeg";
        String streamTypeThree = "application/octet-stream";
        Intent intent = ShareIntentBuilder.from(activity)
                .ignoreSpecification()
                .to(recipient)
                .stream(streamOne, streamTypeOne)
                .stream(streamTwo, streamTypeTwo)
                .text(demoText)
                .stream(streamThree, streamTypeThree)
                .build();

        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND_MULTIPLE);
        assertThat(intent.getType()).isEqualTo("*/*");
        assertThat(intent.getStringArrayExtra(Intent.EXTRA_EMAIL)).isEqualTo(new String[] { recipient });
        assertThat(intent.getStringExtra(Intent.EXTRA_TEXT)).isEqualTo(demoText);
        assertThat(intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM))
                .isEqualTo(Arrays.asList(streamOne, streamTwo, streamThree));
    }

    @Test
    public void shareShouldCreateChooserIntent() {
        TextBuilder builder = ShareIntentBuilder.from(activity).text("Simple text");
        Intent intent = builder.build();
        builder.share();

        ArgumentCaptor<Intent> startActivityCaptor = ArgumentCaptor.forClass(Intent.class);
        verify(activity).startActivity(startActivityCaptor.capture());

        Intent chooserIntent = startActivityCaptor.getValue();
        assertThat(chooserIntent.getAction()).isEqualTo(Intent.ACTION_CHOOSER);
        assertThat(chooserIntent.getParcelableExtra(Intent.EXTRA_INTENT)).isEqualTo(intent);
    }

    @Test
    public void shareWithTitleShouldCreateChooserIntent() {
        String shareDialogTitle = "I like sharing";
        TextBuilder builder = ShareIntentBuilder.from(activity).text("Simple text");
        Intent intent = builder.build();
        builder.share(shareDialogTitle);

        ArgumentCaptor<Intent> startActivityCaptor = ArgumentCaptor.forClass(Intent.class);
        verify(activity).startActivity(startActivityCaptor.capture());

        Intent chooserIntent = startActivityCaptor.getValue();
        assertThat(chooserIntent.getAction()).isEqualTo(Intent.ACTION_CHOOSER);
        assertThat(chooserIntent.getStringExtra(Intent.EXTRA_TITLE)).isEqualTo(shareDialogTitle);
        assertThat(chooserIntent.getParcelableExtra(Intent.EXTRA_INTENT)).isEqualTo(intent);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void shareWithNullTitleShouldThrowException() {
        ShareIntentBuilder.from(activity).text("dummy").share(null);
    }

    @Test
    public void shareFromContextShouldAddNewTaskFlag() throws Exception {
        Context context = createFakeContext();

        ShareIntentBuilder.from(context).text("dummy").share();

        ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(Intent.class);
        verify(context).startActivity(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getFlags() & Intent.FLAG_ACTIVITY_NEW_TASK)
                .isEqualTo(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    private Context createFakeContext() {
        Context context = mock(Context.class);
        when(context.getPackageName()).thenReturn(DEMO_PACKAGE_NAME);
        return context;
    }

    private void setUpMockContentResolver(Uri uri, String type) {
        ContentResolver contentResolver = mock(ContentResolver.class);
        when(contentResolver.getType(uri)).thenReturn(type);
        when(activity.getContentResolver()).thenReturn(contentResolver);
    }

    private void assertThatMultipleTextsWorkAsExpected(Intent intent, String[] demoText) {
        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_SEND_MULTIPLE);
        assertThat(intent.getType()).isEqualTo(TYPE_TEXT_PLAIN);
        assertThat(intent.getStringArrayListExtra(Intent.EXTRA_TEXT).getClass()).isEqualTo(ArrayList.class);
        assertThat(intent.getStringExtra(Intent.EXTRA_STREAM)).isNull();
        assertThat(intent.getStringArrayListExtra(Intent.EXTRA_TEXT)).isEqualTo(Arrays.asList(demoText));
    }
}
