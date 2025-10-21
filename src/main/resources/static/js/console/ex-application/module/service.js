const applicationExperienceService = (() => {
    const updateStatus = async (memberId, experienceNoticeId ,statusValue) => {
        const response = await fetch(`/api/enterprise-console/experience/applicant/${memberId}/status`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ requestExperienceStatus: statusValue, experienceNoticeId: experienceNoticeId })
        });

        if(response.ok) {
            console.log("상태변경 성공")
            window.location.href = `/enterprise-console/experience/application/${experienceNoticeId}/${memberId}`;
        }else if(response.status === 404){
            console.log("상태변경 실패")
        }else {
            const error = await response.text()
            console.log(error);
        }
    };

    return { updateStatus:updateStatus };
})();