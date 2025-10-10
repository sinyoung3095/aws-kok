package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public void joinMember(MemberVO memberVO);

//    보관함에 이력서 넣기
    public void putFileAtStorage(List<MultipartFile> file, Long memberId);

//    이력서들 조회
    public List<FileDTO> findFilesByMemberId(Long memberId);

//    모든 회원 조회
    public AdminMemberCriteriaDTO findUserMembers(int page, String keyword);

//    회원별 모든 이력 조회
    public Optional<UserMemberDTO> findMembersByMemberId(Long memberId);

//    지원한 체험 공고 조회
    public List<RequestExperienceDTO> findRequestExperienceByMemberId(Long memberId);

//    지원한 인턴 공고 조회
    public List<RequestInternDTO> findRequestInternByMemberId(Long memberId);
}
