package com.zerobase.fastlms.admin.banner.dto;

import com.zerobase.fastlms.admin.banner.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {

    private Long id;

    private String bannerName;

    private String LinkAddress;

    private LocalDateTime regdate;

    private Integer orderValue;

    private boolean isShow;

    private String alterText;

    private Integer openValue;

    private String fileName;

    private long seq;

    private long totalCount;

    public static BannerDto of(Banner banner){
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .LinkAddress(banner.getLinkAddress())
                .regdate(banner.getRegdate())
                .orderValue(banner.getOrderValue())
                .isShow(banner.isShow())
                .alterText(banner.getAlterText())
                .openValue(banner.getOpenValue())
                .fileName(banner.getFileName())
                .build();
    }
}
