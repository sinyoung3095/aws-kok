const internDatailService = (() => {
    // 목록
    const getList = async (internNoticeId, page, status, callback) => {
        const response = await fetch(`/api/enterprise-console/intern/applicate-list/${internNoticeId}/${page}?status=${status ?? ""}`);
        const data = await response.json();

        if (callback) {
            setTimeout(() => {
                callback(data);
            }, 1000)
        }

        if (response.ok) {
            console.log("지원자 목록 존재")
            console.log(data)
        } else if (response.status === 404) {
            console.log("지원자 목록 없음")
        } else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    }
    return {getList:getList}
})();


