const experienceService = (() => {
    const getExperienceNotice = async (page=1, callback) => {
        const response = await fetch(`/api/experiences/?keyword=`+keyword);
        const experienceNoticeCriteria = await response.json();
        if(callback){
            setTimeout(() => {
                callback(experienceNoticeCriteria);
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

        return experienceNoticeCriteria;
    }

    return {getExperienceNotice: getExperienceNotice}
})();