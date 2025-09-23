const searchBtn = document.querySelector(".search-status-btn");
const jobMenu = document.querySelector(".job");
const jobItems = document.querySelectorAll(".job-3");
const checkIcon = document.querySelector(".setting-31");
const searchSpan = document.querySelector(".status-span");

// 1️⃣ 버튼 클릭 → 메뉴 표시/숨김 토글
searchBtn.addEventListener("click", (e) => {
    e.stopPropagation(); // 외부 클릭 이벤트 막기
    jobMenu.style.display =
        jobMenu.style.display === "block" ? "none" : "block";
});

// 2️⃣ job-3 선택
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

// 3️⃣ 외부 클릭 시 메뉴 숨김
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

document.querySelectorAll("tr.list-tr").forEach((tr) => {
    const hambugerBtn = tr.querySelector("button.hambuger");
    const hambugerPopWrap = hambugerBtn
        ? hambugerBtn.querySelector(".hambuger-pop-wrap")
        : null;

    if (!hambugerBtn || !hambugerPopWrap) return;

    hambugerBtn.style.position = "relative";
    hambugerPopWrap.style.position = "absolute";

    hambugerBtn.addEventListener("click", (e) => {
        e.stopPropagation();

        // 모든 팝업 닫기
        document
            .querySelectorAll(".hambuger-pop-wrap")
            .forEach((pop) => (pop.style.display = "none"));

        // 버튼 왼쪽 위에 위치시키기
        hambugerPopWrap.style.right = 0; // 버튼 왼쪽 옆
        hambugerPopWrap.style.top = -hambugerPopWrap.offsetHeight + "px"; // 버튼 위

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

const pageNums = document.querySelectorAll(".page-num");
const pageLis = document.querySelectorAll(".page-li");

pageLis.forEach((pageLi) => {
    pageLi.addEventListener("click", (e) => {
        e.preventDefault();
        pageNums.forEach((pageNum) => pageNum.classList.remove("page-on"));
        pageLi.classList.add("page-on");
    });
});
