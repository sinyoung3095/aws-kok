let status = null;

// 초기 로딩
document.addEventListener("DOMContentLoaded", () => {
    filter(1);
});

const statusButtons = document.querySelectorAll(".category-sub");
statusButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        // active 스타일 토글
        statusButtons.forEach(b => b.classList.remove("active"));
        btn.classList.add("active");

        if (btn.classList.contains("await")) {
            status = "await";
        } else if (btn.classList.contains("accept")) {
            status = "accept";
        } else if (btn.classList.contains("reject")) {
            status = "reject";
        } else {
            status = "";
        }``

        filter(1);
    });
});

function filter(page = 1) {
    experienceDatailLayout.contentLayout();
    experienceDatailService.getList(experienceNoticeId, page, status, (data) => {
        if (!data) return;

        experienceDatailLayout.rowTemplate(data.applicantLists);
        experienceDatailLayout.renderPagination(data.criteria);
        experienceDatailLayout.listTotalCount(data);
    });
};

document.addEventListener("click", async (e) => {
    const target = e.target.closest(".download");
    if (!target) return;

    const checkedBoxes = document.querySelectorAll(".check-download:checked");
    const memberIdList = Array.from(checkedBoxes).map(el => el.dataset.memberId);
    console.log("선택된자", memberIdList);

    const urls = await experienceDatailService.downLoad(experienceNoticeId, memberIdList);
    console.log("다운로드", urls);

    for (let i = 0; i < urls.length; i++) {
        const url = urls[i];
        console.log(url);
        location.href = url;
    }

    // urls.forEach((url) => {
    //     console.log(url);
    //     location.href = url;
    // });

    console.log("다운로드 완료");
});








