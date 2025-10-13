package com.example.kok.repository;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestInternDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.mapper.MemberMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void saveMember(MemberVO memberVO) {
        log.info("memberVO={}", memberVO);
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

//    아이디로 지원한 체험 공고 조회
    public List<RequestExperienceDTO> findExperienceByMemberId(Long memberId) {
        return memberMapper.selectExperienceByMemberId(memberId);
    }

    //    아이디로 지원한 인턴 공고 조회
    public List<RequestInternDTO> findInternByMemberId(Long memberId) {
        return memberMapper.selectInternByMemberId(memberId);
    }
//    전화번호로 회원 sns이메일 조회
    public List<MemberDTO> findLink(String userPhone){
        return memberMapper.selectLinkBYPhone(userPhone);
    }
}
