package com.rik.Library.controller;


import com.rik.Library.dto.Member.MemberRegistrationRequest;
import com.rik.Library.dto.Member.MemberUpdateRequest;
import com.rik.Library.entity.Member;
import com.rik.Library.services.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/member")
public class MemberController {


    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @PostMapping("/register")
    public Member addMember(@RequestBody MemberRegistrationRequest member){
        return memberService.addMember(member);
    }

    @GetMapping("/members")
    public List<Member> getAllMembers()
    {
        return memberService.fetchAllMembers();
    }


    @PutMapping("/update")
    public Member updateMember(@RequestBody MemberUpdateRequest member)
    {
        return memberService.updateMemberById(member);
    }

    @GetMapping("/{id}")
    public Member getOneMember(@PathVariable long id)
    {
        return memberService.getUserById(id);
    }


}
