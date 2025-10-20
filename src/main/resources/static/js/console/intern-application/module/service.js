const applicationInternService = (() => {
    const updateStatus = async (memberId, internNoticeId ,statusValue) => {
        const response = await fetch(`/api/enterprise-console/intern/applicant/${memberId}/status`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ requestInternStatus: statusValue, internNoticeId: internNoticeId })
        });

        if(response.ok) {
            console.log("상태변경 성공")
            window.location.href = `/enterprise-console/intern/application/${internNoticeId}/${memberId}`;
        }else if(response.status === 404){
            console.log("상태변경 실패")
        }else {
            const error = await response.text()
            console.log(error);
        }
    };

    return { updateStatus:updateStatus };
})();