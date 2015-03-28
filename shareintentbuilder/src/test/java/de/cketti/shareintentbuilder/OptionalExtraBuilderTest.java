package de.cketti.shareintentbuilder;


import java.util.Arrays;
import java.util.Collection;

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
public class OptionalExtraBuilderTest {
    private static final String DEMO_SUBJECT = "Sharing is caring!";
    private static final String DEMO_EMAIL_ADDRESS = "alice@example.com";
    private static final Collection<String> DEMO_EMAIL_ADDRESSES = Arrays.asList(DEMO_EMAIL_ADDRESS, "bob@example.com");

    private final Intent dummyIntent = new Intent();
    private ShareIntentBuilder builder;
    private TestOptionalExtraBuilder optionalExtraBuilder;

    @Before
    public void createOptionalExtraBuilderWithMockedShareIntentBuilder() {
        builder = mock(ShareIntentBuilder.class);
        when(builder.build()).thenReturn(dummyIntent);

        optionalExtraBuilder = new TestOptionalExtraBuilder(builder);
    }

    @Test
    public void subjectShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.subject(DEMO_SUBJECT);

        verify(builder).subject(DEMO_SUBJECT);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void emailShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.email(DEMO_EMAIL_ADDRESS);

        verify(builder).to(DEMO_EMAIL_ADDRESS);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void emailWithCollectionShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.email(DEMO_EMAIL_ADDRESSES);

        verify(builder).to(DEMO_EMAIL_ADDRESSES);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void toShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.to(DEMO_EMAIL_ADDRESS);

        verify(builder).to(DEMO_EMAIL_ADDRESS);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void toWithCollectionShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.to(DEMO_EMAIL_ADDRESSES);

        verify(builder).to(DEMO_EMAIL_ADDRESSES);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void ccShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.cc(DEMO_EMAIL_ADDRESS);

        verify(builder).cc(DEMO_EMAIL_ADDRESS);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void ccWithCollectionShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.cc(DEMO_EMAIL_ADDRESSES);

        verify(builder).cc(DEMO_EMAIL_ADDRESSES);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void bccShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.bcc(DEMO_EMAIL_ADDRESS);

        verify(builder).bcc(DEMO_EMAIL_ADDRESS);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }

    @Test
    public void bccWithCollectionShouldCallThroughToShareIntentBuilder() {
        TestOptionalExtraBuilder returnedBuilder = optionalExtraBuilder.bcc(DEMO_EMAIL_ADDRESSES);

        verify(builder).bcc(DEMO_EMAIL_ADDRESSES);
        assertThat(returnedBuilder).isSameAs(optionalExtraBuilder);
    }


    static class TestOptionalExtraBuilder extends OptionalExtraBuilder<TestOptionalExtraBuilder> {

        public TestOptionalExtraBuilder(ShareIntentBuilder builder) {
            super(builder);
        }

        @Override
        protected TestOptionalExtraBuilder getSelf() {
            return this;
        }
    }
}
