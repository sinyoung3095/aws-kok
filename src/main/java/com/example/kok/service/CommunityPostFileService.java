package com.example.kok.service;

import com.example.kok.dto.PostFileDTO;
import com.example.kok.repository.CommunityPostFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityPostFileService {
    private final CommunityPostFileDAO communityPostFileDAO;

//    조회
    public Optional<PostFileDTO> getPostFile(Long id) {
        return communityPostFileDAO.findById(id);
    }
}
