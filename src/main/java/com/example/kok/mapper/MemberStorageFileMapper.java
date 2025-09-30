package com.example.kok.mapper;

import com.example.kok.dto.MemberStorageFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberStorageFileMapper {
//    보관함 파일 넣기
    public void insertStorageFile(MemberStorageFileDTO memberStorageFileDTO);
}
