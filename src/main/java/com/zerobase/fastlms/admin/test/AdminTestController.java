package com.zerobase.fastlms.admin.test;

import com.zerobase.fastlms.admin.test.entity.Indexing;
import com.zerobase.fastlms.admin.test.service.IndexingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminTestController {

    private final IndexingService indexingService;

    @GetMapping("/admin/test/get")
    public ResponseEntity<Long> getTime(HttpServletRequest request){
        long startTime = System.currentTimeMillis();

        indexingService.findMemberId(1);


        long endTime = System.currentTimeMillis();


        HttpHeaders header = new HttpHeaders();
        return new ResponseEntity<>((endTime-startTime),header,HttpStatus.OK);
    }

    @GetMapping("admin/test/add")
    public ResponseEntity<Long> getTime2(HttpServletRequest request){
        long startTime = System.currentTimeMillis();

        indexingService.AddIndexing();


        long endTime = System.currentTimeMillis();


        HttpHeaders header = new HttpHeaders();
        return new ResponseEntity<>((endTime-startTime)/1000,header,HttpStatus.OK);
    }
}
