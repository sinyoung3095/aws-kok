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

const experienceTable = document.querySelector("#experience-list-table");
if (experienceTable) {
    // 모집 상태 토글 (기존 로직 유지)
    experienceTable.addEventListener("click", async (e) => {
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

            let statusValue;
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

            const noticeId = tr.dataset.id;
            try {
                const data = await experienceNoticeService.updateExperienceStatus(noticeId, statusValue);
                console.log("DB 반영 성공:", data);
            } catch (err) {
                console.error("DB 반영 실패:", err);
            }
        }

        // 햄버거 버튼 클릭
        const hambugerBtn = e.target.closest("button.hambuger");
        if (hambugerBtn) {
            e.stopPropagation();

            // 모든 팝업 닫기
            document.querySelectorAll(".hambuger-pop-wrap").forEach((pop) => {
                pop.style.display = "none";
            });

            // 현재 버튼 다음 형제 팝업만 열기
            const hambugerPopWrap = hambugerBtn.nextElementSibling;
            if (hambugerPopWrap) {
                hambugerPopWrap.style.position = "absolute";
                hambugerPopWrap.style.top = "50px";
                hambugerPopWrap.style.right = "32px";
                hambugerPopWrap.style.display = "block";
            }
            return;
        }

        // 팝업 삭제하기 버튼 클릭
        const deleteBtn = e.target.closest(".red-ham-list");
        if (deleteBtn) {
            const tr = deleteBtn.closest(".body-tr");
            const noticeId = tr?.dataset.id;
            if (!noticeId) return;

            const confirmDelete = confirm("정말 이 공고를 삭제하시겠습니까?");
            if (!confirmDelete) return;

            const result = await experienceNoticeService.deleteExperience(noticeId);

            if (result === "success") {
                alert("공고가 삭제되었습니다!");
                location.reload();
            } else {
                alert("삭제 실패! 다시 시도해주세요.");
            }
        }
    });

    // 문서 아무 곳 클릭 시 팝업 닫기
    document.addEventListener("click", (e) => {
        if (!e.target.closest(".hambuger-pop-wrap") && !e.target.closest(".hambuger")) {
            document.querySelectorAll(".hambuger-pop-wrap").forEach((pop) => {
                pop.style.display = "none";
            });
        }
    });
}


// ######################### 공고목록 ############################
// const companyId = 1;
const page = 1;
let status = null;
let keyword ="";

const bindPaginationEvent = (companyId, status) => {
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

        experienceNoticeService.getList(companyId, page, status, keyword,(data) => {
            experienceLayout.contentLayout();
            experienceLayout.rowTemplate(data.experienceLists);
            experienceLayout.totalCount(data);
            experienceLayout.listTotalCount(data);
            experienceLayout.renderPagination(data.criteria);

            bindPaginationEvent(companyId, status, keyword);
        });
    });
};

experienceNoticeService.getList(companyId, page, status, keyword,(data) => {
    experienceLayout.contentLayout();
    experienceLayout.rowTemplate(data.experienceLists);
    experienceLayout.totalCount(data);
    experienceLayout.listTotalCount(data);
    experienceLayout.renderPagination(data.criteria);
    bindPaginationEvent(companyId, status, keyword);
});


// ######################### 검색 ############################
// 요소 가져오기
const searchInput = document.querySelector(".search-input");     // 검색어
// const jobButtons = document.querySelectorAll(".job-3 .job-6");   // 직군 선택
const statusButtons = document.querySelectorAll(".category-sub"); // 전체/모집중/종료

// 현재 선택 상태 저장
// let selectedJob = "";     // 직군

// 직군 선택 이벤트
// jobButtons.forEach(btn => {
//     btn.addEventListener("click", () => {
//         selectedJob = btn.textContent.trim();
//         document.querySelector(".search-span").textContent = selectedJob; // 버튼에 표시 업데이트
//     });
// });

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

    experienceNoticeService.getList(companyId, page, status, keyword, (data) => {
        experienceLayout.contentLayout();
        experienceLayout.rowTemplate(data.experienceLists);
        experienceLayout.totalCount(data);
        experienceLayout.listTotalCount(data);
        experienceLayout.renderPagination(data.criteria);
        bindPaginationEvent(companyId, page, status, keyword);
    });
}

// 엔터 입력 시 실행
searchInput.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
        doSearch();
    }
});
