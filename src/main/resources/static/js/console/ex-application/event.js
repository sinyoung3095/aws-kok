document.addEventListener("DOMContentLoaded", () => {
    const selectContainer = document.querySelector(".select-container");
    const statusButton = selectContainer.querySelector(".status-button");
    const dropdown = selectContainer.querySelector(".job");
    const statusSpan = selectContainer.querySelector(".status-button-span");
    const options = dropdown.querySelectorAll(".job-3");
    const popup = document.querySelector(".popup-container");
    const popupTitle = popup.querySelector(".pop-title");
    const btnNo = popup.querySelector(".btn-no");
    const btnYes = popup.querySelector(".btn-yes");
    const evalBtn=document.querySelector(".new-exp");
    let selectedStatus = "";
    let confirmedStatus = "";

    // 버튼 클릭 시 드롭다운 열기 / 닫기
    statusButton.addEventListener("click", (e) => {
        e.stopPropagation();
        dropdown.classList.toggle("active");
    });

    // 옵션 클릭 시 상태 변경
    options.forEach((option) => {
        option.addEventListener("click", () => {
            // 모든 옵션 active 제거
            options.forEach((opt) => opt.classList.remove("active"));
            option.classList.add("active");

            // 선택한 상태 텍스트 임시 저장
            selectedStatus = option.querySelector(".job-6").textContent.trim();

            // 팝업 문구 갱신
            const currentHTML = popupTitle.innerHTML;
            const newHTML = currentHTML.replace(/'(.*?)'/, `'${selectedStatus}'`);
            popupTitle.innerHTML = newHTML;

            // 드롭다운 닫고 팝업 열기
            dropdown.classList.remove("active");
            popup.classList.add("active");
        });
    });

    // 팝업 취소 버튼
    btnNo.addEventListener("click", () => {
        popup.classList.remove("active");
        selectedStatus = "";
    });

    // 팝업 확인(진행 상태 변경) 버튼
    btnYes.addEventListener("click", () => {
        if (!selectedStatus) return;

        // 선택한 상태 반영
        confirmedStatus = selectedStatus;
        statusSpan.textContent = confirmedStatus;
        popup.classList.remove("active");

    });

    // 바깥 클릭 시 닫기
    document.addEventListener("click", (e) => {
        if (!selectContainer.contains(e.target)) {
            dropdown.classList.remove("active");
        }
    });

//     평가하기 버튼
    evalBtn.addEventListener("click", async () => {
        const evalPre = await fetch(`/enterprise-console/experience/isEvalOk?experienceNoticeId=${experienceNoticeId}&memberId=${memberId}`);
        const evalOk=await evalPre.json();
        const eval=evalOk;

        console.log(eval);

        if(eval){
            console.log("eval true if문 들어옴")
            // const goToReview=await fetch(`/enterprise-console/experience/review?memberId=${memberId}&experienceNoticeId=${experienceNoticeId}`);
            // await goToReview;
            window.location.href = `/enterprise-console/experience/review?memberId=${memberId}&experienceNoticeId=${experienceNoticeId}&requestExperienceId=${applicantDetail.requestId}`;
        }
        else{
            alert("평가가 불가능합니다. 합격 여부, 체험 종료일을 확인해 주세요.")
        }

    })

});

document.addEventListener("DOMContentLoaded", () => {
    const downloadBtn = document.querySelector(".download-btn");

    if (!downloadBtn) return;

    downloadBtn.addEventListener("click", async () => {
        try {
            // 백엔드로 presigned URL 요청
            const response = await fetch(`/files/experience/download/${experienceNoticeId}/${memberId}`);

            if (!response.ok) {
                alert("이력서 파일을 찾을 수 없습니다.");
                return;
            }

            // presigned URL 문자열 반환
            const downloadUrl = await response.text();

            // 새 창에서 다운로드 실행
            window.open(downloadUrl, "_blank");
        } catch (error) {
            console.error("다운로드 중 오류 발생:", error);
            alert("다운로드 중 오류가 발생했습니다.");
        }
    });
});
