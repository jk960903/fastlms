package com.zerobase.fastlms.course.entity;

import com.zerobase.fastlms.admin.course.entity.Course;
import com.zerobase.fastlms.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TakeCourse implements TakeCourseCode{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;

    String userId;

    long payPrice; // 결제 금액

    String status; //상태 (수강 신청 ,결제 완료 , 수강 취소)

    LocalDateTime regdate;


    @OneToOne
    @JoinColumn(name="courseId",referencedColumnName = "id",insertable = false , updatable = false)
    Course course;

    @OneToOne
    @JoinColumn(name="userId" , referencedColumnName = "userId",insertable = false, updatable = false )
    Member member;
}
