const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");
const pageNums = document.querySelectorAll(".page-num");
const pageItemNums = document.querySelectorAll(".page-item-num");
const filterBtn = document.getElementById("btn-filter-pg");
const filterWrapper = document.getElementById("filter-pg");
const popMenu = filterWrapper.querySelector(".kok-pop-menu");
const popBack = popMenu.querySelector(".kok-pop-menu-back");
const popContext = popMenu.querySelector(".kok-pop-menu-context");
const checkItems = popContext.querySelectorAll(".kok-check");
const confirmBtn = popContext.querySelector("button.btn-outline-primary");

// 사이드바 펼침/접힘
sideMenuButtons.forEach((menu) => {
    menu.addEventListener("click", function () {
        const submenu = this.nextElementSibling;
        const icon = this.querySelector(".icon-wrapper i");
        if (submenu && submenu.classList.contains("menu-sub-list")) {
            submenu.classList.toggle("show");
            if (submenu.classList.contains("show")) {
                icon.classList.remove("mdi-chevron-right");
                icon.classList.add("mdi-chevron-down");
            } else {
                icon.classList.remove("mdi-chevron-down");
                icon.classList.add("mdi-chevron-right");
            }
        }
    });
});

// 관리자 이메일 토글
userMenuWrapper.addEventListener("click", () => {
    userMenuContent.classList.toggle("show");
});

document.addEventListener("click", (e) => {
    if (
        !userMenuWrapper.contains(e.target) &&
        !userMenuContent.contains(e.target)
    ) {
        userMenuContent.classList.remove("show");
    }
});

// 페이지 번호
pageItemNums.forEach((pageItemNum) => {
    pageItemNum.addEventListener("click", (e) => {
        e.preventDefault();
        pageNums.forEach((pageNum) => pageNum.classList.remove("active"));
        pageItemNum.parentElement.classList.add("active");
    });
});

// 체크박스 클릭 이벤트 (라디오 버튼처럼 하나만 선택)
if (confirmBtn) {
    confirmBtn.style.display = "none";
}

filterBtn.addEventListener("click", function () {
    popBack.classList.toggle("show");
    popContext.classList.toggle("show");
});

document.addEventListener("click", function (e) {
    if (!filterWrapper.contains(e.target)) {
        popBack.classList.remove("show");
        popContext.classList.remove("show");
    }
});

checkItems.forEach((item) => {
    const checkBox = item.querySelector(".kok-check-box");
    const checkIcon = checkBox.querySelector("i");

    item.addEventListener("click", function () {
        const currentLi = item.closest("li");
        const isActive = currentLi.classList.contains("active");

        checkItems.forEach((otherItem) => {
            const otherLi = otherItem.closest("li");
            const otherBox = otherItem.querySelector(".kok-check-box");
            const otherIcon = otherBox.querySelector("i");

            otherIcon.style.display = "none";
            if (otherLi) otherLi.classList.remove("active");
        });

        if (!isActive) {
            checkIcon.style.display = "inline-block";
            currentLi.classList.add("active");
            confirmBtn.style.display = "block";
        } else {
            checkIcon.style.display = "none";
            currentLi.classList.remove("active");
            confirmBtn.style.display = "none";
        }
    });
});

if (confirmBtn) {
    confirmBtn.addEventListener("click", function () {
        popBack.classList.remove("show");
        popContext.classList.remove("show");
    });
}
