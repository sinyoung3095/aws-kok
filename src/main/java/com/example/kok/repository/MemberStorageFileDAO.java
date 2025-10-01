package com.example.kok.repository;

import com.example.kok.dto.MemberStorageFileDTO;
import com.example.kok.mapper.MemberStorageFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberStorageFileDAO {
    private final MemberStorageFileMapper memberStorageFileMapper;

    private void saveStorageFile(MemberStorageFileDTO memberStorageFileDTO) {
        memberStorageFileMapper.insertStorageFile(memberStorageFileDTO);
    }
}
