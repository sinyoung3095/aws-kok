const adNoticeService = (() => {
    // 목록
    const getList = async (companyId, page, keyword, callback) => {
        const response = await fetch(`/api/enterprise-console/ad/list/${companyId}/${page}?keyword=${keyword ?? ""}`);
        const data = await response.json();

        if (callback) {
            setTimeout(() => {
                callback(data);
            }, 1000)
        }

        if (response.ok) {
            console.log("광고 존재")

            console.log(data)
        } else if (response.status === 404) {
            console.log("광고 없음")
        } else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    }

    return {getList:getList}
})();