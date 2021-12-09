package com.zerobase.fastlms.member.Repostiory;

import com.zerobase.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {

    Optional<Member> findByEmailAuthKey(String emailAuthKey);
}
