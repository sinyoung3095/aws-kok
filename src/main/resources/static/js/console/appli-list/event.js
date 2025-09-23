const categoryButtons = document.querySelectorAll(".category-sub");
const countDiv = document.getElementById("count-appli-div");
categoryButtons.forEach((btn) => {
    btn.addEventListener("click", () => {
        categoryButtons.forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
    });
});

document.getElementById("all-btn").addEventListener("click", () => {
    document.querySelectorAll("tr.hap, tr.bul, tr.pend").forEach((tr) => {
        tr.style.display = "";
        countDiv.innerText = "총 11명 중 11명의 지원자가 표시됩니다.";
    });
});

document.getElementById("hap-btn").addEventListener("click", () => {
    document.querySelectorAll("tr.hap").forEach((tr) => {
        tr.style.display = "";
    });
    document.querySelectorAll("tr.bul").forEach((tr) => {
        tr.style.display = "none";
    });
    document.querySelectorAll("tr.pend").forEach((tr) => {
        tr.style.display = "none";
    });
    countDiv.innerText = "총 11명 중 3명의 지원자가 표시됩니다.";
});

document.getElementById("bul-btn").addEventListener("click", () => {
    document.querySelectorAll("tr.hap").forEach((tr) => {
        tr.style.display = "none";
    });
    document.querySelectorAll("tr.bul").forEach((tr) => {
        tr.style.display = "";
    });
    document.querySelectorAll("tr.pend").forEach((tr) => {
        tr.style.display = "none";
    });
    countDiv.innerText = "총 11명 중 6명의 지원자가 표시됩니다.";
});

document.getElementById("pend-btn").addEventListener("click", () => {
    document.querySelectorAll("tr.hap").forEach((tr) => {
        tr.style.display = "none";
    });
    document.querySelectorAll("tr.bul").forEach((tr) => {
        tr.style.display = "none";
    });
    document.querySelectorAll("tr.pend").forEach((tr) => {
        tr.style.display = "";
    });
    countDiv.innerText = "총 11명 중 2명의 지원자가 표시됩니다.";
});
