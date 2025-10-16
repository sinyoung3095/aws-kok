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

    const deleteAd = async (id) => {
        try {
            const response = await fetch(`/api/enterprise-console/ad/${id}`, {
                method: "DELETE",
            });

            if (!response.ok) {
                throw new Error("삭제 요청 실패");
            }

            const result = await response.text(); // "success"
            return result;
        } catch (err) {
            console.error("광고 삭제 중 오류:", err);
            return null;
        }
    };

    return {getList:getList, deleteAd:deleteAd}
})();