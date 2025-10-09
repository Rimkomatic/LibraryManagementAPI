package com.rik.Library.services;

import com.rik.Library.dto.Member.MemberRegistrationRequest;
import com.rik.Library.dto.Member.MemberUpdateRequest;
import com.rik.Library.entity.Author;
import com.rik.Library.entity.Member;
import com.rik.Library.enums.Role;
import com.rik.Library.repository.MemberRepository;
import com.rik.Library.util.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository memberRepository) {
        this.repository = memberRepository;
    }

    public Member addMember(MemberRegistrationRequest member){

        Member newMember = new Member();
        
        long id;

        do {
            id = IdGenerator.generateSevenDigitId();
        } while (repository.existsById(id));

        newMember.setId(id);

        newMember.setName(member.name());
        newMember.setEmail(member.email());
        newMember.setPassword(member.password());

        newMember.setRole(Role.CUSTOMER);


        return repository.save(newMember);
    }

    public List<Member> fetchAllMembers()
    {
        return  repository.findAll();
    }

    public Member updateMemberById(MemberUpdateRequest member){
        Member oldMember = this.repository.findById(member.id())
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + member.id()));

        oldMember.setName(member.name());
        oldMember.setEmail(member.email());
        oldMember.setPassword(member.password());

        return repository.save(oldMember);
    }

    public Member getUserById(long id)
    {
        Member member = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        return member;
    }

}
