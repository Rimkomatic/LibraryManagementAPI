package com.rik.Library.repository;

import com.rik.Library.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
}
