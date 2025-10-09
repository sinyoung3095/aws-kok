const searchBtn = document.querySelector(".status-button");
const jobMenu = document.querySelector(".job");
const jobItems = document.querySelectorAll(".job-3");
const checkIcon = document.querySelector(".setting-31");
const searchSpan = document.querySelector(".search-span");

// 새로 추가되는 요소들
const darkBack = document.querySelector(".darkback");
const sureBox = document.querySelector(".sure");
const sureNoBtn = document.querySelector(".sure-no");

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

        console.log("job-3 클릭됨");
        console.log(darkBack.style.display);
        // ✅ sure 모달/백그라운드 보이기
        darkBack.style.display = "";
        sureBox.style.display = "";
        console.log(darkBack.style.display);
    });
});

// 3️⃣ 외부 클릭 시 메뉴 숨김
document.addEventListener("click", () => {
    jobMenu.style.display = "none";
});

// 4️⃣ dark-back 클릭 시 모달 닫기
darkBack.addEventListener("click", (e) => {
    // sure 내부 클릭은 무시
    if (e.target === darkBack) {
        darkBack.style.display = "none";
        sureBox.style.display = "none";
    }
});

// 5️⃣ sure-no 버튼 클릭 시 모달 닫기
if (sureNoBtn) {
    sureNoBtn.addEventListener("click", () => {
        darkBack.style.display = "none";
        sureBox.style.display = "none";
    });
}

const categoryButtons = document.querySelectorAll(".category-sub");
categoryButtons.forEach((btn) => {
    btn.addEventListener("click", () => {
        categoryButtons.forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
    });
});
