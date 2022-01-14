package com.zerobase.fastlms.admin.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberStatusInput {

    String userId;

    String userStatus;
}
