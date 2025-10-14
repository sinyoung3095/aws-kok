package com.example.kok.service;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.UserProfileFileDTO;
import com.example.kok.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final FileDAO fileDAO;
    private final S3Service s3Service;
    @Override
    public FileDTO findProfileById(Long id) {
        FileDTO file = fileDAO.findFileById(id);
        if(file!=null){
            String preSignedUrl = s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(10));
            file.setFilePath(preSignedUrl);
        }

        return file;
    }
}
