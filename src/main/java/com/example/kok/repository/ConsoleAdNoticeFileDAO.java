
package com.example.kok.repository;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.mapper.ConsoleAdNoticeFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleAdNoticeFileDAO {
    private final ConsoleAdNoticeFileMapper fileMapper;

    // 광고 ID로 파일 목록 조회
    public List<FileDTO> findAllByAdvertisementId(Long advertisementId) {
        return fileMapper.selectAdBackgroundFileByAdId(advertisementId);
    }

    // 파일 등록
    public void saveFile(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

    // 광고-파일 연결
    public void linkFileToAdvertisement(Long fileId, Long advertisementId) {
        fileMapper.insertAdBackgroundFile(fileId, advertisementId);
    }

    // 광고에 연결된 파일 전체 삭제
    public void deleteFilesByAdvertisementId(Long advertisementId) {
        fileMapper.deleteFilesByAdId(advertisementId);
    }

    // 파일 단독 삭제
    public void deleteFile(Long fileId) {
        fileMapper.deleteFileById(fileId);
    }

}
