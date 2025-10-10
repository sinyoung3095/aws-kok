package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberStorageFileDAO memberStorageFileDAO;
    private final S3Service s3Service;
    private final FileDAO fileDAO;
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestInternDAO requestInternDAO;
    private final CommunityPostDAO  communityPostDAO;
    private final UserMemberDTO userMemberDTO;
    private final MemberDTO memberDTO;
    private final FollowDAO followDAO;

    @Override
    public void joinMember(MemberVO memberVO) {
        memberDAO.saveMember(memberVO);
    }

    @Override
    public void putFileAtStorage(List<MultipartFile> files, Long memberId) {
        files.forEach(file -> {
            try {
                String s3Key=s3Service.uploadStorageFile(file, getPath());
                FileDTO fileDTO=new FileDTO();
                fileDTO.setFileOriginName(file.getOriginalFilename());
                fileDTO.setFileName(s3Key.substring(s3Key.lastIndexOf("/") + 1));
                fileDTO.setFileSize(String.valueOf(file.getSize()));
                fileDTO.setFilePath(s3Key);
                fileDTO.setFileContentType(file.getContentType());

                fileDAO.saveFile(fileDTO);
                Long fileId=fileDAO.findFileIdByName(fileDTO.getFileName());
                System.out.println(fileId);

                MemberStorageFileDTO memberStorageFileDTO=new MemberStorageFileDTO();
                memberStorageFileDTO.setMemberId(memberId);
                memberStorageFileDTO.setFileId(fileId);

                memberStorageFileDAO.saveStorageFile(memberStorageFileDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<FileDTO> findFilesByMemberId(Long memberId) {
        return memberStorageFileDAO.findFilesByMemberId(memberId);
    }

//    회원 전체조회
    @Override
    public AdminMemberCriteriaDTO findUserMembers(int page, String keyword) {

        AdminMemberCriteriaDTO  adminMemberCriteriaDTO = new AdminMemberCriteriaDTO();
        Criteria criteria = new Criteria(page, memberDAO.countMembers(keyword));

        List<UserMemberDTO> members = memberDAO.selectMembers(criteria, keyword);

        criteria.setHasMore(members.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        criteria.setHasMore(members.size() == criteria.getRowCount() + 1);
//        10개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            members.remove(members.size() - 1);
        }

        adminMemberCriteriaDTO.setUserMemberDTOList(members);
        adminMemberCriteriaDTO.setCriteria(criteria);
        adminMemberCriteriaDTO.setTotal(memberDAO.countMembers(keyword));


        return adminMemberCriteriaDTO;
    }

//    회원 아이디로 조회
    @Override
    @Cacheable(value = "member", key="'member_' + #memberId")
    public Optional<UserMemberDTO> findMembersByMemberId(Long memberId) {
        return memberDAO.selectMember(memberId)
                .map(userMemberDTO -> {
                    List<RequestExperienceDTO> requestExperiences =
                            requestExperienceDAO.selectAllRequestById(memberId);
                    List<RequestInternDTO> requestInterns =
                            requestInternDAO.selectAllInternById(memberId);
                    List<PostDTO> posts =
                            communityPostDAO.findPostById(memberId);

                    int postsCount = communityPostDAO.findPostsCountByMemberId(memberId);
                    userMemberDTO.setPostsCount(postsCount);

                    int followingCount = followDAO.selectFollowingCountByMemberId(memberId);
                    userMemberDTO.setFollowingCount(followingCount);

                    userMemberDTO.setRequestExperiences(requestExperiences);
                    userMemberDTO.setRequestInterns(requestInterns);
                    userMemberDTO.setPosts(posts);
                    return userMemberDTO;
                });
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }
}
