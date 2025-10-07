//package com.example.kok.service;
//
//import com.example.kok.dto.PostDTO;
//import com.example.kok.dto.PostFileDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ConsoleAdServiceImpl implements ConsoleAdService {
//    private final ConsoleAdDAO postDAO;
//    private final ConsoleAdFileDAO postFileDAO;
//    private final S3Service s3Service;
//    private final PostFileDTO postFileDTO;
//
//    @Override
//    public void setPreSignedUrl(PostDTO postDTO) {
//        List<PostFileDTO> postFiles = postFileDAO.findAllByPostId(postDTO.getId());
//        postFiles.forEach((postFile) -> {
//            postFile.setPostFilePath(s3Service.getPreSignedUrl(postFile.getPostFilePath(), Duration.ofMinutes(5)));
//        });
//
//        postDTO.setPostFiles(postFiles);
//    }
//}
