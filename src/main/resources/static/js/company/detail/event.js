// 서치 드롭다운 옵션 버튼 클릭 시 active 클래스 토글 및 전체 선택 상태 업데이트
function searchDropdownFn() {
    let isAllSelected = false;
    const dropdown = document.querySelectorAll(".dropdown-container");
    const dropdownBtns = document.querySelectorAll(".dropdown-btn");

    if (!dropdown) return;

    dropdownBtns.forEach((btn) => {
        btn.addEventListener("click", () => {
            btn.classList.toggle("active");

            let allActive = true;
            dropdownBtns.forEach((btn) => {
                if (!btn.classList.contains("active")) {
                    allActive = false;
                }
            });

            isAllSelected = allActive;
        });
    });

    // 드롭다운 열기/닫기, 전체 선택, 적용 버튼 이벤트
    dropdown.forEach((box) => {
        const dropdownOpenBtns = box.querySelectorAll(".dropdown-open-btn");
        const dropdownBtns = box.querySelectorAll(".dropdown-btn");
        const selectAllBtn = box.querySelector(".select-all-btn");
        const applyBtn = box.querySelector(".apply-btn");

        // 드롭다운 열기/닫기 버튼 클릭 시 active 클래스 토글
        dropdownOpenBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                const isActive = btn.classList.contains("active");

                dropdown.forEach((otherBox) => {
                    const otherBtns =
                        otherBox.querySelectorAll(".dropdown-open-btn");
                    otherBtns.forEach((b) => b.classList.remove("active"));
                });

                if (!isActive) {
                    btn.classList.add("active");
                }
            });
        });

        // 전체 선택 버튼 클릭 시 모든 옵션 선택 또는 선택 해제
        selectAllBtn.addEventListener("click", () => {
            if (!isAllSelected) {
                dropdownBtns.forEach((btn) => btn.classList.add("active"));
                isAllSelected = true;
            } else {
                dropdownBtns.forEach((btn) => btn.classList.remove("active"));
                isAllSelected = false;
            }
        });

        // 적용 버튼 클릭 시 드롭다운 닫기 및 선택된 옵션에 따라 드롭다운 열기 버튼 텍스트 변경
        applyBtn.addEventListener("click", () => {
            let firstText = null;
            let count = 0;

            dropdownBtns.forEach((btn) => {
                if (btn.classList.contains("active")) {
                    count++;
                    if (firstText === null) {
                        firstText = btn.textContent.trim();
                    }
                }
            });

            // 드롭다운 닫기
            dropdownOpenBtns.forEach((btn) => btn.classList.remove("active"));
        });
    });

    // 드롭다운 외부 클릭 시 드롭다운 닫기
    document.addEventListener("click", (e) => {
        dropdown.forEach((box) => {
            if (!box.contains(e.target)) {
                const openBtns = box.querySelectorAll(".dropdown-open-btn");
                openBtns.forEach((btn) => btn.classList.remove("active"));
            }
        });
    });
}
searchDropdownFn();

// 키워드 입력 시 50자 초과하면 토스트 메시지 노출
function keywordInputValidate() {
    const keywordInput = document.querySelector("#keyword-input");
    const toast = document.querySelector("#toast-red");
    const deleteBtn = document.querySelector(".keyword-delete");
    const resetBtn = document.querySelector(".btn-reset");

    if (!keywordInput) return;

    function checkKeywordLength() {
        if (keywordInput && toast) {
            if (keywordInput.value.length > 50) {
                toast.classList.add("show");

                setTimeout(() => {
                    toast.classList.remove("show");
                }, 2000);
            } else {
                toast.classList.remove("show");
            }
        }
    }

    // 검색 버튼 클릭 시 토스트 메시지 체크
    const searchButton = document.querySelector(".search-button");
    if (searchButton) {
        searchButton.addEventListener("click", () => {
            checkKeywordLength();
        });
    }

    // 엔터 입력 시 체크
    keywordInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            checkKeywordLength();
        }
    });

    // 모바일 인풋 삭제
    deleteBtn.style.display = "none";

    keywordInput.addEventListener("input", () => {
        if (keywordInput.value.trim() !== "") {
            deleteBtn.style.display = "inline-block"; // 보이게
        } else {
            deleteBtn.style.display = "none"; // 숨기기
        }
    });

    // 삭제 버튼 클릭 시 인풋 비우고 버튼 숨기기
    deleteBtn.addEventListener("click", () => {
        keywordInput.value = "";
        deleteBtn.style.display = "none";
        keywordInput.focus(); // 다시 입력할 수 있게 포커스 주기
    });

    resetBtn.addEventListener("click", () => {
        keywordInput.value = "";
        deleteBtn.style.display = "none";
        keywordInput.focus(); // 다시 입력할 수 있게 포커스 주기
    });
}
keywordInputValidate();

// 토스트 팝업 - 팔로우
function followToastFn() {
    const followBtns = document.querySelectorAll(".btn-follow");
    const followToast = document.querySelector("#toast-follow");

    if (!followBtns) return;

    let saved = false; // 저장 상태
    let showingToast = false; // 연타방지

    // 팔로우 저장/저장 취소
    if (followBtns) {
        followBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                if (showingToast) return; // 토스트 떠 있으면 무시

                const textBox = followToast.querySelector(".toast-text");
                const subTextBox = followToast.querySelector(".toast-subText");

                if (!saved) {
                    saved = true;
                    if (textBox) {
                        textBox.textContent = "000님을 팔로우했습니다.";
                        subTextBox.textContent =
                            "관련 소식을 받아볼 수 있습니다.";
                        btn.textContent = "팔로우중";
                        btn.classList.add("btn-default");
                        btn.classList.remove("btn-primary");
                    }
                } else {
                    saved = false;
                    if (textBox) {
                        textBox.textContent = "000님을 팔로우 취소했습니다.";
                        subTextBox.textContent =
                            "소식 알림 및 게시물 추천 빈도가 줄어듭니다.";
                        btn.textContent = "팔로우";
                        btn.classList.remove("btn-default");
                        btn.classList.add("btn-primary");
                    }
                }

                // 토스트 띄우기
                followToast.classList.add("show");
                showingToast = true;

                setTimeout(() => {
                    followToast.classList.remove("show");
                    showingToast = false;
                }, 2000);
            });
        });
    }
}
followToastFn();

// 전체, 체험, 인턴 - 필터 버튼 클릭 시 active 클래스 토글
function sortBtnFn() {
    const sortBtns = document.querySelectorAll(".sort-options .sort-btn");

    if (!sortBtns) return;

    sortBtns.forEach((btn) => {
        btn.addEventListener("click", () => {
            sortBtns.forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");
        });
    });
}
sortBtnFn();

// 페이지네이션
function pagenation() {
    const pageItems = document.querySelectorAll(".page-list .page-item");

    pageItems.forEach((btn) => {
        btn.addEventListener("click", () => {
            pageItems.forEach((item) => item.classList.remove("active"));

            btn.classList.add("active");
        });
    });
}
pagenation();
