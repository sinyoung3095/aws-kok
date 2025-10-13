const myPageLayout = (() => {
    const showPosts=(posts)=>{
        const container=document.getElementById("my-posts-wrap");
        if(!container) return;

        if (!Array.isArray(posts) || posts.length === 0){
            container.innerHTML=`<div className="post-2">
    <div className="post-3">
        <div className="post-13" style="padding-right: 0;padding-left: 0; justify-content: center; display: flex;">
            <div className="post-14" style="margin: 0 auto;">
                <div className="post-15">
                    <div className="post-8">
                        <span>내 게시물이 없습니다.</span>
                    </div>
                </div>
                <div className="post-9" style="margin: 0 auto;">
                    <p className="post-10">게시물을 작성해 주세요.</p>
                </div>
            </div>
            <div className="post-17">
                <div className="post-18">
                    <div className="post-19">
                        <!-- 작성 버튼 -->
                        <div className="content-14" style="width: 200px; margin: 0 auto;">
                            <button className="set content-15" type="button">
                                <span className="content-16">작성하러 가기</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>`;
            return;
        }
        let html=``;

        for(const post of posts){

            html+=`<div className="post-2">
    <!-- 첫게시글 -->
    <div className="post-3">
        <div className="post-4">
            <div>
                <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1"
                     src="/images/mypage/default.webp"
                     style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;"/>
            </div>
            <div className="post-5">
                <div className="post-6">
                    <div className="post-7">
                        <div>
                            <p className="post-8">${post.userName||''}</p>
                        </div>
                    </div>
                </div>
                <div className="post-9">
                    <p className="post-10">${post.jobName||''}</p>
                    <p className="post-10">${post.relativeDate||''}</p>
                </div>
            </div>
            <!-- 삭제 버튼 -->
            <div className="remove">
                <button className="post-23" style="width: 50px; color: black; font-size: 15px; font-weight: 500;">
                    <span
                        className="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">삭제</span>
                </button>
            </div>
        </div>
        <div className="post-13">
            <div className="post-14">
                <div className="post-15">
                    <div className="post-16">
                        <span>${post.postContent||''}</span>
                    </div>
                </div>
            </div>
            <!-- 이미지파일 -->
            <div className="file">
                <div className="file-1">
                    <div className="file-2">
                        <!-- 이미모음 -->
                        <div className="file-9">
                            <!-- 이미지들 -->
                            <div className="file-10">
                                <img className="img" alt="첨부 이미지 1" draggable="false"
                                     src="/images/mypage/post-image-1.png"
                                     style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
                            </div>
                            <div className="file-10">
                                <img className="img" alt="첨부 이미지 1" draggable="false"
                                     src="/images/mypage/post-image-2.png"
                                     style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
                            </div>
                            <div className="file-10">
                                <img className="img" alt="첨부 이미지 1" draggable="false"
                                     src="/images/mypage/post-image-3.png"
                                     style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="post-17">
                <div className="post-18">
                    <div className="post-19">
                        <!-- 좋아요 -->
                        <button className="post-20">
                            <div className="post-21">
                                <svg className="heart" viewBox="0 0 24 24" fill="red" stroke="red" height="20"
                                     role="img" width="20">
                                    <path
                                        d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p className="post-22">0</p>
                            </div>
                        </button>
                        <!-- 댓글 -->
                        <button className="post-20 reply-btn">
                            <div className="post-21">
                                <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
                                     fill="currentColor" height="20" role="img" width="20">
                                    <path clip-rule="evenodd"
                                          d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z"
                                          fill-rule="evenodd"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p className="post-22">0</p>
                            </div>
                        </button>
                        <!-- 수정 -->
                        <button className="post-20 popup-trigger" data-target="#post-write-popup">
                            <div className="post-21">
                                <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
                                     fill="currentColor" height="20" role="img" width="20">
                                    <path clip-rule="evenodd"
                                          d="M2.2 5A2.8 2.8 0 0 1 5 2.2h7a.8.8 0 0 1 0 1.6H5A1.2 1.2 0 0 0 3.8 5v14A1.2 1.2 0 0 0 5 20.2h14a1.2 1.2 0 0 0 1.2-1.2v-6a.8.8 0 1 1 1.6 0v6a2.8 2.8 0 0 1-2.8 2.8H5A2.8 2.8 0 0 1 2.2 19zm14.527-1.858a1.8 1.8 0 0 1 2.546 0l1.585 1.585a1.8 1.8 0 0 1 0 2.546l-8 8a1.8 1.8 0 0 1-1.272.527H10A1.8 1.8 0 0 1 8.2 14v-1.586c0-.477.19-.935.527-1.272zm1.414 1.131a.2.2 0 0 0-.282 0L16.13 6 18 7.87l1.727-1.727a.2.2 0 0 0 0-.283zM16.87 9 15 7.132l-5.141 5.141a.2.2 0 0 0-.059.141V14c0 .11.09.2.2.2h1.586a.2.2 0 0 0 .141-.058z"
                                          fill-rule="evenodd"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p className="post-22">수정</p>
                            </div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div className="post-3">
        <div className="post-4">
            <div>
                <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1"
                     src="/images/default.webp"
                     style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;"/>
            </div>
            <div className="post-5">
                <div className="post-6">
                    <div className="post-7">
                        <div>
                            <p className="post-8">지원자</p>
                        </div>
                    </div>
                </div>
                <div className="post-9">
                    <p className="post-10">SW개발자</p>
                    <p className="post-10">11분 전</p>
                </div>
            </div>
            <!-- 삭제 버튼 -->
            <div className="remove">
                <button className="post-23" style="width: 50px; color: black; font-size: 15px; font-weight: 500;">
                    <span
                        className="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">삭제</span>
                </button>
            </div>
        </div>
        <div className="post-13">
            <div className="post-14">
                <div className="post-15">
                    <div className="post-16">
                        <span>테스트용 글 확인</span>
                    </div>
                </div>
            </div>
            <div className="post-17">
                <div className="post-18">
                    <div className="post-19">
                        <!-- 좋아요 -->
                        <button className="post-20">
                            <div className="post-21">
                                <svg className="heart" viewBox="0 0 24 24" fill="red" stroke="red" height="20"
                                     role="img" width="20">
                                    <path
                                        d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p className="post-22">0</p>
                            </div>
                        </button>
                        <!-- 댓글 -->
                        <button className="post-20 reply-btn">
                            <div className="post-21">
                                <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
                                     fill="currentColor" height="20" role="img" width="20">
                                    <path clip-rule="evenodd"
                                          d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z"
                                          fill-rule="evenodd"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p className="post-22">0</p>
                            </div>
                        </button>
                        <!-- 수정 -->
                        <button className="post-20 popup-trigger" data-target="#post-write-popup">
                            <div className="post-21">
                                <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
                                     fill="currentColor" height="20" role="img" width="20">
                                    <path clip-rule="evenodd"
                                          d="M2.2 5A2.8 2.8 0 0 1 5 2.2h7a.8.8 0 0 1 0 1.6H5A1.2 1.2 0 0 0 3.8 5v14A1.2 1.2 0 0 0 5 20.2h14a1.2 1.2 0 0 0 1.2-1.2v-6a.8.8 0 1 1 1.6 0v6a2.8 2.8 0 0 1-2.8 2.8H5A2.8 2.8 0 0 1 2.2 19zm14.527-1.858a1.8 1.8 0 0 1 2.546 0l1.585 1.585a1.8 1.8 0 0 1 0 2.546l-8 8a1.8 1.8 0 0 1-1.272.527H10A1.8 1.8 0 0 1 8.2 14v-1.586c0-.477.19-.935.527-1.272zm1.414 1.131a.2.2 0 0 0-.282 0L16.13 6 18 7.87l1.727-1.727a.2.2 0 0 0 0-.283zM16.87 9 15 7.132l-5.141 5.141a.2.2 0 0 0-.059.141V14c0 .11.09.2.2.2h1.586a.2.2 0 0 0 .141-.058z"
                                          fill-rule="evenodd"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p className="post-22">수정</p>
                            </div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 삭제 모달 -->
    <div className="del">
        <div className="del-1">
            <div className="del-2">
                <div className="del-3">
                    <div className="del-4">
                        <svg aria-label="icon" color="foregrounds.neutral.primary" fill="currentColor" height="40px"
                             role="img" width="40px">
                            <path clip-rule="evenodd"
                                  d="M8.284 6.2H4a.8.8 0 1 0 0 1.6h1.2V18A2.8 2.8 0 0 0 8 20.8h8a2.8 2.8 0 0 0 2.8-2.8V7.8H20a.8.8 0 0 0 0-1.6h-4.284a3.801 3.801 0 0 0-7.432 0m1.666 0h4.1a2.2 2.2 0 0 0-4.1 0m7.25 1.6H6.8V18A1.2 1.2 0 0 0 8 19.2h8a1.2 1.2 0 0 0 1.2-1.2z"
                                  fill-rule="evenodd"></path>
                        </svg>
                    </div>
                    <div className="del-5">
                        <p className="del-6">게시물을 삭제하시겠습니까?</p>
                        <div className="del-7">
                            <p className="del-8">삭제된 게시물은 다시 복구할 수 없으며, 게시물에서 발생한 다른 사용자의 좋아요, 댓글도 함께 삭제됩니다.</p>
                        </div>
                    </div>
                </div>
                <div className="del-9">
                    <button className="del-10"><span className="del-11">삭제하기</span></button>
                    <button className="del-12"><span className="del-11">닫기</span></button>
                </div>
            </div>
        </div>
    </div>
</div>`
        }
        container.innerHTML=html;
    }
    const showExperienceRequest = (request) => {
        const container = document.querySelector('.exp-request-container');
        if (!container) return;

        if (!Array.isArray(request) || request.length === 0) {
            container.innerHTML = '<p class="no-results">검색 결과가 없습니다.</p>';
            return;
        }

        let html = '';

        for (const req of request) {

            html += `
                <tr>
                                                    <td class="payment-3">
                                                        <p>${req.companyName || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${req.experienceNoticeTitle || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${req.requestExperienceStatus || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <div>
                                                            <button class="post-23 retract-triger">
                                                                <span class="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">지원 취소</span>
                                                            </button>
                                                        </div>
                                                    </td>
                                                </tr>
            `;
        }

        container.innerHTML = html;

        // applyFilters();
    };

    return {showExperienceRequest};
})();


