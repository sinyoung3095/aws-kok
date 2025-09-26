package com.example.kok.repository;

import com.example.kok.domain.MemberVO;
import com.example.kok.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void saveMember(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }
}
