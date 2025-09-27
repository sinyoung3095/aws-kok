document.body.addEventListener("click", (e) => {
    const target = e.target;

    // 글쓰기 팝업 열기
    if (target.closest(".hide-1")) {
        document.getElementById("post-write-popup").style.display = "flex";
        return;
    }

    // 게시물 신고 샌드위치 버튼 → 메뉴 토글
    if (target.closest(".btn")) {
        const report = target.closest(".post")?.querySelector(".report-1");
        if (report) {
            report.style.display =
                report.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    // 신고 메뉴 클릭 → 신고 모달 열기
    if (target.closest(".report-1")) {
        document.querySelectorAll(".report-1").forEach((r) => (r.style.display = "none"));
        document.querySelector(".report-7").style.display = "flex";
        return;
    }

    // 신고 모달 닫기
    if (target.closest(".report-19")) {
        document.querySelector(".report-7").style.display = "none";
        return;
    }

    // 댓글창 열기
    if (target.closest(".replys")) {
        document.querySelector(".reply").style.display = "flex";
        return;
    }

    // 댓글창 닫기 (down 버튼)
    if (target.closest(".leply-7")) {
        const replytext = document.querySelectorAll(".replytext");
        const change = document.querySelector(".change");
        let hasText = false;

        replytext.forEach((t) => {
            if (t.value.trim() !== "") hasText = true;
        });

        if (hasText) {
            change.style.display = "flex";
        } else {
            document.querySelector(".reply").style.display = "none";
        }
        return;
    }

    // 댓글 작성중 경고창 취소
    if (target.closest(".change .del-12")) {
        document.querySelector(".change").style.display = "none";
        return;
    }

    // 댓글 작성중 경고창 종료
    if (target.closest(".change .del-10")) {
        document.querySelectorAll(".replytext").forEach((t) => (t.value = ""));
        document.querySelector(".change").style.display = "none";
        document.querySelector(".reply").style.display = "none";
        return;
    }

    // 댓글 삭제 샌드위치 버튼
    if (target.closest(".delbtn")) {
        const menu = target.closest(".comment-wrap")?.querySelector(".delbtn-1");
        if (menu) {
            menu.style.display = menu.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    // 댓글 삭제 선택 → 삭제 모달 열기
    if (target.closest(".delbtn-1")) {
        document.querySelectorAll(".delbtn-1").forEach((m) => (m.style.display = "none"));
        document.querySelector(".del").style.display = "flex";
        return;
    }

    // 댓글 삭제 모달 닫기
    if (target.closest(".del .del-12") || target.closest(".del .del-10")) {
        document.querySelector(".del").style.display = "none";
        return;
    }

    // 답글 토글
    if (target.closest(".comment")) {
        const comments = target.closest(".comment-wrap")?.querySelector(".comments");
        if (comments) {
            comments.style.display = comments.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    // 답글 삭제 샌드위치 버튼
    if (target.closest(".delbtn-0")) {
        const menu = target.closest(".reply-wrap")?.querySelector(".delbtn-2");
        if (menu) {
            menu.style.display = menu.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    // 답글 삭제 선택 → 삭제 모달 열기
    if (target.closest(".delbtn-2")) {
        document.querySelectorAll(".delbtn-2").forEach((m) => (m.style.display = "none"));
        document.querySelector(".del-comment").style.display = "flex";
        return;
    }

    // 답글 삭제 모달 닫기
    if (target.closest(".del-comment .del-12") || target.closest(".del-comment .del-10")) {
        document.querySelector(".del-comment").style.display = "none";
        return;
    }

    // 댓글 하트 버튼
    const replyHeartBtn = target.closest(".reply-31");
    if (replyHeartBtn) {
        const replyHeartIcon = replyHeartBtn.querySelector(".heart");
        if (replyHeartIcon) {
            if (!replyHeartIcon.dataset.clicked) {
                replyHeartIcon.style.fill = "red";
                replyHeartIcon.style.stroke = "red";
                replyHeartIcon.dataset.clicked = "true";
            } else {
                if (replyHeartIcon.style.fill === "red") {
                    replyHeartIcon.style.fill = "white";
                    replyHeartIcon.style.stroke = "red";
                } else {
                    replyHeartIcon.style.fill = "red";
                    replyHeartIcon.style.stroke = "red";
                }
            }
        }
        return;
    }

    // 게시글 하트 버튼
    const postHeartBtn = target.closest(".post-24");
    if (postHeartBtn) {
        const postHeartIcon = postHeartBtn.querySelector(".heart");
        if (postHeartIcon) {
            if (!postHeartIcon.dataset.clicked) {
                postHeartIcon.style.fill = "red";
                postHeartIcon.style.stroke = "red";
                postHeartIcon.dataset.clicked = "true";
            } else {
                if (postHeartIcon.style.fill === "red") {
                    postHeartIcon.style.fill = "white";
                    postHeartIcon.style.stroke = "red";
                } else {
                    postHeartIcon.style.fill = "red";
                    postHeartIcon.style.stroke = "red";
                }
            }
        }
        return;
    }
});

// 무한 스크롤
let page = 1;
let checkScroll = true;
let postsCriteria;

const showList = async (page = 1) => {
    const loading = document.getElementById("loading");
    loading.style.display = "block";
    const postsCriteria = await postService.getPost(postLayout.showList, page);
    setTimeout(() => {
        loading.style.display = "none";
    }, 500);
    return postsCriteria;
};
showList();

window.addEventListener("scroll", async () => {
    const scrollTop = window.scrollY;
    const windowHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    if (scrollTop + windowHeight >= documentHeight - 100) {
        if (checkScroll) {
            postsCriteria = await showList(++page);
            checkScroll = false;
        }
        setTimeout(() => {
            if (postsCriteria !== null && postsCriteria.criteria.hasMore) {
                checkScroll = true;
            }
        }, 700);
    }
});
