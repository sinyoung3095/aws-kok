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

//    멤버id로 알람id 조회
    public long findByMemberId(Long memberId){
        return memberAlarmSettingMapper.selectByMemberId(memberId);
    }

}
