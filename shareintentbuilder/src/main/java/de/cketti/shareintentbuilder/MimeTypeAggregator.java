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


import java.util.Locale;


class MimeTypeAggregator {
    private static final String ASTERISK_WILDCARD = "*";
    private static final String DELIMITER = "/";

    private String topLevelType;
    private String subType;

    public String getType() {
        if (isNotInitialized()) {
            throw new IllegalStateException("Add at least one MIME type");
        }
        return topLevelType + DELIMITER + subType;
    }

    public void add(String type) {
        checkTypeFormat(type);
        if (alreadyMatchesAllTypes()) {
            return;
        }

        String[] parts = type.split(DELIMITER, 2);
        String newTopLevelType = parts[0].toLowerCase(Locale.US);
        String newSubType = parts[1].toLowerCase(Locale.US);

        if (isNotInitialized()) {
            topLevelType = newTopLevelType;
            subType = newSubType;
        } else if (matchesTopLevelType(newTopLevelType)) {
            topLevelType = ASTERISK_WILDCARD;
            subType = ASTERISK_WILDCARD;
        } else if (!matchesSubType(newSubType)) {
            subType = ASTERISK_WILDCARD;
        }
    }

    private void checkTypeFormat(String type) {
        //TODO: check this against specification
        if (!type.matches("[a-zA-Z0-9+.-]+/[a-zA-Z0-9+.-]+")) {
            throw new IllegalArgumentException("Not a valid MIME type");
        }
    }

    private boolean alreadyMatchesAllTypes() {
        return ASTERISK_WILDCARD.equals(topLevelType);
    }

    private boolean isNotInitialized() {
        return topLevelType == null;
    }

    private boolean matchesTopLevelType(String newTopLevelType) {
        return !topLevelType.equals(newTopLevelType);
    }

    private boolean matchesSubType(String newSubType) {
        return subType.equals(newSubType);
    }
}
