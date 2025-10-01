const searchBtn = document.querySelector(".search-btn");
const searchSpan = document.querySelector(".search-span");
const dropdownMenu = document.querySelector(".dropdown-menu");
const jobButtons = document.querySelectorAll(".dropdown-btn");

// 검색 버튼 클릭 시 드롭다운 열기/닫기
searchBtn.addEventListener("click", (e) => {
    e.stopPropagation();
    dropdownMenu.classList.toggle("active");
});

// 옵션 클릭 시 active 처리 & 버튼 텍스트 변경
jobButtons.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        e.stopPropagation();

        // 모든 버튼 active 제거
        jobButtons.forEach((b) => b.classList.remove("active"));

        // 클릭한 버튼에 active 추가
        btn.classList.add("active");

        // 선택된 텍스트를 상단 버튼에 반영
        searchSpan.innerText = btn.querySelector(".job-6").innerText;
        searchSpan.style.color = "#000";

        // 드롭다운 닫기
        dropdownMenu.classList.remove("active");
    });
});

// 외부 클릭 시 닫기
document.addEventListener("click", (e) => {
    if (!dropdownMenu.contains(e.target) && !searchBtn.contains(e.target)) {
        dropdownMenu.classList.remove("active");
    }
});

// 글씨 길이 제한 있는 인풋 - validate-length
const validateLengthInputs = document.querySelectorAll(".validate-length");
validateLengthInputs.forEach((input) => {
    input.addEventListener("input", () => {
        const max = Number(input.dataset.max || 0);

        if (max > 0 && input.value.length > max) {
            input.value = input.value.substring(0, max);
        }

        if (max > 0 && input.value.length > max) {
            input.style.border = "2px solid red";
        } else {
            input.style.border = "";
        }
    });
});

// 필수값 유효성 검사
const openBtn = document.querySelector("#btn-register-experience"); // 하단 버튼
const popup = document.querySelector(".popup-container");           // 팝업
const btnNo = document.querySelector(".btn-no");                    // 팝업 취소
const btnYes = document.querySelector(".btn-yes");                  // 팝업 확인

// 팝업 열기
openBtn.addEventListener("click", () => {
    popup.style.display = "flex";
});

// 팝업 취소
btnNo.addEventListener("click", () => {
    popup.style.display = "none";
});

// 팝업 확인 (유효성 검사 실행)
btnYes.addEventListener("click", () => {
    let isValid = true;

    // 입력 필드 검사
    document.querySelectorAll(".contents .content-input").forEach(input => {
        const hasMust = input.closest(".contents")?.querySelector(".must");

        if (hasMust) {
            if (input.value.trim() === "") {
                input.style.border = "2px solid red";
                isValid = false;
            } else {
                input.style.border = "";
            }
        }
    });

    // 드롭다운 검사
    document.querySelectorAll(".contents .search-btn").forEach(btn => {
        const hasMust = btn.closest(".contents")?.querySelector(".must");
        const span = btn.querySelector(".search-span");

        if (hasMust) {
            if (!span || span.textContent.includes("선택")) {
                btn.style.border = "2px solid red";
                isValid = false;
            } else {
                btn.style.border = "";
            }
        }
    });

    // textarea 검사
    document.querySelectorAll(".contents .textarea").forEach(textarea => {
        const hasMust = textarea.closest(".contents")?.querySelector(".must");
        if (hasMust) {
            if (textarea.value.trim() === "") {
                textarea.style.border = "2px solid red";
                isValid = false;
            } else {
                textarea.style.border = "";
            }
        }
    });

    // date-input 검사 (yyyy-mm-dd 형식)
    document.querySelectorAll(".date-input").forEach(input => {
        const hasMust = input.closest(".contents")?.querySelector(".must");
        const datePattern = /^\d{4}-\d{2}-\d{2}$/;

        if (hasMust) {
            if (!datePattern.test(input.value.trim())) {
                input.style.border = "2px solid red";
                isValid = false;
            } else {
                input.style.border = "";
            }
        }
    });

    if (isValid) {
        console.log("유효성 통과 → 등록 진행!");
    } else {
        console.log("유효성 실패 → 빨간 border 표시");
    }

    // 팝업은 결과와 상관없이 닫기
    popup.style.display = "none";
});

// 입력 중 border 해제 (실시간)
document.querySelectorAll(".contents .content-input").forEach(input => {
    input.addEventListener("input", () => {
        if (input.value.trim() !== "" && input.value.length <= 30) {
            input.style.border = "";
        }
    });
});

// 드롭다운 선택 시 border 해제
document.querySelectorAll(".contents .dropdown-btn").forEach(btn => {
    btn.addEventListener("click", (e) => {
        const selectedText = e.currentTarget.querySelector(".job-6")?.textContent || "";
        const container = e.currentTarget.closest(".dropdown-container");
        const searchBtn = container.querySelector(".search-btn");
        const span = searchBtn.querySelector(".search-span");

        if (span) span.textContent = selectedText;
        searchBtn.style.border = "";
    });
});

// 입력 중 border 해제 (textarea)
document.querySelectorAll(".contents .textarea").forEach(textarea => {
    textarea.addEventListener("input", () => {
        if (textarea.value.trim() !== "") {
            textarea.style.border = "";
        }
    });
});

// 달력 input
document.querySelectorAll(".date-input").forEach(input => {
    input.addEventListener("input", (e) => {
        let value = e.target.value.replace(/[^0-9]/g, ""); // 숫자만 허용
        if (value.length > 8) value = value.substring(0, 8);

        if (value.length >= 5) {
            value = value.substring(0,4) + "-" + value.substring(4,6) +
                (value.length > 6 ? "-" + value.substring(6) : "");
        } else if (value.length >= 4) {
            value = value.substring(0,4) + "-" + value.substring(4);
        }

        e.target.value = value;
    });
});