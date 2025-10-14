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
    const memberIdList = [];

    for (let i = 0; i < checkedBoxes.length; i++) {
        memberIdList.push(checkedBoxes[i].dataset.memberId);
    }

    const requestExperienceDownloadUrlDTO = await experienceDatailService.downLoad(experienceNoticeId, memberIdList);
    const urls = requestExperienceDownloadUrlDTO.urls;
    const fileNames = requestExperienceDownloadUrlDTO.fileNames;
    const zip = new JSZip();

    for (let i = 0; i < urls.length; i++) {
        const url = urls[i];
        try {
            const response = await fetch(url);
            if (!response.ok) throw new Error(`Failed to fetch: ${url}`);
            const blob = await response.blob();

            zip.file(fileNames[i], blob);
        } catch (err) {
            console.error(`Error fetching ${url}:`, err);
        }
    }
    zip.generateAsync({ type: 'blob' }).then(content => {
        const a = document.createElement('a');
        a.href = URL.createObjectURL(content);
        a.download = 'downloaded_files.zip';
        a.style.display = 'none';
        document.body.appendChild(a);
        a.click();
        URL.revokeObjectURL(a.href); // 메모리 해제
        a.remove();
    });

    console.log("다운로드 완료");
});
