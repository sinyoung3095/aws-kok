const jobMenu = document.querySelector(".job");
const jobItems = document.querySelectorAll(".job-3");
const checkIcon = document.querySelector(".setting-31");
const searchSpan = document.querySelector(".search-span");

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

const internTable = document.querySelector("#intern-list-table");
if (internTable) {
    internTable.addEventListener("click", async (e) => {
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
            const noticeId = tr.dataset.id;
            try {
                const data = await internNoticeService.updateInternStatus(noticeId, statusValue);
                console.log("DB 반영 성공:", data);
            } catch (err) {
                console.error("DB 반영 실패:", err);
            }
        }

        const trs = document.querySelectorAll("#intern-list-table tr.body-tr");
        trs.forEach((tr) => {
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
    });
}

// 외부 클릭 시 모든 팝업 닫기
document.addEventListener("click", (e) => {
    document.querySelectorAll(".hambuger-pop-wrap").forEach((pop) => {
        if (!pop.contains(e.target) && !pop.parentElement.contains(e.target)) {
            pop.style.display = "none";
        }
    });
});


// ######################### 공고목록 ############################
const companyId = 1;
const page = 1;
let status = null;
let keyword ="";

const bindPaginationEvent = (companyId, status) => {
    const paginationArea = document.querySelector("#intern-list-table .page-ul");
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

        internNoticeService.getList(companyId, page, status, keyword,(data) => {
            internLayout.contentLayout();
            internLayout.rowTemplate(data.internLists);
            internLayout.totalCount(data);
            internLayout.listTotalCount(data);
            internLayout.renderPagination(data.criteria);

            bindPaginationEvent(companyId, status, keyword);
        });
    });
};

internNoticeService.getList(companyId, page, status, keyword,(data) => {
    internLayout.contentLayout();
    internLayout.rowTemplate(data.internLists);
    internLayout.totalCount(data);
    internLayout.listTotalCount(data);
    internLayout.renderPagination(data.criteria);
    bindPaginationEvent(companyId, status, keyword);
});


// ######################### 검색 ############################
const searchInput = document.querySelector(".search-input"); // 검색어
const statusButtons = document.querySelectorAll(".category-sub"); // 전체/모집중/종료

// 상태 버튼 이벤트 (전체/모집중/모집종료)
statusButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        // active 스타일 토글
        statusButtons.forEach(b => b.classList.remove("active"));
        btn.classList.add("active");

        if (btn.classList.contains("ing")) {
            status = "active";
        } else if (btn.classList.contains("end")) {
            status = "inactive";
        } else {
            status = ""; // 전체
        }

        // 상태 탭 누르면 바로 검색 실행
        doSearch(1);
    });
});

// 검색 실행 함수
function doSearch(page = 1) {
    keyword = searchInput.value.trim();

    internNoticeService.getList(companyId, page, status, keyword, (data) => {
        internLayout.contentLayout();
        internLayout.rowTemplate(data.internLists);
        internLayout.totalCount(data);
        internLayout.listTotalCount(data);
        internLayout.renderPagination(data.criteria);
        bindPaginationEvent(companyId, page, status, keyword);
    });
}

// 엔터 입력 시 실행
searchInput.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
        doSearch();
    }
});
