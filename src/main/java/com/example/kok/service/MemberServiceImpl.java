package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberStorageFileDAO memberStorageFileDAO;
    private final S3Service s3Service;
    private final FileDAO fileDAO;
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestInternDAO requestInternDAO;
    private final CommunityPostDAO  communityPostDAO;

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
    public List<UserMemberDTO> findUserMembers(int page, String keyword) {
        Criteria criteria = new Criteria(page, 10);
        return memberDAO.selectMembers(criteria, keyword);
    }

//    회원 아이디로 조회
    @Override
    public Optional<UserMemberDTO> findMembersByMemberId(Long memberId) {
        return memberDAO.selectMember(memberId)
                .map(userMemberDTO -> {
                    List<RequestExperienceDTO> requestExperiences =
                            requestExperienceDAO.selectAllRequestById(memberId);
                    List<RequestInternDTO> requestInterns =
                            requestInternDAO.selectAllInternById(memberId);
                    List<PostDTO> posts =
                            communityPostDAO.findPostById(memberId);
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
