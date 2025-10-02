package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.MemberStorageFileDTO;
import com.example.kok.repository.FileDAO;
import com.example.kok.repository.MemberDAO;
import com.example.kok.repository.MemberStorageFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberStorageFileDAO memberStorageFileDAO;
    private final S3Service s3Service;
    private final FileDAO fileDAO;

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

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }
}
