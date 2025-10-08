package com.example.kok.repository;

import com.example.kok.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowDAO {
    private final FollowMapper followMapper;

//    팔로우
    public void insertFollow(Long memberId, Long companyId) {
        followMapper.insertFollow(memberId, companyId);
    }

//    팔로우 취소
    public void deleteFollow(Long memberId, Long companyId) {
        followMapper.deleteFollow(memberId, companyId);
    }

//    팔로우 여부 확인
    public boolean isFollowing(Long memberId, Long companyId) {
        return followMapper.isFollowing(memberId, companyId);
    }

//    기업 팔로워 수
    public int countFollowersByCompanyId(Long companyId) {
        return followMapper.countFollowersByCompanyId(companyId);
    }
}
