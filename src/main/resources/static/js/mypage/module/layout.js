let commentcount;
const myPageLayout = (() => {
    const showPosts = (posts) => {
        const container = document.getElementById("my-posts-wrap");
        if (!container) return;

        if (!Array.isArray(posts) || posts.length === 0) {
            container.innerHTML = `<div class="post-2">
    <div class="post-3">
        <div class="post-13" style="padding-right: 0;padding-left: 0; justify-content: center; display: flex;">
            <div class="post-14" style="margin: 0 auto;">
                <div class="post-15">
                    <div class="post-8">
                        <span>내 게시물이 없습니다.</span>
                    </div>
                </div>
                <div class="post-9" style="margin: 0 auto;">
                    <p class="post-10">게시물을 작성해 주세요.</p>
                </div>
            </div>
            <div class="post-17">
                <div class="post-18">
                    <div class="post-19">
                        <!-- 작성 버튼 -->
                        <div class="content-14" style="width: 200px; margin: 0 auto;">
                            <button class="set content-15" type="button">
                                <span class="content-16">작성하러 가기</span>
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
        let html = ``;

        for (const post of posts) {

            html += `<div class="post-2">
    <!-- 첫게시글 -->
    <div class="post-3">
        <div class="post-4">
            <div>
                <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1"
                     src="${post.memberProfileUrl || '/images/main-page/image2.png'}"
                     style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;"/>
            </div>
            <div class="post-5">
                <div class="post-6">
                    <div class="post-7">
                        <div>
                            <p class="post-8">${post.userName || ''}</p>
                        </div>
                    </div>
                </div>
                <div class="post-9">
                    <p class="post-10">${post.jobName || ''}</p>
                    <p class="post-10">${post.relativeDate || ''}</p>
                </div>
            </div>
            <!-- 삭제 버튼 -->
<!--            <div class="remove">-->
<!--                <button class="post-23" style="width: 50px; color: black; font-size: 15px; font-weight: 500;">-->
<!--                    <span-->
<!--                        class="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">삭제</span>-->
<!--                </button>-->
<!--            </div>-->
        </div>
        <div class="post-13">
            <div class="post-14">
                <div class="post-15">
                    <div class="post-16">
                        <span>${post.postContent || ''}</span>
                    </div>
                </div>
            </div>`;
            // console.log(post.id);
            // console.log(post.postFiles);
            // console.log(Array.isArray(post.postFiles));
            if (post.postFiles && post.postFiles.length > 0) {
                html += `<!-- 이미지파일 -->
                    <div class="file">
                        <div class="file-1">
                            <div class="file-2">
                                <!-- 이미모음 -->
                                <div class="file-9">`;
                post.postFiles.forEach((file) => {
                    html += `<!-- 이미지들 -->
                                        <div class="file-10">
                                            <img class="img" alt="첨부 이미지 1" draggable="false"
                                                 src="${file.postFilePath}"
                                                 style="color: transparent; height: 320px; object-fit: cover; width: auto;"/>
                                        </div>`;
                    // console.log(file.postFilePath);
                });
                html += `</div>
                            </div>
                        </div>
                    </div>`;
            }
            html += `<div class="post-17">
                <div class="post-18">
                    <div class="post-19">
                        <!-- 좋아요 -->
<!--                        <button class="post-20" data-liked="${post.liked}">-->
<!--                            <div class="post-21">-->
<!--                                <svg class="heart" viewBox="0 0 24 24" fill="red" style="fill:${post.liked ? 'red' : 'white'}; stroke: red;" height="20"-->
<!--                                     role="img" width="20">-->
<!--                                    <path-->
<!--                                        d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>-->
<!--                                </svg>-->
<!--                            </div>-->
<!--                            <div style="margin-left: 5px;">-->
<!--                                <p class="post-22">${post.likesCount}</p>-->
<!--                            </div>-->
<!--                        </button>-->
                        <!-- 댓글 -->
                        <button class="post-20 reply-btn" data-post-id="${post.id}" >
                            <div class="post-21">
                                <svg class="icons" aria-label="icon" color="foregrounds.neutral.primary"
                                     fill="currentColor" height="20" role="img" width="20">
                                    <path clip-rule="evenodd"
                                          d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z"
                                          fill-rule="evenodd"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p class="post-22">${post.commentsCount}</p>
                            </div>
                        </button>
                        <!-- 수정 -->
<!--                        <button class="post-20 popup-trigger" data-target="#post-write-popup">-->
<!--                            <div class="post-21">-->
<!--                                <svg class="icons" aria-label="icon" color="foregrounds.neutral.primary"-->
<!--                                     fill="currentColor" height="20" role="img" width="20">-->
<!--                                    <path clip-rule="evenodd"-->
<!--                                          d="M2.2 5A2.8 2.8 0 0 1 5 2.2h7a.8.8 0 0 1 0 1.6H5A1.2 1.2 0 0 0 3.8 5v14A1.2 1.2 0 0 0 5 20.2h14a1.2 1.2 0 0 0 1.2-1.2v-6a.8.8 0 1 1 1.6 0v6a2.8 2.8 0 0 1-2.8 2.8H5A2.8 2.8 0 0 1 2.2 19zm14.527-1.858a1.8 1.8 0 0 1 2.546 0l1.585 1.585a1.8 1.8 0 0 1 0 2.546l-8 8a1.8 1.8 0 0 1-1.272.527H10A1.8 1.8 0 0 1 8.2 14v-1.586c0-.477.19-.935.527-1.272zm1.414 1.131a.2.2 0 0 0-.282 0L16.13 6 18 7.87l1.727-1.727a.2.2 0 0 0 0-.283zM16.87 9 15 7.132l-5.141 5.141a.2.2 0 0 0-.059.141V14c0 .11.09.2.2.2h1.586a.2.2 0 0 0 .141-.058z"-->
<!--                                          fill-rule="evenodd"></path>-->
<!--                                </svg>-->
<!--                            </div>-->
<!--                            <div style="margin-left: 5px;">-->
<!--                                <p class="post-22">수정</p>-->
<!--                            </div>-->
<!--                        </button>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 삭제 모달 -->
<!--    <div class="del del-post">-->
<!--        <div class="del-1">-->
<!--            <div class="del-2">-->
<!--                <div class="del-3">-->
<!--                    <div class="del-4">-->
<!--                        <svg aria-label="icon" color="foregrounds.neutral.primary" fill="currentColor" height="40px"-->
<!--                             role="img" width="40px">-->
<!--                            <path clip-rule="evenodd"-->
<!--                                  d="M8.284 6.2H4a.8.8 0 1 0 0 1.6h1.2V18A2.8 2.8 0 0 0 8 20.8h8a2.8 2.8 0 0 0 2.8-2.8V7.8H20a.8.8 0 0 0 0-1.6h-4.284a3.801 3.801 0 0 0-7.432 0m1.666 0h4.1a2.2 2.2 0 0 0-4.1 0m7.25 1.6H6.8V18A1.2 1.2 0 0 0 8 19.2h8a1.2 1.2 0 0 0 1.2-1.2z"-->
<!--                                  fill-rule="evenodd"></path>-->
<!--                        </svg>-->
<!--                    </div>-->
<!--                    <div class="del-5">-->
<!--                        <p class="del-6">게시물을 삭제하시겠습니까?</p>-->
<!--                        <div class="del-7">-->
<!--                            <p class="del-8">삭제된 게시물은 다시 복구할 수 없으며, 게시물에서 발생한 다른 사용자의 좋아요, 댓글도 함께 삭제됩니다.</p>-->
<!--                        </div>-->
<!--                    </div>-->
<!--                </div>-->
<!--                <div class="del-9">-->
<!--                    <button class="del-10 delete-list-post"><span class="del-11">삭제하기</span></button>-->
<!--                    <button class="del-12 delete-list-post-cancle"><span class="del-11">닫기</span></button>-->
<!--                </div>-->
<!--            </div>-->
<!--        </div>-->
<!--    </div>-->
</div>`;
        }
        container.innerHTML = html;
    }

    const showPostDetail=(post)=>{
        const leftContainer=document.querySelector(".reply-left");
        let leftHtml=``;
        leftHtml+=`<div id="back" class="mobile">
                            <svg class="icons" aria-label="icon" color="foregrounds.neutral.primary" fill="currentColor" height="20" role="img" width="20">
                                <path clip-rule="evenodd" d="M11.566 5.435a.8.8 0 0 0-1.132 0l-6 6a.8.8 0 0 0 0 1.13l6 6a.8.8 0 1 0 1.132-1.13L6.93 12.8H19a.8.8 0 1 0 0-1.6H6.931l4.635-4.634a.8.8 0 0 0 0-1.131" fill-rule="evenodd"></path>
                            </svg>                                         
                            <div  class="reply-5">
                                <span class="reply-6">뒤로가기</span>
                            </div>                                                
                        </div> 
                        <!-- 좌측상단 -->
                        <div class="reply-21">
                            <div>
                                <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1" src="${post.memberProfileUrl || '/images/main-page/image2.png'}" style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                            </div>
                            <div class="reply-22">
                                <div class="reply-23">
                                    <div class="reply-24">
                                        <div>
                                            <p class="reply-25">${post.userName}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="reply-26">
                                    <p class="reply-27">${post.jobName||'-'}</p>
                                    <p class="reply-27">${post.relativeDate}</p>
                                </div>
                            </div>
                        </div>
                        <div class="reply-34">
                            <div class="reply-35">
                                <div class="reply-36">
                                    <div class="reply-37">
                                        <span>${post.postContent}</span>
                                    </div>
                                </div>
                            </div>`
            if (post.postFiles && post.postFiles.length > 0) {
                leftHtml +=
                    `<!-- 이미지파일 -->
                            <div class="file">
                                <div class="file-1">
                                    <div class="file-2">
                                        <!-- 이미지 모음 -->
                                        <div class="file-9">`;
                post.postFiles.forEach((file) => {
                    leftHtml += `<div class="file-10">
                                                <img class="img" alt="첨부 이미지 1" src="${file.postFilePath}" style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                                            </div>`;
                });
                leftHtml += `
                                        </div>
                                    </div>
                                </div>`;
            }
            leftHtml+=`
                            </div>
                            <div class="reply-28">
                                <div class="reply-29">
                                    <div class="reply-30">
                                        <!-- 좋아요 -->
                                        <button class="reply-31" data-post-id="${post.id}" data-liked="${post.liked}">
                                            <div class="reply-32">
                                                <svg class="heart" viewBox="0 0 24 24" style="fill:${post.liked ? 'red' : 'white'}; stroke: red;" height="20" role="img" width="20">
                                                    <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                                </svg>
                                            </div>
                                            <div style="margin-left: 5px;">
                                                <p class="reply-33">${post.likesCount}</p>
                                            </div>
                                        </button>                                           
                                    </div>
                                </div>
                            </div>
                        </div>`;
            // console.log(post.liked);

        leftContainer.innerHTML=leftHtml;
        commentcount=post.commentsCount;

    }

    const showCommentsList=(comments)=>{
        const rightContainer=document.querySelector(".reply-right");
        // console.log(comments.length);
        let rightHtml=`<div class="reply-2">
                            <!-- 댓글 상단 -->
                            <div class="reply-3">
                                <div class="reply-4">
                                    <div class="reply-5">
                                        <span class="reply-6">댓글</span>
                                        <span class="reply-6">${commentcount}</span>
                                    </div>
                                    <div class="leply-7">
                                        <button id="down" class="leply-8">
                                            <p class="leply-9">닫기</p>
                                        </button>
                                    </div>
                                </div>
                            </div>`;

        // console.log(comments);
        if(comments.length<1){
            rightHtml+=`<div class="reply-10">
                                <!-- 댓글이 없을 때 -->
                                <div class="reply-11">
                                    <div class="reply-12">
                                        <div class="reply-13">
                                            <p class="reply-25">아직 댓글이 없습니다.</p>
                                            <p class="reply-27">게시물에 첫 번째 댓글을 남겨보세요.</p>
                                        </div>
                                    </div>
                                    <div style="height: 0px;"></div>
                                </div>
</div>`;
            rightContainer.innerHTML=rightHtml;
            return;
        }
        rightHtml+=`<!-- 댓글 메인 -->
                            <div class="reply-10">`;
        comments.forEach((comment)=> {
            rightHtml += `
                                <div class="post-8" style="padding: 8px; border-bottom:none; gap:5px;">
                                    <div class="reply-21">
                                        <div>
                                            <img alt="image" draggable="false" loading="lazy" width="25" height="25" decoding="async" data-nimg="1" src="${comment.memberProfileUrl || '/images/main-page/image2.png'}" style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 25px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                                        </div>
                                        <div class="reply-22">
                                            <div class="reply-23">
                                                <div class="reply-24">
                                                    <div>
                                                        <p class="reply-25" style="font-size: 14px;">${comment.userName}</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="reply-26">
                                                <p class="reply-27" style="font-size: 14px;">${comment.jobName || '-'}</p>
                                                <p class="reply-27" style="font-size: 14px;">${comment.relativeDate}</p>
                                            </div>                                            
                                        </div>
                                        ${comment.owner ? `
                                        <div class="delete">
                                            <button class="delete-1" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                                                <svg class="delbtn" aria-label="icon" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                                                    <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path>                                                </svg>
                                                <div class="delbtn-1">
                                                    <div class="report-2">
                                                        <div class="report-3">
                                                            <div class="report-4">
                                                                <div class="report-5">
                                                                    <p class="report-6">댓글 삭제</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </button>
                                        </div>` : ``}
                                    </div>
                                    <div class="reply-34" style="gap: 8px;">
                                        <div class="reply-35">
                                            <div class="reply-36">
                                                <div class="reply-37" style="font-size: 14px; white-space: normal;">
                                                    <span>${comment.commentContent}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="reply-28">
                                            <div class="reply-29">
                                                <div class="reply-30">
                                                    <button class="reply-31">
                                                        <div class="reply-32">
                                                            <svg class="heart" viewBox="0 0 24 24" fill="red" style="fill:${comment.liked ? 'red' : 'white'}; stroke: red;" height="20" role="img" width="20">
                                                                <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                                            </svg>
                                                        </div>
                                                        <div style="margin-left: 5px;">
                                                            <p class="reply-33">0</p>
                                                        </div>
                                                        <div style="margin-left: 10px;">
                                                            <p class="reply-33 comment">답글</p>
                                                        </div>
                                                    </button>                                                 
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 답글 작성 -->
                                        <div class="comments">
                                            <div class="reply-32">
                                                <svg class="icons" aria-label="icon" color="black" fill="currentColor" height="20" role="img" width="20">
                                                    <path clip-rule="evenodd" d="M5 3.2a.8.8 0 0 1 .8.8v7A3.2 3.2 0 0 0 9 14.2h8.069l-3.635-3.634a.8.8 0 1 1 1.132-1.131l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L17.07 15.8H9A4.8 4.8 0 0 1 4.2 11V4a.8.8 0 0 1 .8-.8" fill-rule="evenodd"></path>
                                                </svg>
                                            </div>
                                            <div class="reply-14 ">
                                                <div class="reply-15">
                                                    <div class="reply-16">
                                                        <div class="reply-17">
                                                            <textarea maxlength="2000" placeholder="답글을 남겨보세요." rows="1" class="reply-18 replytext"></textarea>
                                                            <svg class="enter" aria-label="icon" color="foregrounds.neutral.secondary" fill="currentColor" height="24" role="img" width="24">
                                                                <path clip-rule="evenodd" d="M9.434 6.435a.8.8 0 0 1 1.132 0l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L13.87 12 9.434 7.566a.8.8 0 0 1 0-1.131" fill-rule="evenodd"></path>
                                                            </svg>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div> `;
            // console.log(comment.liked);

            rightHtml += `<!-- 답글 모음 -->
                                        <div class="comment-contain">`;
            if (comment.replies && comment.replies.length > 0) {
                comment.replies.forEach((reply) => {
                    rightHtml += `
                                            <div class="post-8" style="padding: 8px; data-reply-id="${reply.id}" border-bottom:none; gap:5px;">
                                                <div class="reply-21">
                                                    <div>
                                                        <img alt="image" draggable="false" loading="lazy" width="25" height="25" decoding="async" data-nimg="1" src="${reply.memberProfileUrl || '/images/main-page/image2.png'}" style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 25px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                                                    </div>
                                                    <div class="reply-22">
                                                        <div class="reply-23">
                                                            <div class="reply-24">
                                                                <div>
                                                                    <p class="reply-25" style="font-size: 14px;">${reply.userName}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="reply-26">
                                                            <p class="reply-27" style="font-size: 14px;">${reply.jobName || '-'}</p>
                                                            <p class="reply-27" style="font-size: 14px;">${reply.relativeDate}</p>
                                                        </div>                                            
                                                    </div>
                                                    ${reply.owner ? `
                                                    <div class="delete">
                                                        <button class="delete-2" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                                                            <svg class="delbtn-0" aria-label="icon" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                                                                <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path>
                                                            </svg>
                                                            <div class="delbtn-2">
                                                                <div class="report-2">
                                                                    <div class="report-3">
                                                                        <div class="report-4">
                                                                            <div class="report-5">
                                                                                <p class="report-6">답글 삭제</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </button>
                                                    </div>` : ``}
                                                </div>
                                                <div class="reply-34" style="gap: 8px;">
                                                    <div class="reply-35">
                                                        <div class="reply-36">
                                                            <div class="reply-37" style="font-size: 14px; white-space: normal;">
                                                                <span>${reply.replyContent}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="reply-28">
                                                        <div class="reply-29">
                                                            <div class="reply-30">
                                                                <button class="reply-31">
                                                                    <div class="reply-32">
                                                                        <svg class="heart" viewBox="0 0 24 24" fill="red" style="fill:${reply.liked ? 'red' : 'white'}; stroke: red;" height="20" role="img" width="20">
                                                                            <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                                                        </svg>
                                                                    </div>
                                                                </button>                                                 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>   
                                            </div>`;
                    // console.log(reply.liked);
                })
                rightHtml += `</div>`;
            }
            rightHtml += `</div>
                                </div>
                            </div>`;
        });
        rightHtml+=`
                            <!-- 댓글 작성 -->
                            <div class="reply-14">
                                <div class="reply-15">
                                    <div class="reply-16">
                                        <div class="reply-17">
                                            <textarea maxlength="2000" placeholder="댓글을 남겨보세요." rows="1" class="reply-18 replytext"></textarea>
                                            <svg class="enter" aria-label="icon" color="foregrounds.neutral.secondary" fill="currentColor" height="24" role="img" width="24">
                                                <path clip-rule="evenodd" d="M9.434 6.435a.8.8 0 0 1 1.132 0l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L13.87 12 9.434 7.566a.8.8 0 0 1 0-1.131" fill-rule="evenodd"></path>
                                            </svg>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 댓글 삭제모달 -->
                            <div class="del">
                                <div class="del-1">
                                    <div class="del-2">
                                        <div class="del-3">
                                            <div class="del-4">
                                                <svg aria-label="icon" color="foregrounds.neutral.primary" fill="currentColor" height="40px" role="img" width="40px">
                                                    <path clip-rule="evenodd" d="M8.284 6.2H4a.8.8 0 1 0 0 1.6h1.2V18A2.8 2.8 0 0 0 8 20.8h8a2.8 2.8 0 0 0 2.8-2.8V7.8H20a.8.8 0 0 0 0-1.6h-4.284a3.801 3.801 0 0 0-7.432 0m1.666 0h4.1a2.2 2.2 0 0 0-4.1 0m7.25 1.6H6.8V18A1.2 1.2 0 0 0 8 19.2h8a1.2 1.2 0 0 0 1.2-1.2z" fill-rule="evenodd"></path>
                                                </svg>
                                            </div>
                                            <div class="del-5">
                                                <p class="del-6">댓글을 삭제하시겠습니까?</p>
                                                <div class="del-7">
                                                    <p class="del-8">삭제된 댓글은 다시 복구할 수 없으며, 게시물에서 발생한 다른 사용자의 좋아요, 댓글도 함께 삭제됩니다.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="del-9">
                                            <button class="del-10"><span class="del-11">삭제하기</span></button>
                                            <button class="del-12"><span class="del-11">닫기</span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>`;
        rightContainer.innerHTML=rightHtml;
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
            let status;
            if(req.requestExperienceStatus==='await'){
                status='대기 중';
            } else if(req.requestExperienceStatus==='accept'){
                status='합격';
            } else if(req.requestExperienceStatus==='reject'){
                status='불합격';
            } else{
                status='-';
            }

            // console.log(req);
            // console.log(req.id);

            html += `
                <tr>
                                                    <td class="payment-3">
                                                        <p>${req.companyName || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${req.experienceNoticeTitle || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${status}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <div>`;
            if(req.requestExperienceStatus==='await'){
                html+=`<button class="post-23 retract-triger" data-reqid="${req.id}">
                                                                <span class="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">지원 취소</span>
                                                            </button>`
            }else{
                html+=`<p>-</p>`;
            }
                                                           html+=`</div>
                                                    </td>
                                                </tr>
            `;
        }

        container.innerHTML = html;

        // applyFilters();
    };

    const showInternRequest = (request) => {
        const container = document.querySelector('.int-request-container');
        if (!container) return;

        if (!Array.isArray(request) || request.length === 0) {
            container.innerHTML = '<p class="no-results">검색 결과가 없습니다.</p>';
            return;
        }

        let html = '';

        for (const req of request) {
            let status;
            if(req.requestInternStatus==='await'){
                status='대기 중';
            } else if(req.requestInternStatus==='accept'){
                status='합격';
            } else if(req.requestInternStatus==='reject'){
                status='불합격';
            } else{
                status='-';
            }

            html += `
                <tr>
                                                    <td class="payment-3">
                                                        <p>${req.companyName || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${req.internNoticeTitle || ''}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${status}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <div>`;
            if(req.requestInternStatus==='await'){
                html+=`<button class="post-23 retract-triger" data-reqid="${req.id}">
                                                                <span class="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">지원 취소</span>
                                                            </button>`
            }else{
                html+=`<p>-</p>`;
            }
                                                           html+=`</div>
                                                    </td>
                                                </tr>
            `;
        }

        container.innerHTML = html;

        // applyFilters();
    };

    const showPaymentList=(payments)=>{
        const container=document.querySelector(".payment-tbody");
        if(!container) return;

        if (!Array.isArray(payments) || payments.length === 0) {
            container.innerHTML = '<p class="no-results">검색 결과가 없습니다.</p>';
            return;
        }

        let html=``;

        for(const pay of payments){
            if(pay.requestExperienceStatus==='await'){
                status='대기 중';
            } else if(pay.requestExperienceStatus==='accept'){
                status='합격';
            } else if(pay.requestExperienceStatus==='reject'){
                status='불합격';
            } else{
                status='-';
            }
            // console.log(pay.id);
            const datetime=pay.paymentPaidDatetime.split(" ");
            // console.log('datetime: ', datetime);
            const date=datetime[0];
            // console.log('date: ', date);
            html+=`<tr>
                                                    <td class="payment-3">
                                                        <p>${pay.companyName}</p>
                                                    </td>
                                                    <td class="payment-3">
                                                        <p>${pay.experienceNoticeTitle}</p>
                                                    </td>  
                                                    <td class="payment-3">
                                                        <p>${status}</p>
                                                    </td>
                                                    </td>  
                                                    <td class="payment-3">
                                                        <p>${pay.paymentPrice}</p>
                                                    </td>    
                                                    <td class="payment-3">
                                                        <p>${date}</p>
                                                    </td>
                                                </tr>`;
        }

        container.innerHTML=html;
    }

    const showSavedExpList=async (exps) => {
        const container = document.querySelector("#experience-list");
        if (!container) return;

        if (!Array.isArray(exps) || exps.length === 0) {
            container.innerHTML = '<div class="post-3">\n' +
                '                                        <div class="post-13" style="padding-right: 0;padding-left: 0; justify-content: center; display: flex;">\n' +
                '                                            <div class="post-14" style="margin: 0 auto;">\n' +
                '                                                <div class="post-15" >\n' +
                '                                                    <div class="post-8" style="margin: 0 auto;">\n' +
                '                                                        <span>아직 저장한 체험 공고가 없습니다.</span>\n' +
                '                                                    </div>\n' +
                '                                                </div>\n' +
                '                                                <div class="post-9" style="margin: 0 auto;">\n' +
                '                                                    <p class="post-10">관심 있는 체험 공고를 찾아 저장하면 이곳에서 모아볼 수 있습니다.</p>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                            <div class="post-17">\n' +
                '                                                <div class="post-18">\n' +
                '                                                    <div class="post-19">\n' +
                '                                                        <!-- 작성 버튼 -->\n' +
                '                                                        <div class="content-14" style="width: 200px; margin: 0 auto;">\n' +
                '                                                            <button class="set content-15" type="button" >\n' +
                '                                                                <span class="content-16">체험 공고 둘러보기</span>\n' +
                '                                                            </button>\n' +
                '                                                        </div>\n' +
                '                                                    </div>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                        </div>\n' +
                '                                    </div>';
            return;
        }

        let html = ``;

        for (const exp of exps) {
            const fileUrl = await fetch(`/api/experiences/profile?companyId=${exp.companyId}`)
                .then(res => res.text());
            html += `<div class="post-3">
                                        <div class="post-4">
                                            <div>
                                                <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1" src="${fileUrl}" style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                                            </div>
                                            <div class="post-5">
                                                <div class="post-9">
                                                    <p class="post-10">${exp.companyName}</p>
                                                </div>
                                                <div class="post-6">
                                                    <div class="post-7">
                                                        <div>
                                                            <p class="post-8">${exp.experienceNoticeTitle}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="post-9">
                                                    <p class="post-10">${exp.experienceNoticeSubtitle}</p>
                                                </div>
                                            </div>
                                            <!-- 취소버튼 -->
                                            <div>
<!--                                                <button class="post-23">-->
<!--                                                    <span class="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">저장 취소</span>-->
<!--                                                </button>-->
                                            </div>
                                        </div>
                                    </div>`;
        }

        container.innerHTML = html;
    }

    const showSavedIntList=async (ints) => {
        const container = document.querySelector("#intern-list");
        if (!container) return;

        if (!Array.isArray(ints) || ints.length === 0) {
            container.innerHTML = '<div class="post-3">\n' +
                '                                        <div class="post-13" style="padding-right: 0;padding-left: 0; justify-content: center; display: flex;">\n' +
                '                                            <div class="post-14" style="margin: 0 auto;">\n' +
                '                                                <div class="post-15" >\n' +
                '                                                    <div class="post-8" style="margin: 0 auto;">\n' +
                '                                                        <span>아직 저장한 인턴 공고가 없습니다.</span>\n' +
                '                                                    </div>\n' +
                '                                                </div>\n' +
                '                                                <div class="post-9" style="margin: 0 auto;">\n' +
                '                                                    <p class="post-10">관심 있는 인턴 공고를 찾아 저장하면 이곳에서 모아볼 수 있습니다.</p>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                            <div class="post-17">\n' +
                '                                                <div class="post-18">\n' +
                '                                                    <div class="post-19">\n' +
                '                                                        <!-- 작성 버튼 -->\n' +
                '                                                        <div class="content-14" style="width: 200px; margin: 0 auto;">\n' +
                '                                                            <button class="set content-15" type="button" >\n' +
                '                                                                <span class="content-16">인턴 공고 둘러보기</span>\n' +
                '                                                            </button>\n' +
                '                                                        </div>\n' +
                '                                                    </div>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                        </div>\n' +
                '                                    </div>';
            return;
        }

        let html = ``;

        for (const int of ints) {
            const fileUrl = await fetch(`/api/interns/profile?companyId=${int.companyId}`)
                .then(res => res.text());
            html += `<div class="post-3">
                                        <div class="post-4">
                                            <div>
                                                <img alt="image" draggable="false" loading="lazy" width="40" height="40" decoding="async" data-nimg="1" src="${fileUrl}" style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                                            </div>
                                            <div class="post-5">
                                                <div class="post-9">
                                                    <p class="post-10">${int.companyName}</p>
                                                </div>
                                                <div class="post-6">
                                                    <div class="post-7">
                                                        <div>
                                                            <p class="post-8">${int.internNoticeTitle}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="post-9">
                                                    <p class="post-10">${int.internNoticeSubtitle}</p>
                                                </div>
                                            </div>
                                            <!-- 취소버튼 -->
                                            <div>
                                                <button class="post-23">
<!--                                                    <span class="ai_center d_flex jc_center us_none white-space_nowrap p_0px_3px textStyle_Body.BodyS_Bold">저장 취소</span>-->
                                                </button>
                                            </div>
                                        </div>
                                    </div>`;
        }

        container.innerHTML = html;
    }

    return {showPosts: showPosts, showPostDetail:showPostDetail, showCommentsList:showCommentsList, showExperienceRequest: showExperienceRequest, showInternRequest:showInternRequest, showPaymentList:showPaymentList,
    showSavedExpList:showSavedExpList, showSavedIntList:showSavedIntList};
})();