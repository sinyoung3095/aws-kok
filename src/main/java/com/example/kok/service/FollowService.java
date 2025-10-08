package com.example.kok.service;

public interface FollowService {
//    팔로우
    void follow(Long memberId, Long companyId);

//    팔로우 취소
    void unfollow(Long memberId, Long companyId);

//    팔로우 여부 확인
    boolean isFollowing(Long memberId, Long companyId);

//    기업 팔로워 수
    int countFollowers(Long companyId);
}
