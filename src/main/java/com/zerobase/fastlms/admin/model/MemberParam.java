package com.zerobase.fastlms.admin.model;

import lombok.*;

@Data
public class MemberParam {

    private long pageIndex;
    private long pageSize;
    private String searchType;
    private String searchValue;

    private String userId;

    public long getPageStart(){
        init();
        return (pageIndex -1) * pageSize;
    }

    public long getPageEnd(){
        init();
        return pageSize;
    }

    public void init(){
        if(pageIndex < 1){
            pageIndex =1;
        }
        if(pageSize < 10){
            pageSize = 10;
        }
    }

    public String getQueryString(){
        init();

        StringBuilder sb = new StringBuilder();

        if(searchType!=null && searchType.length() > 0){
            sb.append(String.format("searchType=%s",searchType));
        }

        if(searchValue!=null && searchValue.length() > 0 ){
            sb.append(String.format("searchValue=%s",searchValue));
        }


        return sb.toString();

    }
}
