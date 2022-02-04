package com.zerobase.fastlms.homework.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusEnum {
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    PAUSE("PAUSE");

    private final String status;

    public String getStatus(){
        return this.status;
    }
}
