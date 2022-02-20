package com.zerobase.fastlms.homework12.domain.type;

public enum YesNo {

    Y, N;

    public boolean isYes() {
        return this == Y;
    }

    public boolean isNo() {
        return this == N;
    }
}
