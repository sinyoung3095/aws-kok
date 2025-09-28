// 무한 스크롤
let page = 1;
let checkScroll = true;
let postsCriteria;

const showList = async (page = 1) => {
    const loading = document.getElementById("loading");
    if (loading) loading.style.display = "block";

    const postsCriteria = await postService.getPost(postLayout.showList, page);

    setTimeout(() => {
        if (loading) loading.style.display = "none";
    }, 1000);

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
        }, 1200);
    }
});

// 글쓰기 모달
const popup = document.getElementById("post-write-popup");
const writeBtn = document.querySelector(".popup-trigger");
const closeBtn = document.querySelector(".popup-write-close");

// 글쓰기 모달 열기
if (writeBtn) {
    writeBtn.addEventListener("click", () => {
        popup.classList.add("active");
    });
}

// 글쓰기 모달 닫기 → '게시하지 않고 나가시겠어요?' 모달 띄우기
if (closeBtn) {
    closeBtn.addEventListener("click", () => {
        popup.classList.remove("active");
        document.querySelector("#message-popup2").style.display = "flex";
    });
}

// 바디 이벤트 위임
document.body.addEventListener("click", (e) => {
    const target = e.target;

    // 글쓰기 관련
    // 나가기 선택
    if (target.closest(".popup-all-close")) {
        document.querySelector("#message-popup2").style.display = "none";
        popup.classList.remove("active");
        return;
    }

    // 계속 작성하기 선택
    if (target.closest(".popup-continue")) {
        document.querySelector("#message-popup2").style.display = "none";
        popup.classList.add("active");
        return;
    }

    // 글쓰기 유효성 검사
    if (target.closest(".pop-btn-write")) {
        const input = document.querySelector(".popup-textarea");
        const files = document.querySelector("#btn-add-photo");

        if ((!input || input.value.trim().length < 10) && (!files || files.files.length === 0)) {
            const toast = document.getElementById("toast-white");
            if (toast) {
                toast.style.display = "flex";
                setTimeout(() => {
                    toast.style.display = "none";
                }, 2000);
            }
            return;
        }
    }

    // 댓글창
    if (target.closest(".replys")) {
        document.querySelector(".reply").style.display = "flex";
        return;
    }

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

    if (target.closest(".change .del-12")) {
        document.querySelector(".change").style.display = "none";
        return;
    }

    if (target.closest(".change .del-10")) {
        document.querySelectorAll(".replytext").forEach((t) => (t.value = ""));
        document.querySelector(".change").style.display = "none";
        document.querySelector(".reply").style.display = "none";
        return;
    }

    // 신고 관련
    if (target.closest(".btn")) {
        const btn = target.closest(".btn");
        const reportMenu = btn.parentElement.querySelector(".report-1");
        if (reportMenu) {
            reportMenu.style.display = reportMenu.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    if (target.closest(".report-1")) {
        document.querySelectorAll(".report-1").forEach((r) => (r.style.display = "none"));
        document.querySelector(".report-7").style.display = "flex";
        return;
    }

    if (target.closest(".report-19")) {
        document.querySelector(".report-7").style.display = "none";
        return;
    }

    document.querySelectorAll(".report-1").forEach((r) => {
        if (!target.closest(".report-1") && !target.closest(".btn")) {
            r.style.display = "none";
        }
    });

    // 댓글 삭제
    if (target.closest(".delbtn")) {
        const menu = target.closest(".comment-wrap")?.querySelector(".delbtn-1");
        if (menu) {
            menu.style.display = menu.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    if (target.closest(".delbtn-1")) {
        document.querySelectorAll(".delbtn-1").forEach((m) => (m.style.display = "none"));
        const delModal = document.querySelector(".del");
        if (delModal) delModal.style.display = "flex";
        return;
    }

    if (target.closest(".del .del-12") || target.closest(".del .del-10")) {
        const delModal = document.querySelector(".del");
        if (delModal) delModal.style.display = "none";
        return;
    }

    // 답글
    if (target.closest(".comment")) {
        const comments = target.closest(".comment-wrap")?.querySelector(".comments");
        if (comments) {
            comments.style.display = comments.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    if (target.closest(".delbtn-0")) {
        const menu = target.closest(".reply-wrap")?.querySelector(".delbtn-2");
        if (menu) {
            menu.style.display = menu.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    if (target.closest(".delbtn-2")) {
        document.querySelectorAll(".delbtn-2").forEach((m) => (m.style.display = "none"));
        document.querySelector(".del-comment").style.display = "flex";
        return;
    }

    if (target.closest(".del-comment .del-12") || target.closest(".del-comment .del-10")) {
        document.querySelector(".del-comment").style.display = "none";
        return;
    }

    // 좋아요 (하트)
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
