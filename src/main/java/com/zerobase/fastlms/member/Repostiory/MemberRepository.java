package com.zerobase.fastlms.member.Repostiory;

import com.zerobase.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {

    Optional<Member> findByEmailAuthKey(String emailAuthKey);

    Optional<Member> findByUserIdAndUserName(String userId,String userName);

    Optional<Member> findByResetPasswordKey(String resetPasswordKey);

    Optional<List<Member>> findByRegdateLessThan(LocalDateTime regdate);
}
