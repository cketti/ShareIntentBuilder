package de.cketti.shareintentbuilder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class ShareIntentBuilderTest {
    private static final String DEMO_PACKAGE_NAME = "com.example.demo";
    private static final ComponentName DEMO_COMPONENT_NAME =
            new ComponentName(ShareIntentBuilderTest.DEMO_PACKAGE_NAME, "DummyActivity");
    private static final String TYPE_TEXT_PLAIN = "text/plain";
    private static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";

    protected Activity activity;

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
        } catch (IllegalArgumentException ignored) {}
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
        } catch (IllegalArgumentException ignored) {}
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
        } catch (IllegalArgumentException ignored) {}
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
        } catch (IllegalArgumentException ignored) {}
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
        Uri[] uris = { Uri.parse("content://dummy/42"), Uri.parse("content://dummy/23")};
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
        Uri uri = Uri.parse("content://dummy/42");
        setUpMockContentResolver(uri, null);

        ShareIntentBuilder.from(activity).stream(uri);
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
