const commentLayout = (() => {
    const showCommentsList = (comments) => {
        const commentContainer = document.querySelector(".reply-10");
        let text = ``;

        comments.forEach((comment) => {
            text += `
            <div class="post-8" data-comment-id="${comment.id}" style="padding: 8px; border-bottom:none; gap:5px;">
                <div class="post-9">
                    <div>
                        <img alt="image" width="25" height="25" src="${comment.memberProfileUrl || '/images/main-page/image2.png'}" 
                        style="color: transparent; border-radius: 999px; cursor: none; max-height: 25px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                    </div>
                    <div class="post-10">
                        <div class="post-11">
                            <div class="post-12">
                                <div>
                                    <p class="post-13" style="font-size: 14px;">${comment.userName}</p>
                                </div>
                            </div>
                        </div>
                        <div class="post-14">
                            <p class="post-15" style="font-size: 14px;">${comment.jobName}</p>
                            <p class="post-15" style="font-size: 14px;">${comment.relativeDate}</p>
                        </div>
                    </div>
                    <!-- 댓글 삭제 -->
                    <div class="delete">
                        <button class="post-28" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                            <svg class="delbtn" aria-label="icon" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                                <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path></symbol><symbol id="paper-fold-text-outline" viewBox="0 0 24 24"><path clip-rule="evenodd" d="M7 3.8A1.2 1.2 0 0 0 5.8 5v8a.8.8 0 0 1-1.6 0V5A2.8 2.8 0 0 1 7 2.2h12A2.8 2.8 0 0 1 21.8 5v13a4.15 4.15 0 0 1-.75 2.343c-.6.83-1.59 1.457-3.05 1.457H6a4.15 4.15 0 0 1-2.342-.75C2.827 20.45 2.2 19.46 2.2 18v-2a.8.8 0 0 1 .8-.8h12a.8.8 0 0 1 .8.8v2c0 .94.373 1.45.792 1.751.46.331 1.036.45 1.408.45.94 0 1.449-.374 1.75-.793A2.55 2.55 0 0 0 20.2 18V5A1.2 1.2 0 0 0 19 3.8zm7.827 16.4c-.384-.563-.627-1.29-.627-2.2v-1.2H3.8V18c0 .94.373 1.45.792 1.751.46.331 1.036.45 1.408.45zM8.2 7a.8.8 0 0 1 .8-.8h8a.8.8 0 0 1 0 1.6H9a.8.8 0 0 1-.8-.8m0 4a.8.8 0 0 1 .8-.8h4a.8.8 0 0 1 0 1.6H9a.8.8 0 0 1-.8-.8" fill-rule="evenodd"></path>
                            </svg>
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
                    </div>
                </div>
                <div class="post-16" style="gap: 8px;">
                    <div class="post-17">
                        <div class="post-18">
                            <div class="post-19" style="font-size: 14px; white-space: normal;">
                                <span>${comment.commentContent}</span>
                            </div>
                        </div>
                    </div>
                    <div class="detail-post-20">
                        <div class="post-21">
                            <div class="post-22">
                                <button class="post-23">
                                    <div class="post-24">
                                        <svg class="heart" viewBox="0 0 24 24" fill="${comment.liked ? "red" : "none"}" stroke="red" height="20" role="img" width="20">
                                            <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                        </svg>
                                    </div>
                                    <div style="margin-left: 5px;">
                                        <p class="post-25">${comment.likesCount}</p>
                                    </div>
                                    <div style="margin-left: 10px;">
                                        <p class="post-25 comment">답글</p>
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- 답글 작성 -->
                    <div class="comments">
                        <div class="reply-32">
                            <svg class="enter icons" color="black" fill="currentColor" height="20" role="img" width="20">
                                <path clip-rule="evenodd" d="M5 3.2a.8.8 0 0 1 .8.8v7A3.2 3.2 0 0 0 9 14.2h8.069l-3.635-3.634a.8.8 0 1 1 1.132-1.131l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L17.07 15.8H9A4.8 4.8 0 0 1 4.2 11V4a.8.8 0 0 1 .8-.8" fill-rule="evenodd"></path>
                            </svg>
                        </div>
                        <div class="comment-text">
                            <div class="reply-15">
                                <div class="reply-16">
                                    <div class="reply-17 reply-wrap" data-comment-id="${comment.id}">
                                        <textarea maxlength="2000" placeholder="답글을 남겨보세요." rows="1" class="reply-18 replytext"></textarea>
                                        <svg class="enter reply-submit" data-comment-id="${comment.id}" color="foregrounds.neutral.secondary" fill="currentColor" height="24" role="img" width="24">
                                            <path clip-rule="evenodd" d="M9.434 6.435a.8.8 0 0 1 1.132 0l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L13.87 12 9.434 7.566a.8.8 0 0 1 0-1.131" fill-rule="evenodd"></path>
                                        </svg>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;

            if(comment.replies && comment.replies.length > 0) {
                    text += `
                    <!-- 답글 목록 -->
                    <div class="comment-contain">
                        <div class="non-show-replies-container" style="display: flex">
                            <button class="show-replies-content">
                                <svg color="foregrounds.neutral.secondary" fill="currentColor" height="20" role="img" width="20" viewBox="0 0 24 24">
                                    <path clip-rule="evenodd" d="M17.566 14.566a.8.8 0 0 0 0-1.132l-5-5a.8.8 0 0 0-1.132 0l-5 5a.8.8 0 0 0 1.132 1.132L12 10.13l4.434 4.435a.8.8 0 0 0 1.132 0" fill-rule="evenodd"></path>
                                </svg>
                                <span>답글 보기</span>
                            </button>
                        </div>
                        <div class="show-replies-container" style="display: none">
                            <button class="show-replies-content">
                                <svg color="foregrounds.neutral.secondary" fill="currentColor" height="20" role="img" width="20" viewBox="0 0 24 24">
                                    <path clip-rule="evenodd" d="M6.434 9.435a.8.8 0 0 1 1.132 0L12 13.869l4.434-4.434a.8.8 0 1 1 1.132 1.13l-5 5a.8.8 0 0 1-1.132 0l-5-5a.8.8 0 0 1 0-1.13" fill-rule="evenodd"></path>
                                </svg>
                                <span>답글 닫기</span>
                            </button>
                        </div>
                        <div class="reply-list" style="display: none">`;
                    comment.replies.forEach(reply => {
                        text += `                      
                        <div class="post-13 reply-item" style="padding: 8px; border-bottom:none; gap:5px;">
                            <div class="post-9">
                                <div>
                                    <img alt="image" width="25" height="25" src="${reply.memberProfileUrl || '/images/main-page/image2.png'}"
                                    style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 25px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                                </div>
                                <div class="post-10">
                                    <div class="post-11">
                                        <div class="post-12">
                                            <div>
                                                <p class="post-13" style="font-size: 14px;">${reply.userName}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="post-14">
                                        <p class="post-15" style="font-size: 14px;">${reply.jobName}</p>
                                        <p class="post-15" style="font-size: 14px;">${reply.relativeDate}</p>
                                    </div>
                                </div>
                                <div class="delete">
                                    <button class="delete-2" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                                        <svg class="delbtn-0" aria-label="icon" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                                            <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path></symbol><symbol id="paper-fold-text-outline" viewBox="0 0 24 24"><path clip-rule="evenodd" d="M7 3.8A1.2 1.2 0 0 0 5.8 5v8a.8.8 0 0 1-1.6 0V5A2.8 2.8 0 0 1 7 2.2h12A2.8 2.8 0 0 1 21.8 5v13a4.15 4.15 0 0 1-.75 2.343c-.6.83-1.59 1.457-3.05 1.457H6a4.15 4.15 0 0 1-2.342-.75C2.827 20.45 2.2 19.46 2.2 18v-2a.8.8 0 0 1 .8-.8h12a.8.8 0 0 1 .8.8v2c0 .94.373 1.45.792 1.751.46.331 1.036.45 1.408.45.94 0 1.449-.374 1.75-.793A2.55 2.55 0 0 0 20.2 18V5A1.2 1.2 0 0 0 19 3.8zm7.827 16.4c-.384-.563-.627-1.29-.627-2.2v-1.2H3.8V18c0 .94.373 1.45.792 1.751.46.331 1.036.45 1.408.45zM8.2 7a.8.8 0 0 1 .8-.8h8a.8.8 0 0 1 0 1.6H9a.8.8 0 0 1-.8-.8m0 4a.8.8 0 0 1 .8-.8h4a.8.8 0 0 1 0 1.6H9a.8.8 0 0 1-.8-.8" fill-rule="evenodd"></path>
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
                            </div>
                        </div>
                        <div class="post-16" style="gap: 8px;">
                            <div class="post-17">
                                <div class="post-18">
                                    <div class="post-19" style="font-size: 14px; white-space: normal;">
                                        <span>${reply.replyContent}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="reply-28">
                                <div class="reply-29">
                                    <div class="reply-30">
                                        <button class="reply-31">
                                            <div class="reply-32">
                                                <svg class="heart" viewBox="0 0 24 24" fill="red" stroke="red" height="20" role="img" width="20">
                                                    <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                                </svg>
                                            </div>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
                });
            }

            text += `
                    </div>
                </div>
            </div>`
        });

        commentContainer.innerHTML += text;
    };

    return { showCommentsList : showCommentsList };
})();