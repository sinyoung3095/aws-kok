package com.example.kok.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberAlarmSettingMapper {
    public void insertByMemberId(Long memberId);

//    멤버id로 알람id 조회
    public long selectByMemberId(Long memberId);
}
