package com.example.kok.mapper;

import com.example.kok.dto.MemberAlarmSettingDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberAlarmSettingMapper {
    public void insertByMemberId(Long memberId);

//    멤버id로 알람id 조회
    public long selectByMemberId(Long memberId);
    public MemberAlarmSettingDTO selectAllByMemberId(Long memberId);
    public void updateByKeywordToActive(Long id, String keyword);
    public void updateByKeywordToInactive(Long id, String keyword);
}
