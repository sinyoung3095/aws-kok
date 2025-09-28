const service = (() => {
    const getNotice = async (callback, page = 1) => {
        const response = await fetch(`/api/support/${page}`);
        const adminNoticesCriteriaDTO = await response.json();
        if(callback){
            callback(adminNoticesCriteriaDTO);
        }

        if(response.ok) {
            console.log("게시글 존재")
        }else if(response.status === 404){
            console.log("게시글 없음")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminNoticesCriteriaDTO;
    }

    return {getNotice: getNotice}
})();