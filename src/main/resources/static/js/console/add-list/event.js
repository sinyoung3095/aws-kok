const searchBtn = document.querySelector(".search-status-btn");
const jobMenu = document.querySelector(".job");
const jobItems = document.querySelectorAll(".job-3");
const checkIcon = document.querySelector(".setting-31");
const searchSpan = document.querySelector(".status-span");

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
        // console.log(item.querySelector(".job-6"));
        // console.log(text);
        searchSpan.innerText = text;

        // 메뉴 숨김
        jobMenu.style.display = "none";
    });
});

// 외부 클릭 시 메뉴 숨김
document.addEventListener("click", () => {
    jobMenu.style.display = "none";
});

const allStatus = document.querySelector(".all-status");
const doingStatus = document.querySelector(".doing-status");
const pendingStatus = document.querySelector(".pending-status");
const compStatus = document.querySelector(".complete-status");

const ing = document.querySelectorAll(".ing");
const pend = document.querySelectorAll(".pend");
const comp = document.querySelectorAll(".comp");

allStatus.addEventListener("click", () => {
    ing.forEach((tr) => {
        tr.style.display = "";
    });
    pend.forEach((tr) => {
        tr.style.display = "";
    });
    comp.forEach((tr) => {
        tr.style.display = "";
    });
});

doingStatus.addEventListener("click", () => {
    ing.forEach((tr) => {
        tr.style.display = "";
    });
    pend.forEach((tr) => {
        tr.style.display = "none";
    });
    comp.forEach((tr) => {
        tr.style.display = "none";
    });
});

pendingStatus.addEventListener("click", () => {
    ing.forEach((tr) => {
        tr.style.display = "none";
    });
    pend.forEach((tr) => {
        tr.style.display = "";
    });
    comp.forEach((tr) => {
        tr.style.display = "none";
    });
});

compStatus.addEventListener("click", () => {
    ing.forEach((tr) => {
        tr.style.display = "none";
    });
    pend.forEach((tr) => {
        tr.style.display = "none";
    });
    comp.forEach((tr) => {
        tr.style.display = "";
    });
});

const adTable = document.querySelector("#ad-list-table");
if (adTable) {
    // 테이블 전체 클릭 이벤트 위임
    adTable.addEventListener("click", async (e) => {
        const hambugerBtn = e.target.closest(".hambuger");
        const redHamBtn = e.target.closest(".red-ham-list");

        // 햄버거 버튼 클릭 시 팝업 열기
        if (hambugerBtn) {
            e.stopPropagation();

            document.querySelectorAll(".hambuger-pop-wrap")
                .forEach(pop => pop.style.display = "none");

            // 현재 버튼 바로 다음에 있는 팝업 선택
            const hambugerPopWrap = hambugerBtn.nextElementSibling;
            if (!hambugerPopWrap) return;

            // 팝업 위치 조정
            const rect = hambugerBtn.getBoundingClientRect();
            hambugerPopWrap.style.position = "absolute";
            hambugerPopWrap.style.top = "50px";
            hambugerPopWrap.style.right = "32px";
            hambugerPopWrap.style.display = "block";

            return;
        }

        // 삭제 버튼 클릭 시 삭제 실행
        if (redHamBtn) {
            const tr = redHamBtn.closest(".list-tr");
            const adId = tr?.dataset.id;
            if (!adId) return;

            const confirmDelete = confirm("정말 삭제하시겠습니까?");
            if (!confirmDelete) return;

            const result = await adNoticeService.deleteAd(adId);
            if (result === "success") {
                alert("광고가 삭제되었습니다!");
                location.reload();
            } else {
                alert("삭제 실패! 다시 시도해주세요.");
            }
            return;
        }
    });

    // 외부 클릭 시 팝업 닫기
    document.addEventListener("click", () => {
        document.querySelectorAll(".hambuger-pop-wrap")
            .forEach(pop => (pop.style.display = "none"));
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

const pageNums = document.querySelectorAll(".page-num");
const pageLis = document.querySelectorAll(".page-li");

pageLis.forEach((pageLi) => {
    pageLi.addEventListener("click", (e) => {
        e.preventDefault();
        pageNums.forEach((pageNum) => pageNum.classList.remove("page-on"));
        pageLi.classList.add("page-on");
    });
});

function getDisplayStatus(ad) {
    const today = new Date();
    const start = new Date(ad.advertiseStartDatetime);
    const end = new Date(ad.advertiseEndDatetime);

    if (ad.advertisementRequestStatus === 'REJECT') return '반려';
    if (ad.advertisementRequestStatus === 'AWAIT') return '대기중';
    if (ad.advertisementRequestStatus === 'ACCEPT') {
        if (today < start) return '대기중';
        if (today > end) return '완료';
        return '진행중';
    }
    return '대기중';
}

// 사용 예시
// data.adLists.forEach(ad => {
//     const status = getDisplayStatus(ad);
//     console.log(`${ad.advertisementMainText} → ${status}`);
// });



// ######################### 공고목록 ############################
const companyId = 1;
const page = 1;
let keyword ="";

const bindPaginationEvent = (companyId, status) => {
    const paginationArea = document.querySelector("#ad-list-table .page-ul");
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

        adNoticeService.getList(companyId, page, keyword,(data) => {
            adLayout.contentLayout();
            adLayout.rowTemplate(data.adLists);
            adLayout.totalCount(data);
            // adLayout.listTotalCount(data);
            adLayout.renderPagination(data.criteria);

            bindPaginationEvent(companyId, keyword);
        });
    });
};

adNoticeService.getList(companyId, page, keyword,(data) => {
    adLayout.contentLayout();
    adLayout.rowTemplate(data.adLists);
    adLayout.totalCount(data);
    // adLayout.listTotalCount(data);
    adLayout.renderPagination(data.criteria);
    bindPaginationEvent(companyId, keyword);
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

    adNoticeService.getList(companyId, page, keyword, (data) => {
        adLayout.contentLayout();
        adLayout.rowTemplate(data.adLists);
        adLayout.totalCount(data);
        // adLayout.listTotalCount(data);
        adLayout.renderPagination(data.criteria);
        bindPaginationEvent(companyId, page, keyword);
    });
}

// 엔터 입력 시 실행
searchInput.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
        doSearch();
    }
});
