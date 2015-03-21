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
