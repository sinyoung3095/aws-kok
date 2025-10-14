package com.example.kok.repository;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.mapper.FileMapper;
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
    private final FileMapper fileMapper;

    public void saveMember(MemberVO memberVO) {
        log.info("memberVO={}", memberVO);
        memberMapper.insertMember(memberVO);
    }

//    프로필 사진 삭제
    public void deleteProfile(Long id){
        memberMapper.deleteProfile(id);
    }

//    파일 등록
    public void saveFile(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

//    프로필 파일 등록
    public void saveProfileFile(UserProfileFileDTO userProfileFileDTO) {
        memberMapper.insertProfileFile(userProfileFileDTO);
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
    //    아이디로 게시글 조회
    public List<PostDTO> findPostsByMemberId(Long memberId) {
        return memberMapper.selectPostsByMemberId(memberId);
    }

//    아이디로 직군 조회
    public String findJobCategoryByMemberId(Long memberId) {
        return memberMapper.selectJobCategoryByMemberId(memberId);
    }
    //    전화번호로 회원 sns이메일 조회
    public List<MemberDTO> findLink(String userPhone){
        return memberMapper.selectLinkBYPhone(userPhone);
    }

//    id로 결제내역 조회
    public List<PaymentDTO> findPaymentByMemberId(Long memberId) {
        return memberMapper.selectPaymentByMemberId(memberId);
    }

//    id로 저장 체험 조회
    public List<ExperienceNoticeDTO> findSavedExpByMemberId(Long memberId) {
        return memberMapper.selectSavedExpByMemberId(memberId);
    }

//    id로 저장 인턴 조회
    public List<InternNoticeDTO> findSavedInternNoticeByMemberId(Long memberId) {
        return memberMapper.selectSavedIntByMemberId(memberId);
    }

//    지원 id로 지원 취소
//    체험
    public void deleteExperienceRequest(Long id){
        memberMapper.updateExpReq(id);
    }

//    인턴
    public void deleteInternRequest(Long id){
        memberMapper.updateIntReq(id);
    }


//    소개 수정
    public void updateInfo(Long memberId, String info) {
        memberMapper.updateProfileInfo(memberId, info);
    }

//    이름 수정
    public void updateName(Long id, String name) {
        memberMapper.updateProfileName(id, name);
    }

//    직군 변경
    public void updateJob(Long id, String job) {
        memberMapper.updateProfileCategory(id, job);
    }

//    직군 추가
    public void plusJob(Long id, String job){
        memberMapper.insertProfileJob(id, job);
    }
}
