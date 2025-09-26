const postLayout = (() => {
    const showList = (postsCriteria) => {
        const postContainer = document.querySelector("#post-container");
        let text = ``;

        if (!postsCriteria.posts) {
            text += `
            <div class="post-8">
                <div class="post-16" style="padding-left: 0; justify-content: center; display: flex;">
                    <div class="post-17" style="margin: 0 auto;">
                        <div class="post-16" style="padding-left: 0;">
                            <div class="post-13" style="margin: 0 auto;">
                                <span>작성글이 없습니다.</span>
                            </div>
                        </div>
                        <div class="post-14" style="margin: 0 auto;">
                            <p class="post-15">게시물을 작성해 주세요.</p>
                        </div>
                    </div>
                </div>
            </div>`;
            postContainer.innerHTML = text;

        } else {
            postsCriteria.posts.forEach((post) => {

                text += `
                <div class="post-8">
                    <div class="post-9">
                        <div>
                            <img alt="image" draggable="false" loading="lazy" width="40" height="40"
                                 src="${post.memberProfileUrl}"
                                 style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                        </div>
                        <div class="post-10">
                            <div class="post-11">
                                <div class="post-12">
                                    <div>
                                        <p class="post-13">${post.userName}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="post-14">
                                <p class="post-15">${post.jobName}</p>
                                <p class="post-15">${post.relativeDate}</p>
                            </div>
                        </div>
                        <div class="report">
                            <button class="post-28" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                                <svg class="btn" aria-label="icon" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                                    <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path></symbol><symbol id="paper-fold-text-outline" viewBox="0 0 24 24"><path clip-rule="evenodd" d="M7 3.8A1.2 1.2 0 0 0 5.8 5v8a.8.8 0 0 1-1.6 0V5A2.8 2.8 0 0 1 7 2.2h12A2.8 2.8 0 0 1 21.8 5v13a4.15 4.15 0 0 1-.75 2.343c-.6.83-1.59 1.457-3.05 1.457H6a4.15 4.15 0 0 1-2.342-.75C2.827 20.45 2.2 19.46 2.2 18v-2a.8.8 0 0 1 .8-.8h12a.8.8 0 0 1 .8.8v2c0 .94.373 1.45.792 1.751.46.331 1.036.45 1.408.45.94 0 1.449-.374 1.75-.793A2.55 2.55 0 0 0 20.2 18V5A1.2 1.2 0 0 0 19 3.8zm7.827 16.4c-.384-.563-.627-1.29-.627-2.2v-1.2H3.8V18c0 .94.373 1.45.792 1.751.46.331 1.036.45 1.408.45zM8.2 7a.8.8 0 0 1 .8-.8h8a.8.8 0 0 1 0 1.6H9a.8.8 0 0 1-.8-.8m0 4a.8.8 0 0 1 .8-.8h4a.8.8 0 0 1 0 1.6H9a.8.8 0 0 1-.8-.8" fill-rule="evenodd"></path>
                                </svg>
                                <div class="report-1">
                                    <div class="report-2">
                                        <div class="report-3">
                                            <div class="report-4">
                                                <div class="report-5">
                                                    <p class="report-6">게시물 신고</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </div>
                    </div>

                    <div class="post-16">
                        <div class="post-17">
                            <div class="post-18">
                                <div class="post-19">
                                    <span>${post.postContent}</span>
                                </div>
                            </div>
                        </div>`;

                if (post.files && post.files.length > 0) {
                    text += `
                    <div class="file">
                        <div class="file-1">
                            <div class="file-2">
                                <div class="file-9">`;
                    post.files.forEach((file) => {
                        text += `
                        <div class="file-10">
                            <img class="img" src="${file.filePath}" alt="첨부 이미지"
                                 style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                        </div>`;
                    });
                    text += `
                                </div>
                            </div>
                        </div>
                    </div>`;
                }

                text += `
                        <div class="post-20">
                            <div class="post-21">
                                <div class="post-22">
                                    <button class="post-23">
                                        <div class="post-24">
                                            <svg class="heart" viewBox="0 0 24 24" fill="currentColor" stroke="red" height="20" role="img" width="20">
                                                <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                            </svg>
                                        </div>
                                        <div style="margin-left: 5px;">
                                            <p class="post-25">${post.likeCount}</p>
                                        </div>
                                    </button>

                                    <button class="post-23 replys">
                                        <div class="post-24">
                                            <svg class="icons" aria-label="icon" fill="currentColor" height="20" role="img" width="20">
                                                <path clip-rule="evenodd" d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z" fill-rule="evenodd"></path>
                                            </svg>
                                        </div>
                                        <div style="margin-left: 5px;">
                                            <p class="post-25">${post.commentCount}</p>
                                        </div>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;
            });

            postContainer.innerHTML += text;
        }

        return postsCriteria;
    }

    return { showList: showList };
})();
