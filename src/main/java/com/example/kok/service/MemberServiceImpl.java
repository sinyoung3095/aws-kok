package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private final CommunityPostFileDAO communityPostFileDAO;
    private final CommunityLikeDAO communityLikeDAO;
    private final CommunityCommentService communityCommentService;

    @Override
    public void joinMember(MemberVO memberVO) {
        memberDAO.saveMember(memberVO);
    }

    @Override
    public void deleteProfile(Long id) {
        memberDAO.deleteProfile(id);
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

    @Override
    public List<RequestExperienceDTO> findRequestExperienceByMemberId(Long memberId) {
        List<RequestExperienceDTO> experiences=memberDAO.findExperienceByMemberId(memberId);
        return experiences;
    }

    @Override
    public List<RequestInternDTO> findRequestInternByMemberId(Long memberId) {
        List<RequestInternDTO> requestInterns=memberDAO.findInternByMemberId(memberId);
        return requestInterns;
    }

    @Override
    public List<PostDTO> findPostsByMemberId(Long memberId) {
        List<PostDTO> posts=memberDAO.findPostsByMemberId(memberId);
        posts.forEach(post -> {
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDateTime()));
            post.setCommentsCount(communityCommentService.commentsCountByPostId(post.getId()));
            List<PostFileDTO> postFiles = communityPostFileDAO.findAllByPostId(post.getId());
            postFiles.forEach(postFile -> {
                postFile.setPostFilePath(s3Service.getPreSignedUrl(postFile.getPostFilePath(), Duration.ofMinutes(10)));
            });
            post.setPostFiles(postFiles);

            post.setLikesCount(communityLikeDAO.getPostLikeCount(post.getId()));
            if (memberId != null) {
                post.setOwner(memberId.equals(post.getMemberId()));
                post.setLiked(communityLikeDAO.isexistLike(post.getId(), memberId));
            } else {
                post.setOwner(false);
                post.setLiked(false);
            }
        });
        return posts;
    }

    @Override
    public List<PaymentDTO> findPaymentByMemberId(Long memberId) {
        return memberDAO.findPaymentByMemberId(memberId);
    }

    @Override
    public List<ExperienceNoticeDTO> findExperienceNoticeByMemberId(Long memberId) {
        return memberDAO.findSavedExpByMemberId(memberId);
    }

    @Override
    public List<InternNoticeDTO> findInternNoticeByMemberId(Long memberId) {
        return memberDAO.findSavedInternNoticeByMemberId(memberId);
    }

    @Override
    public void deleteRequestExperience(Long id) {
        memberDAO.deleteExperienceRequest(id);
    }

    @Override
    public void deleteRequestIntern(Long id) {
        memberDAO.deleteInternRequest(id);
    }

    @Override
    public void updateProfile(Long id, UserMemberDTO dto, MultipartFile profile) {
        String JobCate=memberDAO.findJobCategoryByMemberId(id);
        memberDAO.updateInfo(id, dto.getMemberInfo());
        memberDAO.updateName(id, dto.getUserName());
        System.out.println("서비스 인포: " + dto.getMemberInfo());
        System.out.println("서비스 직군: " + dto.getJobName());
        if(JobCate.isEmpty()){
            memberDAO.plusJob(id, dto.getJobName());
        } else{
            memberDAO.updateJob(id, dto.getJobName());
        }


        if(profile != null) {
            memberDAO.deleteProfile(id);

            try {
                String s3Key = s3Service.uploadFile(profile, getPath());

                // 파일 DTO 생성
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileOriginName(profile.getOriginalFilename());
                fileDTO.setFileName(UUID.randomUUID().toString());
                fileDTO.setFilePath(s3Key);
                fileDTO.setFileSize(String.valueOf(profile.getSize()));
                fileDTO.setFileContentType(profile.getContentType());

                // tbl_file 등록
                memberDAO.saveFile(fileDTO);

                // userProfileFile 등록
                UserProfileFileDTO userProfileFileDTO = new UserProfileFileDTO();
                userProfileFileDTO.setFileId(fileDTO.getId());
                userProfileFileDTO.setId(dto.getId());

                memberDAO.saveProfileFile(userProfileFileDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        UserProfileFileDTO updatedProfile = getProfile(dto.getId());
    }

    @Override
    public UserProfileFileDTO getProfile(Long id) {
        return null;
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }
}
