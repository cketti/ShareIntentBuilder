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
import android.support.annotation.NonNull;


public interface Buildable {
    /**
     * Create a share intent from the data in this builder.
     *
     * @return the share intent
     */
    @NonNull Intent build();

    /**
     * Start chooser activity for the share intent.
     */
    void share();

    /**
     * Start chooser activity with the specified title for the share intent.
     *
     * @param title
     *         the title to use for the chooser activity
     */
    void share(@NonNull CharSequence title);
}
