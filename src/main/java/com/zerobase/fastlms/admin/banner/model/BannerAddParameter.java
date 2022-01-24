package com.zerobase.fastlms.admin.banner.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerAddParameter {

    private long id;
    private String bannerName;
    private String address;
    private int openValue;
    private int orderValue;
    private int isOpen;

    private String alterText;

}
