const service = (() => {
    const getNotice = async (page=1, callback) => {
        const response = await fetch(`/api/support/${page}`);
        const adminNoticesCriteriaDTO = await response.json();
        if(callback){
            setTimeout(() => {
                callback(adminNoticesCriteriaDTO);
            }, 1000)
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