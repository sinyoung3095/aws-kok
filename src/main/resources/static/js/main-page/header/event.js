// 헤더 프로필 아이콘
const profileIcon = document.querySelector(".account-info-popover");
const profileModal = document.querySelector(".profile-modal");

profileIcon.addEventListener("click", (e) => {
    const isVisible = profileModal.style.display === "flex";
    profileModal.style.display = isVisible ? "none" : "flex";
});

document.addEventListener("click", (e) => {
    if (!profileModal.contains(e.target) && e.target !== profileIcon) {
        profileModal.style.display = "none";
    }
});

// 사이드바 설정 모달
const settingHeaderBtn = document.querySelector(
    ".profile-modal-list-section-setting"
);
const settingHeaderModal = document.querySelector(".sidebar-setting-modal");
const settingHeaderCloseBtn = document.querySelector(
    ".sidebar-setting-modal-close-button"
);

if (settingHeaderBtn && settingHeaderModal) {
    settingHeaderBtn.addEventListener("click", () => {
        if (profileModal) profileModal.style.display = "none";
        settingHeaderModal.style.display = "flex";
    });
}

if (settingHeaderCloseBtn && settingHeaderModal) {
    settingHeaderCloseBtn.addEventListener("click", (e) => {
        settingHeaderModal.style.display = "none";
    });
}

if (settingHeaderModal) {
    settingHeaderModal.addEventListener("click", (e) => {
        if (e.target === settingHeaderModal) {
            settingHeaderModal.style.display = "none";
        }
    });
}

// main-ex.html 구상 시 중복된 js로 주석처리
// 따로 쓰게되면 주석 해제하시면 됩니다!
// 알림 설정 토글
// document.querySelectorAll(".setting-modal-alarm-button").forEach((button) => {
//     const check = button.querySelector(".setting-modal-alarm-button-check");

//     button.addEventListener("click", () => {
//         button.classList.toggle("off");
//         check.classList.toggle("off");
//     });
// });

// const profileHeaderWrap = document.querySelector(
//     ".setting-modal-member-profile-wrap"
// );
// const alarmWrap = document.querySelector(".setting-modal-alarm-wrap");
// const rightLists = document.querySelectorAll(".setting-modal-right-list-wrap");
// const profileRightList = rightLists[0];
// const alarmRightList = rightLists[1];
// const activeBg = "rgba(86, 105, 143, 0.08)";
// const inactiveBg = "transparent";

// profileRightList.style.display = "block";
// alarmRightList.style.display = "none";
// profileHeaderWrap.style.background = activeBg;
// alarmWrap.style.background = inactiveBg;

// profileHeaderWrap.addEventListener("click", () => {
//     profileRightList.style.display = "block";
//     alarmRightList.style.display = "none";

//     profileHeaderWrap.style.background = activeBg;
//     alarmWrap.style.background = inactiveBg;
// });

// alarmWrap.addEventListener("click", () => {
//     alarmRightList.style.display = "block";
//     profileRightList.style.display = "none";

//     alarmWrap.style.background = activeBg;
//     profileHeaderWrap.style.background = inactiveBg;
// });

// 지원 내역 모달
const historyBtn = document.querySelector(
    ".profile-modal-list-section-history"
);
const historyModal = document.querySelector(".history-modal-background");
const historyCloseBtn = document.querySelector(".history-modal-close-button");
const experienceContent = document.querySelector(
    ".history-modal-main-experience"
);
const employContent = document.querySelector(".history-modal-main-employ");

if (historyBtn && historyModal) {
    historyBtn.addEventListener("click", () => {
        if (profileModal) profileModal.style.display = "none";
        if (settingHeaderModal) settingHeaderModal.style.display = "none";

        historyModal.style.display = "flex";
    });
}

if (historyCloseBtn && historyModal) {
    historyCloseBtn.addEventListener("click", () => {
        historyModal.style.display = "none";
    });
}

if (historyModal) {
    historyModal.addEventListener("click", (e) => {
        if (e.target === historyModal) {
            historyModal.style.display = "none";
        }
    });
}

const experienceTab = document.querySelector(
    ".history-modal-top-section-active"
);
const experienceText = experienceTab.querySelector("p");
const employmentTab = document.querySelector(".history-modal-top-section");
const employmentText = employmentTab.querySelector("p");

experienceTab.addEventListener("click", () => {
    experienceTab.classList.add("history-modal-top-section-active");
    experienceTab.classList.remove("history-modal-top-section");
    experienceText.classList.add("history-modal-top-section-text-active");
    experienceText.classList.remove("history-modal-top-section-text");

    employmentTab.classList.remove("history-modal-top-section-active");
    employmentTab.classList.add("history-modal-top-section");
    employmentText.classList.remove("history-modal-top-section-text-active");
    employmentText.classList.add("history-modal-top-section-text");
    employContent.style.display = "none";
    experienceContent.style.display = "flex";
});

employmentTab.addEventListener("click", () => {
    employmentTab.classList.add("history-modal-top-section-active");
    employmentTab.classList.remove("history-modal-top-section");
    employmentText.classList.add("history-modal-top-section-text-active");
    employmentText.classList.remove("history-modal-top-section-text");

    experienceTab.classList.remove("history-modal-top-section-active");
    experienceTab.classList.add("history-modal-top-section");
    experienceText.classList.remove("history-modal-top-section-text-active");
    experienceText.classList.add("history-modal-top-section-text");
    employContent.style.display = "flex";
    experienceContent.style.display = "none";
});

// 지원내역 상세 모달
const historyDetailModal = document.querySelector(
    ".history-detail-modal-background"
);
const historyDetailCloseBtn = document.querySelector(
    ".history-detail-modal-top-right-button"
);
const historyDetailBackBtn = document.querySelector(
    ".history-detail-modal-top-left-button"
);

document.querySelectorAll(".history-modal-main-section").forEach((section) => {
    section.addEventListener("click", () => {
        // 불합격 지원내역은 상세보기 막기
        if (section.classList.contains("fail")) return;
        if (historyDetailModal) {
            historyDetailModal.style.display = "flex";
        }
    });
});

if (historyDetailCloseBtn) {
    historyDetailCloseBtn.addEventListener("click", () => {
        historyDetailModal.style.display = "none";
        historyModal.style.display = "none";
    });
}

if (historyDetailBackBtn) {
    historyDetailBackBtn.addEventListener("click", () => {
        historyDetailModal.style.display = "none";
    });
}

if (historyDetailModal) {
    historyDetailModal.addEventListener("click", (e) => {
        if (e.target === historyDetailModal) {
            historyDetailModal.style.display = "none";
        }
    });
}
