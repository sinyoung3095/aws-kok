const searchBtn = document.querySelector(".search-btn");
const jobMenu = document.querySelector(".job");
const jobItems = document.querySelectorAll(".job-3");
const checkIcon = document.querySelector(".setting-31");
const searchSpan = document.querySelector(".search-span");
const cateBtns = document.querySelectorAll(".category-sub");

// 버튼 클릭 → 메뉴 표시/숨김 토글
searchBtn.addEventListener("click", (e) => {
    e.stopPropagation(); // 외부 클릭 이벤트 막기
    jobMenu.style.display =
        jobMenu.style.display === "block" ? "none" : "block";
});

// job-3 선택
jobItems.forEach((item) => {
    item.addEventListener("click", (e) => {
        e.stopPropagation();

        // 체크마크 이동
        if (checkIcon.parentNode) checkIcon.parentNode.removeChild(checkIcon);
        item.appendChild(checkIcon);

        // 선택 강조
        jobItems.forEach((j) => j.classList.remove("selected"));
        item.classList.add("selected");

        // span 업데이트
        const text = item.querySelector(".job-6").innerText;
        searchSpan.innerText = text;

        // 메뉴 숨김
        jobMenu.style.display = "none";
    });
});

// 외부 클릭 시 메뉴 숨김
document.addEventListener("click", () => {
    jobMenu.style.display = "none";
});

const categoryButtons = document.querySelectorAll(".category-sub");
categoryButtons.forEach((btn) => {
    btn.addEventListener("click", () => {
        categoryButtons.forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
    });
});

// 여러 개의 tr.body-tr에 대해 각각 적용
// document.querySelectorAll("tr.body-tr").forEach((tr) => {
//     const activeExp = tr.querySelector("button.appli-active-btn");
//     const activeCircle = activeExp ? activeExp.querySelector(".circle") : null;
//     const expStatus = tr.querySelector("span.exp-status");
//
//     if (!activeExp || !activeCircle || !expStatus) return;
//
//     activeExp.style.transition = "background-color 0.5s";
//     activeCircle.style.transition = "transform 0.5s";
//     expStatus.style.transition = "background-color 0.5s, color 0.5s";
//
//     activeExp.addEventListener("click", () => {
//         if (expStatus.classList.contains("gray")) {
//             activeExp.classList.remove("gray");
//             expStatus.classList.remove("gray");
//             expStatus.innerText = "모집 중";
//         } else {
//             activeExp.classList.add("gray");
//             expStatus.classList.add("gray");
//             expStatus.innerText = "모집 완료";
//         }
//     });
//
//     cateBtns.forEach((btn) => {
//         btn.addEventListener("click", (e) => {
//             if (e.target.classList.contains("all")) {
//                 tr.classList.remove("disnone");
//             } else if (e.target.classList.contains("ing")) {
//                 if (expStatus.classList.contains("gray")) {
//                     tr.classList.add("disnone");
//                 } else {
//                     tr.classList.remove("disnone");
//                 }
//             } else if (e.target.classList.contains("end")) {
//                 if (expStatus.classList.contains("gray")) {
//                     tr.classList.remove("disnone");
//                 } else {
//                     tr.classList.add("disnone");
//                 }
//             }
//         });
//     });
// });

const experienceTable = document.querySelector("#experience-list-table");
if (experienceTable) {
    experienceTable.addEventListener("click", async (e) => {
        // 모집 활성/비활성 토글 버튼
        const activeExp = e.target.closest("button.appli-active-btn");
        if (activeExp) {
            const tr = activeExp.closest("tr.body-tr");
            const activeCircle = activeExp.querySelector(".circle");
            const expStatus = tr.querySelector("span.exp-status");

            if (!activeCircle || !expStatus) return;

            tr.querySelectorAll(".appli-active-btn").forEach(btn => {
                btn.classList.remove("active");
            });
            activeExp.classList.add("active");
            expStatus.classList.add("active");

            if (expStatus.classList.contains("gray")) {
                activeExp.classList.remove("gray");
                expStatus.classList.remove("gray");
                expStatus.innerText = "모집 중";
                statusValue = "active";
            } else {
                activeExp.classList.add("gray");
                expStatus.classList.add("gray");
                expStatus.innerText = "모집 완료";
                statusValue = "inactive";
            }

            // 상태 버튼 클릭시 확인
            // const isActive = activeExp.classList.contains("active");
            // const statusValue = isActive ? "active" : "inactive";
            const noticeId = tr.dataset.id;
            console.log(statusValue, noticeId)

            try {
                const data = await exprienceNoticeService.updateExperienceStatus(noticeId, statusValue);
                console.log("DB 반영 성공:", data);
            } catch (err) {
                console.error("DB 반영 실패:", err);
            }

            // return;
        }

    });
}


document.querySelectorAll("tr.body-tr").forEach((tr) => {
    const hambugerBtn = tr.querySelector("button.hambuger");
    const hambugerPopWrap = hambugerBtn
        ? hambugerBtn.querySelector(".hambuger-pop-wrap")
        : null;

    if (!hambugerBtn || !hambugerPopWrap) return;

    hambugerBtn.addEventListener("click", (e) => {
        e.stopPropagation();

        // 모든 팝업 닫기
        document
            .querySelectorAll(".hambuger-pop-wrap")
            .forEach((pop) => (pop.style.display = "none"));

        // 버튼 옆에 위치시키기
        const btnOffsetLeft = hambugerBtn.offsetLeft;
        const btnOffsetTop = hambugerBtn.offsetTop;

        hambugerPopWrap.style.right =
            btnOffsetLeft + hambugerBtn.offsetWidth + "px"; // 버튼 오른쪽 옆
        hambugerPopWrap.style.top = btnOffsetTop + "px"; // 버튼 상단 기준

        hambugerPopWrap.style.display = "block";
    });
});

// 외부 클릭 시 모든 팝업 닫기
document.addEventListener("click", (e) => {
    document.querySelectorAll(".hambuger-pop-wrap").forEach((pop) => {
        if (!pop.contains(e.target) && !pop.parentElement.contains(e.target)) {
            pop.style.display = "none";
        }
    });
});


// ######################### 공고목록 ############################
const bindPaginationEvent = (companyId) => {
    const paginationArea = document.querySelector("#experience-list-table .page-ul");
    if (!paginationArea) return;

    paginationArea.addEventListener("click",(e) => {
        const link = e.target.closest(".page-a");
        if (!link) return;

        e.preventDefault();
        paginationArea.querySelectorAll(".page-a").forEach(a => {
            a.classList.remove("active");
        });
        link.classList.add("active");

        const page = parseInt(link.dataset.page, 10);

        exprienceNoticeService.getList(companyId, page, (data) => {
            experienceLayout.contentLayout();
            experienceLayout.rowTemplate(data.experienceLists);
            experienceLayout.totalCount(data);
            experienceLayout.listTotalCount(data);
            experienceLayout.renderPagination(data.criteria);

            bindPaginationEvent(companyId);
        });
    });
};

exprienceNoticeService.getList(1, 1, (data) => {
    experienceLayout.contentLayout();
    experienceLayout.rowTemplate(data.experienceLists);
    experienceLayout.totalCount(data);
    experienceLayout.listTotalCount(data);
    experienceLayout.renderPagination(data.criteria);
    bindPaginationEvent(1);
});

