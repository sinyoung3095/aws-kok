document.addEventListener("DOMContentLoaded", () => {
    filter(1);
});

const statusButtons = document.querySelectorAll(".category-sub");
let status = "";

statusButtons.forEach(btn => {
    btn.addEventListener("click", async () => {
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
        }

        await filter(1);
    });
});

async function filter(page = 1) {
    const data = await internDatailService.getList(internNoticeId, page, status);

    internDatailLayout.contentLayout();
    internDatailLayout.rowTemplate(data.applicantLists);
    internDatailLayout.renderPagination(data.criteria);
    internDatailLayout.listTotalCount(data);
};

document.addEventListener("click", async (e) => {
    const target = e.target.closest(".download");
    if (!target) return;

    const checkedBoxes = document.querySelectorAll(".check-download:checked");
    if (checkedBoxes.length === 0) {
        alert("다운로드할 지원자를 선택해주세요.");
        return;
    }

    // 로딩바 보이게 하기
    const loading = document.querySelector(".loading");
    loading.style.display = "block";

    const memberIdList = [];
    for (let i = 0; i < checkedBoxes.length; i++) {
        memberIdList.push(checkedBoxes[i].dataset.memberId);
    }

    const requestInternDownloadUrlDTO = await internDatailService.downLoad(internNoticeId, memberIdList);
    const urls = requestInternDownloadUrlDTO.urls;
    const fileNames = requestInternDownloadUrlDTO.fileNames;
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
    const content = await zip.generateAsync({ type: 'blob' });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(content);
    a.download = 'downloaded_files.zip';
    a.style.display = 'none';
    document.body.appendChild(a);
    a.click();
    URL.revokeObjectURL(a.href); // 메모리 해제
    a.remove();

    //    로딩바 숨기기
    loading.style.display = "none"
});