// 게시글

// <div className="post-2">
//     <!-- 게시물이 없을 때 -->
//     <div className="post-3">
//         <div className="post-13" style="padding-right: 0;padding-left: 0; justify-content: center; display: flex;">
//             <div className="post-14" style="margin: 0 auto;">
//                 <div className="post-15">
//                     <div className="post-8">
//                         <span>내 게시물이 없습니다.</span>
//                     </div>
//                 </div>
//                 <div className="post-9" style="margin: 0 auto;">
//                     <p className="post-10">게시물을 작성해 주세요.</p>
//                 </div>
//             </div>
//             <div className="post-17">
//                 <div className="post-18">
//                     <div className="post-19">
//                         <!-- 작성 버튼 -->
//                         <div className="content-14" style="width: 200px; margin: 0 auto;">
//                             <button className="set content-15" type="button">
//                                 <span className="content-16">작성하러 가기</span>
//                             </button>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     </div>
//     <!-- 첫게시글 -->
//     <div className="post-3">
//         <div className="post-4">
//             <div>
//                 <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1"
//                      src="/images/mypage/default.webp"
//                      style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;"/>
//             </div>
//             <div className="post-5">
//                 <div className="post-6">
//                     <div className="post-7">
//                         <div>
//                             <p className="post-8">지원자</p>
//                         </div>
//                     </div>
//                 </div>
//                 <div className="post-9">
//                     <p className="post-10">SW개발자</p>
//                     <p className="post-10">11분 전</p>
//                 </div>
//             </div>
//             <!-- 삭제 버튼 -->
//             <div className="remove">
//                 <button className="post-23" style="width: 50px; color: black; font-size: 15px; font-weight: 500;">
//                     <span
//                         className="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">삭제</span>
//                 </button>
//             </div>
//         </div>
//         <div className="post-13">
//             <div className="post-14">
//                 <div className="post-15">
//                     <div className="post-16">
//                         <span>테스트용 글 확인</span>
//                     </div>
//                 </div>
//             </div>
//             <!-- 이미지파일 -->
//             <div className="file">
//                 <div className="file-1">
//                     <div className="file-2">
//                         <!-- 이미모음 -->
//                         <div className="file-9">
//                             <!-- 이미지들 -->
//                             <div className="file-10">
//                                 <img className="img" alt="첨부 이미지 1" draggable="false"
//                                      src="/images/mypage/post-image-1.png"
//                                      style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
//                             </div>
//                             <div className="file-10">
//                                 <img className="img" alt="첨부 이미지 1" draggable="false"
//                                      src="/images/mypage/post-image-2.png"
//                                      style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
//                             </div>
//                             <div className="file-10">
//                                 <img className="img" alt="첨부 이미지 1" draggable="false"
//                                      src="/images/mypage/post-image-3.png"
//                                      style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//             <div className="post-17">
//                 <div className="post-18">
//                     <div className="post-19">
//                         <!-- 좋아요 -->
//                         <button className="post-20">
//                             <div className="post-21">
//                                 <svg className="heart" viewBox="0 0 24 24" fill="red" stroke="red" height="20"
//                                      role="img" width="20">
//                                     <path
//                                         d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
//                                 </svg>
//                             </div>
//                             <div style="margin-left: 5px;">
//                                 <p className="post-22">0</p>
//                             </div>
//                         </button>
//                         <!-- 댓글 -->
//                         <button className="post-20 reply-btn">
//                             <div className="post-21">
//                                 <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
//                                      fill="currentColor" height="20" role="img" width="20">
//                                     <path clip-rule="evenodd"
//                                           d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z"
//                                           fill-rule="evenodd"></path>
//                                 </svg>
//                             </div>
//                             <div style="margin-left: 5px;">
//                                 <p className="post-22">0</p>
//                             </div>
//                         </button>
//                         <!-- 수정 -->
//                         <button className="post-20 popup-trigger" data-target="#post-write-popup">
//                             <div className="post-21">
//                                 <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
//                                      fill="currentColor" height="20" role="img" width="20">
//                                     <path clip-rule="evenodd"
//                                           d="M2.2 5A2.8 2.8 0 0 1 5 2.2h7a.8.8 0 0 1 0 1.6H5A1.2 1.2 0 0 0 3.8 5v14A1.2 1.2 0 0 0 5 20.2h14a1.2 1.2 0 0 0 1.2-1.2v-6a.8.8 0 1 1 1.6 0v6a2.8 2.8 0 0 1-2.8 2.8H5A2.8 2.8 0 0 1 2.2 19zm14.527-1.858a1.8 1.8 0 0 1 2.546 0l1.585 1.585a1.8 1.8 0 0 1 0 2.546l-8 8a1.8 1.8 0 0 1-1.272.527H10A1.8 1.8 0 0 1 8.2 14v-1.586c0-.477.19-.935.527-1.272zm1.414 1.131a.2.2 0 0 0-.282 0L16.13 6 18 7.87l1.727-1.727a.2.2 0 0 0 0-.283zM16.87 9 15 7.132l-5.141 5.141a.2.2 0 0 0-.059.141V14c0 .11.09.2.2.2h1.586a.2.2 0 0 0 .141-.058z"
//                                           fill-rule="evenodd"></path>
//                                 </svg>
//                             </div>
//                             <div style="margin-left: 5px;">
//                                 <p className="post-22">수정</p>
//                             </div>
//                         </button>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     </div>
//     <div className="post-3">
//         <div className="post-4">
//             <div>
//                 <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1"
//                      src="/images/default.webp"
//                      style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;"/>
//             </div>
//             <div className="post-5">
//                 <div className="post-6">
//                     <div className="post-7">
//                         <div>
//                             <p className="post-8">지원자</p>
//                         </div>
//                     </div>
//                 </div>
//                 <div className="post-9">
//                     <p className="post-10">SW개발자</p>
//                     <p className="post-10">11분 전</p>
//                 </div>
//             </div>
//             <!-- 삭제 버튼 -->
//             <div className="remove">
//                 <button className="post-23" style="width: 50px; color: black; font-size: 15px; font-weight: 500;">
//                     <span
//                         className="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">삭제</span>
//                 </button>
//             </div>
//         </div>
//         <div className="post-13">
//             <div className="post-14">
//                 <div className="post-15">
//                     <div className="post-16">
//                         <span>테스트용 글 확인</span>
//                     </div>
//                 </div>
//             </div>
//             <div className="post-17">
//                 <div className="post-18">
//                     <div className="post-19">
//                         <!-- 좋아요 -->
//                         <button className="post-20">
//                             <div className="post-21">
//                                 <svg className="heart" viewBox="0 0 24 24" fill="red" stroke="red" height="20"
//                                      role="img" width="20">
//                                     <path
//                                         d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
//                                 </svg>
//                             </div>
//                             <div style="margin-left: 5px;">
//                                 <p className="post-22">0</p>
//                             </div>
//                         </button>
//                         <!-- 댓글 -->
//                         <button className="post-20 reply-btn">
//                             <div className="post-21">
//                                 <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
//                                      fill="currentColor" height="20" role="img" width="20">
//                                     <path clip-rule="evenodd"
//                                           d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z"
//                                           fill-rule="evenodd"></path>
//                                 </svg>
//                             </div>
//                             <div style="margin-left: 5px;">
//                                 <p className="post-22">0</p>
//                             </div>
//                         </button>
//                         <!-- 수정 -->
//                         <button className="post-20 popup-trigger" data-target="#post-write-popup">
//                             <div className="post-21">
//                                 <svg className="icons" aria-label="icon" color="foregrounds.neutral.primary"
//                                      fill="currentColor" height="20" role="img" width="20">
//                                     <path clip-rule="evenodd"
//                                           d="M2.2 5A2.8 2.8 0 0 1 5 2.2h7a.8.8 0 0 1 0 1.6H5A1.2 1.2 0 0 0 3.8 5v14A1.2 1.2 0 0 0 5 20.2h14a1.2 1.2 0 0 0 1.2-1.2v-6a.8.8 0 1 1 1.6 0v6a2.8 2.8 0 0 1-2.8 2.8H5A2.8 2.8 0 0 1 2.2 19zm14.527-1.858a1.8 1.8 0 0 1 2.546 0l1.585 1.585a1.8 1.8 0 0 1 0 2.546l-8 8a1.8 1.8 0 0 1-1.272.527H10A1.8 1.8 0 0 1 8.2 14v-1.586c0-.477.19-.935.527-1.272zm1.414 1.131a.2.2 0 0 0-.282 0L16.13 6 18 7.87l1.727-1.727a.2.2 0 0 0 0-.283zM16.87 9 15 7.132l-5.141 5.141a.2.2 0 0 0-.059.141V14c0 .11.09.2.2.2h1.586a.2.2 0 0 0 .141-.058z"
//                                           fill-rule="evenodd"></path>
//                                 </svg>
//                             </div>
//                             <div style="margin-left: 5px;">
//                                 <p className="post-22">수정</p>
//                             </div>
//                         </button>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     </div>
//     <!-- 삭제 모달 -->
//     <div className="del">
//         <div className="del-1">
//             <div className="del-2">
//                 <div className="del-3">
//                     <div className="del-4">
//                         <svg aria-label="icon" color="foregrounds.neutral.primary" fill="currentColor" height="40px"
//                              role="img" width="40px">
//                             <path clip-rule="evenodd"
//                                   d="M8.284 6.2H4a.8.8 0 1 0 0 1.6h1.2V18A2.8 2.8 0 0 0 8 20.8h8a2.8 2.8 0 0 0 2.8-2.8V7.8H20a.8.8 0 0 0 0-1.6h-4.284a3.801 3.801 0 0 0-7.432 0m1.666 0h4.1a2.2 2.2 0 0 0-4.1 0m7.25 1.6H6.8V18A1.2 1.2 0 0 0 8 19.2h8a1.2 1.2 0 0 0 1.2-1.2z"
//                                   fill-rule="evenodd"></path>
//                         </svg>
//                     </div>
//                     <div className="del-5">
//                         <p className="del-6">게시물을 삭제하시겠습니까?</p>
//                         <div className="del-7">
//                             <p className="del-8">삭제된 게시물은 다시 복구할 수 없으며, 게시물에서 발생한 다른 사용자의 좋아요, 댓글도 함께 삭제됩니다.</p>
//                         </div>
//                     </div>
//                 </div>
//                 <div className="del-9">
//                     <button className="del-10"><span className="del-11">삭제하기</span></button>
//                     <button className="del-12"><span className="del-11">닫기</span></button>
//                 </div>
//             </div>
//         </div>
//     </div>
// </div>


// 지원내역


//
// <tr>
//     <td class="payment-3">
//         <p>코리아IT</p>
//     </td>
//     <td class="payment-3">
//         <p>SW 개발 백엔드 체험</p>
//     </td>
//     <td class="payment-3">
//         <p>합격</p>
//     </td>
//     <td class="payment-3">
//         <p>-</p>
//     </td>
// </tr>
// <tr>
//     <td class="payment-3">
//         <p>코리아IT</p>
//     </td>
//     <td class="payment-3">
//         <p>SW 개발 백엔드 체험</p>
//     </td>
//     <td class="payment-3">
//         <p>불합격</p>
//     </td>
//     <td class="payment-3">
//         <p>환불</p>
//     </td>
// </tr>