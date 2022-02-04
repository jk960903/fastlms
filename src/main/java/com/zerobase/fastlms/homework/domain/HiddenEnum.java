package com.zerobase.fastlms.homework.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HiddenEnum {
    FALSE(false),
    TRUE(true);

    private final boolean hidden;

    public boolean getHidden(){
        return hidden;
    }

}
