document.querySelectorAll(".footer-list-section").forEach((section) => {
    section.addEventListener("click", function (e) {
        // 임시 페이지 이동 막기
        // 서버 구상 시 제외하시면 됩니다
        e.preventDefault();

        document.querySelectorAll(".footer-list-icon-active").forEach((el) => {
            el.classList.replace("footer-list-icon-active", "footer-list-icon");
        });
        document.querySelectorAll(".footer-list-text-active").forEach((el) => {
            el.classList.replace("footer-list-text-active", "footer-list-text");
        });

        const icon = this.querySelector(
            ".footer-list-icon, .footer-list-icon-active"
        );
        const text = this.querySelector(
            ".footer-list-text, .footer-list-text-active"
        );

        icon.classList.replace("footer-list-icon", "footer-list-icon-active");
        text.classList.replace("footer-list-text", "footer-list-text-active");
    });
});
