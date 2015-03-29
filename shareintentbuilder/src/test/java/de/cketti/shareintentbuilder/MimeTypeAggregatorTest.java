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
