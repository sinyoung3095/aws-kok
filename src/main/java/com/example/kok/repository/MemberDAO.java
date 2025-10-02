package com.example.kok.repository;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.mapper.MemberMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void saveMember(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }

//    회원 조회
    public List<UserMemberDTO> selectMembers(Criteria criteria, String keyword) {
        return memberMapper.selectMembers(criteria, keyword);
    }

    public int countMembers(String keyword) {
        return memberMapper.selectCount(keyword);
    }

    //    아이디로 회원 조회
    public Optional<UserMemberDTO> selectMember(Long memberId) {
        return memberMapper.selectMember(memberId);
    }
}
