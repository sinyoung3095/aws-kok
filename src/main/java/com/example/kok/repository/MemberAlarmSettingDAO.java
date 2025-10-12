package com.example.kok.repository;

import com.example.kok.dto.MemberAlarmSettingDTO;
import com.example.kok.mapper.MemberAlarmSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberAlarmSettingDAO {
    private final MemberAlarmSettingMapper memberAlarmSettingMapper;

    public void save(Long memberId){
        memberAlarmSettingMapper.insertByMemberId(memberId);
    };

//    멤버id로 알람id 조회
    public long findByMemberId(Long memberId){
        return memberAlarmSettingMapper.selectByMemberId(memberId);
    }
//    멤버 id로 전체 알람 조회
    public MemberAlarmSettingDTO findAllByMemberId(Long memberId){
        return memberAlarmSettingMapper.selectAllByMemberId(memberId);
    }
    public void updateByKeywordToActive(Long id, String keyword){
         memberAlarmSettingMapper.updateByKeywordToActive(id,keyword);
    }
    public void updateByKeywordToInactive(Long id, String keyword){
        memberAlarmSettingMapper.updateByKeywordToInactive(id,keyword);

    }


}
