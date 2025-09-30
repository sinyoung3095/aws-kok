package com.example.kok.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberAlarmSettingMapper {
    public void insertByMemberId(Long memberId);
}
