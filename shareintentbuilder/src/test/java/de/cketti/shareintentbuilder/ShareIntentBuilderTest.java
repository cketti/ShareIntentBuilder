package de.cketti.shareintentbuilder;


import java.util.Arrays;
import java.util.List;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


@RunWith(RobolectricTestRunner.class)
public class ShareIntentBuilderTest {
    private static final String TYPE_TEXT_PLAIN = "text/plain";

    @Test
    public void testShareTextWithEmail() {
        String demoText = "Help, I'm trapped in the basement!";
        String[] to = { "alice@example.com", "bob@example.com", "charlie@example.com" };
        String[] cc = { "joe@example.com", "john@example.com" };
        String[] bcc = { "cketti@gmail.com" };
        Intent intent = ShareIntentBuilder.newInstance()
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
        ShareIntentBuilder.newInstance().text("text").email(email);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithEmailListNullArgument() {
        List<String> emails = null;
        ShareIntentBuilder.newInstance().text("text").email(emails);
    }

    @Test
    public void testShareTextWithEmailListContainingNullElement() {
        String demoText = "boring.example.com";
        String email = "joe@example.com";
        String[] emails = { "alice@example.com", null, "charlie@example.com" };
        TextBuilder textBuilder = ShareIntentBuilder.newInstance().text(demoText).email(email);
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
        ShareIntentBuilder.newInstance().text("text").cc(email);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithCcListNullArgument() {
        List<String> emails = null;
        ShareIntentBuilder.newInstance().text("text").cc(emails);
    }

    @Test
    public void testShareTextWithCcListContainingNullElement() {
        String demoText = "boring.example.com";
        String email = "joe@example.com";
        String[] emails = { "alice@example.com", null, "charlie@example.com" };
        TextBuilder textBuilder = ShareIntentBuilder.newInstance().text(demoText).cc(email);
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
        ShareIntentBuilder.newInstance().text("text").bcc(email);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testShareTextWithBccListNullArgument() {
        List<String> emails = null;
        ShareIntentBuilder.newInstance().text("text").bcc(emails);
    }

    @Test
    public void testShareTextWithBccListContainingNullElement() {
        String demoText = "boring.example.com";
        String email = "joe@example.com";
        String[] emails = { "alice@example.com", null, "charlie@example.com" };
        TextBuilder textBuilder = ShareIntentBuilder.newInstance().text(demoText).bcc(email);
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
        Intent intent = ShareIntentBuilder.newInstance()
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
        ShareIntentBuilder.newInstance().text("text").subject(null);
    }
}
