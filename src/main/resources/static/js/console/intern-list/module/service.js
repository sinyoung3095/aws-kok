const internNoticeService = (() => {
    // 목록
    const getList = async (companyId, page, status, keyword, callback) => {
        console.log("보내는 keyword:", keyword);
        const response = await fetch(`/api/enterprise-console/intern/list/${companyId}/${page}?status=${status ?? ""}&keyword=${keyword ?? ""}`);
        const data = await response.json();

        if (callback) {
            setTimeout(() => {
                callback(data);
            }, 1000)
        }

        if (response.ok) {
            console.log("공고 존재")
            console.log(data)
        } else if (response.status === 404) {
            console.log("게시글 없음")
        } else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    }

    const updateInternStatus = async (noticeId, statusValue) => {
        const data = { id: noticeId, internNoticeStatus: statusValue };

        const response = await fetch(`/api/enterprise-console/intern/${noticeId}/status`, {
            method:"PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        console.log("보내는 데이터",data)

        if(response.ok) {
            console.log("상태변경 성공")
        }else if(response.status === 404){
            console.log("상태변경 실패")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    }

    return {getList:getList, updateInternStatus:updateInternStatus}
})();


