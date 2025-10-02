package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import com.example.kok.repository.CommunityLikeDAO;
import com.example.kok.repository.CommunityPostDAO;
import com.example.kok.repository.CommunityPostFileDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityPostServiceImpl implements CommunityPostService {
    private final CommunityPostDAO communityPostDAO;
    private final CommunityPostFileDAO communityPostFileDAO;
    private final CommunityLikeDAO communityLikeDAO;
    private final S3Service s3Service;
    private final CommunityCommentService communityCommentService;

    @Override
    public void setPreSignedUrl(PostDTO postDTO) {
        List<PostFileDTO> postFiles = communityPostFileDAO.findAllByPostId(postDTO.getId());
        postFiles.forEach((postFile) -> {
            postFile.setPostFilePath(s3Service.getPreSignedUrl(postFile.getPostFilePath(), Duration.ofMinutes(5)));
        });

        postDTO.setPostFiles(postFiles);
    }

    @Override
    public PostsCriteriaDTO getList(int page, Long memberId) {
        Criteria criteria = new Criteria(page, communityPostDAO.findCountAll());
        List<PostDTO> posts = communityPostDAO.findAll(criteria);

        posts.forEach(post -> {
            post.setRelativeDate(DateUtils.toRelativeTime(post.getCreatedDateTime()));
            post.setCommentsCount(communityCommentService.commentsCountByPostId(post.getId()));
            List<PostFileDTO> postFiles = communityPostFileDAO.findAllByPostId(post.getId());
            postFiles.forEach(postFile -> {
                postFile.setPostFilePath(s3Service.getPreSignedUrl(postFile.getPostFilePath(), Duration.ofMinutes(5)));
            });
            post.setPostFiles(postFiles);

            post.setLikesCount(communityLikeDAO.getPostLikeCount(post.getId()));
            if (memberId != null) {
                post.setLiked(communityLikeDAO.isexistLike(post.getId(), memberId));
            } else {
                post.setLiked(false);
            }
        });

        criteria.setHasMore(criteria.getPage() < criteria.getRealEnd());
        if(criteria.isHasMore()){
            posts.remove(posts.size() - 1);
        }

        PostsCriteriaDTO postsCriteriaDTO = new PostsCriteriaDTO();
        postsCriteriaDTO.setPosts(posts);
        postsCriteriaDTO.setCriteria(criteria);
        return postsCriteriaDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "posts", key="'post_' + #id")
    public PostDTO getPost(Long id, Long memberId) {
        PostDTO postDTO = communityPostDAO.findById(id).orElseThrow(PostNotFoundException::new);
        postDTO.setRelativeDate(DateUtils.toRelativeTime(postDTO.getCreatedDateTime().split("\\.")[0]));
        postDTO.setCreatedDateTime(postDTO.getCreatedDateTime().split(" ")[0]);

        List<PostFileDTO> files = communityPostFileDAO.findAllByPostId(postDTO.getId());
        files.forEach(f -> {
            f.setPostFilePath(s3Service.getPreSignedUrl(f.getPostFilePath(), Duration.ofMinutes(5)));
        });
        postDTO.setPostFiles(files);

        postDTO.setLikesCount(communityLikeDAO.getPostLikeCount(postDTO.getId()));
        if (memberId != null) {
            postDTO.setLiked(communityLikeDAO.isexistLike(postDTO.getId(), memberId));
        } else {
            postDTO.setLiked(false);
        }

        return postDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(PostDTO postDTO, List<MultipartFile> multipartFiles) {
        communityPostDAO.save(postDTO);

        multipartFiles.forEach((multipartFile) -> {
            if(multipartFile.isEmpty()) return;

            try {
                String s3Key = s3Service.uploadFile(multipartFile, getPath());
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                fileDTO.setFileName(s3Key.substring(s3Key.lastIndexOf("/") + 1));
                fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                fileDTO.setFilePath(s3Key);
                fileDTO.setFileContentType(multipartFile.getContentType());

                communityPostFileDAO.saveFile(fileDTO);

                PostFileDTO postFileDTO = new PostFileDTO();
                postFileDTO.setFileId(fileDTO.getId());
                postFileDTO.setPostId(postDTO.getId());

                communityPostFileDAO.save(postFileDTO);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="posts", key = "'post_' + #id")
    public void delete(Long id) {
        List<PostFileDTO> postFiles = communityPostFileDAO.findAllByPostId(id);
        postFiles.forEach((postFile) -> {
            s3Service.deleteFile(postFile.getPostFilePath());
        });

        communityPostDAO.delete(id);
    }

    @Override
    public PostDTO update(PostDTO postDTO, Long[] deleteFilesIds, List<MultipartFile> multipartFiles) {
        communityPostDAO.update(toPostVO(postDTO));

        if(deleteFilesIds != null){
            Arrays.stream(deleteFilesIds).forEach((id) -> {
                PostFileDTO postFile =
                        communityPostFileDAO.findPostFilePathByPostFileId(id)
                                .orElseThrow(PostNotFoundException::new);
                s3Service.deleteFile(postFile.getPostFilePath());
                communityPostFileDAO.deleteById(id);
            });
        }

        multipartFiles.forEach((multipartFile) -> {
            if(multipartFile.getOriginalFilename().equals("")){
                return;
            }

            PostFileDTO postFileDTO = new PostFileDTO();
            try {
                String s3Key = s3Service.uploadFile(multipartFile, getPath());

                postFileDTO.setPostId(postDTO.getId());
                postFileDTO.setPostFileName(multipartFile.getOriginalFilename());
                postFileDTO.setPostFilePath(s3Key);
                postFileDTO.setPostFileSize(String.valueOf(multipartFile.getSize()));

                communityPostFileDAO.save(postFileDTO);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return postDTO;
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }

}
