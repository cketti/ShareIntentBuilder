package de.cketti.shareintentbuilder;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MimeTypeAggregatorTest {

    @Test
    public void getTypeShouldReturnSingleMimeTypeThatWasAdded() {
        String type = "text/plain";
        MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();
        mimeTypeAggregator.add(type);
        String resultType = mimeTypeAggregator.getType();

        assertThat(resultType).isEqualTo(type);
    }

    @Test
    public void addingSingleTypeTwiceShouldReturnThatType() {
        String type = "text/plain";
        MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();
        mimeTypeAggregator.add(type);
        mimeTypeAggregator.add(type);
        String resultType = mimeTypeAggregator.getType();

        assertThat(resultType).isEqualTo(type);
    }

    @Test
    public void addingDifferentTypesWithSameTopLevelTypeShouldReturnTopLevelTypeSlashAsterisk() {
        MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();
        mimeTypeAggregator.add("text/plain");
        mimeTypeAggregator.add("text/html");
        String resultType = mimeTypeAggregator.getType();

        assertThat(resultType).isEqualTo("text/*");
    }

    @Test
    public void addingCompletelyDifferentTypesShouldReturnAsteriskSlashAsterisk() {
        MimeTypeAggregator mimeTypeAggregator = new MimeTypeAggregator();
        mimeTypeAggregator.add("text/plain");
        mimeTypeAggregator.add("image/png");
        String resultType = mimeTypeAggregator.getType();

        assertThat(resultType).isEqualTo("*/*");
    }

    @Test(expected = IllegalStateException.class)
    public void getTypeShouldThrowExceptionWhenNoMimeTypeWasAdded() {
        new MimeTypeAggregator().getType();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenMimeTypeIsInvalidOne() {
        new MimeTypeAggregator().add("something");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenMimeTypeIsInvalidTwo() {
        new MimeTypeAggregator().add("application/octet_stream");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenMimeTypeIsInvalidThree() {
        new MimeTypeAggregator().add("/");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenMimeTypeIsInvalidFour() {
        new MimeTypeAggregator().add("a/b/c");
    }
}
