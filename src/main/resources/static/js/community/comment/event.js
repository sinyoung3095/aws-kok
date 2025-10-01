// 댓글 불러오기
const showComments = async (postId) => {
    try {
        const comments = await commentService.getComments(postId);
        const commentContainer = document.querySelector("#post-detail-modal .reply-10");
        commentContainer.innerHTML = "";
        commentLayout.showCommentsList(comments);
    } catch (err) {
        console.error("댓글 불러오기 실패:", err);
        alert("댓글을 불러올 수 없습니다.");
    }
};

document.body.addEventListener("click", async (e) => {
    const target = e.target;


    // 댓글 작성
    if (target.closest(".reply-14 .enter")) {
        const modal = document.getElementById("post-detail-modal");
        const postId = modal.dataset.postId;
        const textarea = modal.querySelector(".reply-14 .replytext");
        const content = textarea.value.trim();

        if (!content) {
            alert("댓글을 입력해주세요.");
            return;
        }

        try {
            await commentService.writeComment({
                postId: postId,
                commentContent: content,
                memberId: 3
            });

            textarea.value = "";

            const commentContainer = modal.querySelector(".reply-10");
            commentContainer.innerHTML = "";
            await showComments(postId);

        } catch (err) {
            console.error("댓글 작성 실패:", err);
            alert("댓글 작성 중 오류가 발생했습니다.");
        }
    }

    // 답글 작성
    if (target.closest(".reply-wrap .enter")) {
        const modal = document.getElementById("post-detail-modal");
        const postId = modal.dataset.postId;
        const replyBox = target.closest(".reply-wrap");
        const textarea = replyBox.querySelector(".replytext");
        const content = textarea.value.trim();
        const commentId = replyBox.dataset.commentId;

        if (!content) {
            alert("답글을 입력해주세요.");
            return;
        }

        try {
            await commentService.writeReply({
                commentId: commentId,
                replyContent: content,
                memberId: 3,
                postId: postId
            });

            textarea.value = "";

            await showComments(postId);

        } catch (err) {
            console.error("답글 작성 실패:", err);
            alert("답글 작성 중 오류가 발생했습니다.");
        }
    }

    // 답글 보기 / 접기 버튼 토글
    if (target.closest(".show-replies-content")) {
        const commentContain = target.closest(".comment-contain");
        if (!commentContain) return;

        const nonShow = commentContain.querySelector(".non-show-replies-container");
        const show = commentContain.querySelector(".show-replies-container");
        const replyList = commentContain.querySelector(".reply-list");

        if (replyList.style.display === "none") {
            replyList.style.display = "block";
            nonShow.style.display = "none";
            show.style.display = "flex";
        } else {
            replyList.style.display = "none";
            nonShow.style.display = "flex";
            show.style.display = "none";
        }
    }

    // 답글 작성란 토글
    if (target.closest(".post-25.comment")) {
        const postCard = target.closest(".post-23");
        if (postCard) {
            const comments = postCard.closest(".post-8").querySelector(".comments");
            if (comments) {
                comments.style.display = (comments.style.display === "flex") ? "none" : "flex";
            }
        }
    }

    // 댓글 삭제
    if (target.closest(".delbtn")) {
        const menu = target.closest(".comment-wrap").querySelector(".delbtn-1");
        if (menu) menu.style.display = menu.style.display === "flex" ? "none" : "flex";
        return;
    }

    if (target.closest(".delbtn-1")) {
        document.querySelectorAll(".delbtn-1").forEach((m) => (m.style.display = "none"));
        const delModal = document.querySelector(".del");
        if (delModal) delModal.style.display = "flex";
        return;
    }

    if (target.closest(".del .del-12") || target.closest(".del .del-10")) {
        document.querySelector(".del").style.display = "none";
        return;
    }

    if (target.closest(".delbtn-0")) {
        const menu = target.closest(".reply-wrap").querySelector(".delbtn-2");
        if (menu) menu.style.display = menu.style.display === "flex" ? "none" : "flex";
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
});