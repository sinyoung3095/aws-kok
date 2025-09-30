// 무한 스크롤
let page = 1;
let checkScroll = true;
let postsCriteria;
const showList = async (page = 1) => {
    const loading = document.getElementById("loading");
    if (loading) loading.style.display = "block";

    postsCriteria = await postService.getList(page, postLayout.showList);

    if (loading) setTimeout(() => loading.style.display = "none", 500);
    return postsCriteria;
};
showList();

window.addEventListener("scroll", async () => {
    if (window.scrollY + window.innerHeight >= document.documentElement.scrollHeight - 100) {
        // console.log("현재 페이지: ", page);
        if (checkScroll) {
            postsCriteria = await showList(++page);
            // console.log("다음 페이지: ", page);
            checkScroll = false;
        }
        setTimeout(() => {
            if (postsCriteria && postsCriteria.criteria.hasMore) checkScroll = true;
        }, 800);
    }
});

// 글쓰기 모달
const popup = document.getElementById("post-write-popup");
const writeBtns = document.querySelectorAll(".popup-trigger");
const closeBtn = document.querySelector(".popup-write-close");
const writeTextarea = document.querySelector(".popup-textarea");
const writeFiles = document.querySelector("#btn-add-photo");

// 글쓰기 모달 열기
writeBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        popup.classList.add("active");
    });
});

// 모달 닫기
if (closeBtn) {
    closeBtn.addEventListener("click", () => {
        popup.classList.remove("active");
        document.querySelector("#message-popup2").style.display = "flex";
    });
}

