const service = (() => {
    const getExperience = async (callback, page = 1, keyword = '') => {
        const response = await fetch(`/api/experience/${page}?keyword=${keyword}`);
        const adminExperienceDetailDTO = await response.json();
        if(callback){
            callback(adminExperienceDetailDTO);
        }

        if(response.ok) {
            console.log("체험 게시글 존재")
        }else if(response.status === 404){
            console.log("체험 게시글 없음")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminExperienceDetailDTO;
    }

    return {getExperience: getExperience}
})();