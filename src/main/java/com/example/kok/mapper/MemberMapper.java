package com.example.kok.mapper;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    public void insertMember(MemberVO memberVO);

//    회원 조회
    public List<UserMemberDTO> selectMembers(@Param("criteria") Criteria criteria,@Param("keyword") String keyword);

//    목록 개수 조회
    public int selectCount(String keyword);

//    아이디로 회원 조회
    public Optional<UserMemberDTO> selectMember(Long memberId);

//    아이디로 지원한 체험 공고 조회
    public List<RequestExperienceDTO> selectExperienceByMemberId(Long memberId);

//    아이디로 지원한 인턴 공고 조회
    public List<RequestInternDTO> selectInternByMemberId(Long memberId);

//    아이디로 게시글 조회
    public List<PostDTO> selectPostsByMemberId(Long memberId);
}
