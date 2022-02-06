package com.zerobase.fastlms.homework2.type;

import java.util.stream.Stream;

public enum ZerobaseCourseStatus {
    OPEN, IN_PROGRESS, CLOSE, UNKNOWN;

    public static ZerobaseCourseStatus findOrDefault(String status) {
        return Stream.of(values())
                .filter(it -> it.name().equalsIgnoreCase(status))
                .findAny()
                .orElse(UNKNOWN);
    }

    public boolean isClose() {
        return this == CLOSE;
    }

    public boolean isUnknown() {
        return this == UNKNOWN;
    }
}
