package com.example.kok.repository;

import com.example.kok.mapper.MemberAlarmSettingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberAlarmSettingDAO {
    private final MemberAlarmSettingMapper memberAlarmSettingMapper;

    public void save(Long memberId){
        memberAlarmSettingMapper.insertByMemberId(memberId);
    };

}