document.body.addEventListener("click", async (e) => {
    const target = e.target;

    // 글쓰기
    if (target.closest(".popup-all-close")) {
        document.querySelector("#message-popup2").style.display = "none";
        popup.classList.remove("active");
        return;
    }

    if (target.closest(".popup-continue")) {
        document.querySelector("#message-popup2").style.display = "none";
        popup.classList.add("active");
        return;
    }

    if (target.closest(".pop-btn-write")) {
        const content = writeTextarea.value.trim();
        const files = writeFiles.files;

        if (content.length < 10 && files.length === 0) {
            const toast = document.getElementById("toast-white");
            if (toast) {
                toast.style.display = "flex";
                setTimeout(() => toast.style.display = "none", 2000);
            }
            return;
        }

        try {
            const postId = await postService.write(content, files);
            console.log("글쓰기 성공:", postId);

            popup.classList.remove("active");
            writeTextarea.value = "";
            writeFiles.value = "";

            const previewContainer = document.querySelector(".popup-preview-inner");
            if (previewContainer) previewContainer.innerHTML = "";

            const postContainer = document.querySelector("#post-container");
            if (postContainer) postContainer.innerHTML = "";
            page = 1;
            checkScroll = true;
            await showList(page);
        } catch (err) {
            console.error("업로드 실패:", err.message);
            alert("업로드를 실패했습니다.");
        }
        return;
    }

    // 게시글 상세 모달 열기
    if (target.closest(".check-detail-post")) {
        const postCard = target.closest(".post-8");
        const postId = postCard.dataset.postId;

        try {
            const post = await postService.getOne(postId);
            console.log("게시글 :", post);
            postLayout.showDetail(post);
        } catch (err) {
            console.error("상세보기 불러오기 실패:", err);
            alert("게시글을 불러올 수 없습니다.");
        }
        return;
    }

    // 게시글 상세 모달 닫기
    if (target.id === "post-detail-modal" || target.closest(".close-modal")) {
        document.getElementById("post-detail-modal").style.display = "none";
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

    // 신고
    if (target.closest(".btn")) {
        const btn = target.closest(".btn");
        const reportMenu = btn.parentElement.querySelector(".report-1");
        if (reportMenu) {
            reportMenu.style.display = reportMenu.style.display === "flex" ? "none" : "flex";
        }
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

    // 답글 토글
    if (target.closest(".post-25.comment")) {
        const postCard = target.closest(".post-23");
        if (postCard) {
            const comments = postCard.closest(".post-8").querySelector(".comments");
            if (comments) {
                comments.style.display = (comments.style.display === "flex") ? "none" : "flex";
            }
        }
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

    // 좋아요 (게시글)
    const likeBtn = target.closest(".like-btn");
    if (likeBtn) {
        const postId = likeBtn.dataset.postId;
        const likeCountEl = likeBtn.querySelector(".post-25");
        const heartIcon = likeBtn.querySelector(".heart");

        let liked = likeBtn.dataset.liked === "true";
        let current = parseInt(likeCountEl.textContent) || 0;

        if (!liked) {
            const success = await postService.postLike(postId);
            if (success) {
                likeBtn.dataset.liked = "true";
                likeCountEl.textContent = current + 1;
                heartIcon.style.fill = "red";
                heartIcon.style.stroke = "red";
            }
        } else {
            const success = await postService.removeLike(postId);
            if (success) {
                likeBtn.dataset.liked = "false";
                likeCountEl.textContent = Math.max(0, current - 1);
                heartIcon.style.fill = "white";
                heartIcon.style.stroke = "red";
            }
        }
    }

    // 게시글 신고
    if (target.closest(".report-1")) {
        const postCard = target.closest(".post-8");
        const postId = postCard.dataset.postId;
        const reportModal = document.querySelector(".report-7");

        if (postId && reportModal) {
            reportModal.dataset.postId = postId;
        }

        document.querySelectorAll(".report-1").forEach((r) => (r.style.display = "none"));
        reportModal.style.display = "flex";
        return;
    }

    if (target.closest(".report-17")) {
        const reportModal = document.querySelector(".report-7");
        const postId = reportModal.dataset.postId;

        if (!postId) {
            alert("게시글 ID를 찾을 수 없습니다.");
            return;
        }

        try {
            await postService.reportPost(postId);
            alert("신고가 접수되었습니다.");
            reportModal.style.display = "none";
        } catch (err) {
            console.error("신고 실패:", err);
            alert(err.message);
            reportModal.style.display = "none";
        }
    }
});


// 파일 썸네일
(() => {
    const input = document.getElementById('btn-add-photo');
    const previewContainer = document.querySelector('.popup-preview-inner');

    const MAX_FILES = 8;
    const MAX_SIZE = 20 * 1024 * 1024;

    let fileBuffer = [];

    const toKey = (f) => `${f.name}|${f.size}|${f.lastModified}`;

    const syncInput = () => {
        const dt = new DataTransfer();
        fileBuffer.forEach(f => dt.items.add(f));
        input.files = dt.files;
    };

    const render = () => {
        previewContainer.innerHTML = '';
        fileBuffer.forEach((file, idx) => {
            const item = document.createElement('div');
            item.className = 'preview-item';

            if (file.type && file.type.startsWith('image/')) {
                const img = document.createElement('img');
                img.className = 'preview-thumb';
                const reader = new FileReader();
                reader.onload = (e) => { img.src = e.target.result; };
                reader.readAsDataURL(file);
                item.appendChild(img);
            } else {
                const box = document.createElement('div');
                box.className = 'preview-generic';
                box.textContent = file.name;
                item.appendChild(box);
            }

            const rm = document.createElement('button');
            rm.type = 'button';
            rm.className = 'preview-remove';
            rm.innerHTML = `<svg width="14" height="14" viewBox="0 0 24 24" fill="none">
                              <path d="M18 6L6 18M6 6l12 12" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
                            </svg>`;
            rm.addEventListener('click', () => {
                fileBuffer.splice(idx, 1);
                syncInput();
                render();
            });

            item.appendChild(rm);
            previewContainer.appendChild(item);
        });
    };

    const addFiles = (files) => {
        const existingKeys = new Set(fileBuffer.map(toKey));
        const arFile = Array.from(files);

        for (const f of arFile) {
            if (fileBuffer.length >= MAX_FILES) {
                alert(`최대 ${MAX_FILES}개까지 업로드할 수 있습니다.`);
                break;
            }
            if (f.size > MAX_SIZE) {
                alert(`"${f.name}" 파일이 용량 제한(20MB)을 초과했습니다.`);
                continue;
            }
            if (existingKeys.has(toKey(f))) {
                continue;
            }
            fileBuffer.push(f);
            existingKeys.add(toKey(f));
        }
        syncInput();
        render();
    };

    // 이벤트 바인딩
    input.addEventListener('change', () => addFiles(input.files));
})();
