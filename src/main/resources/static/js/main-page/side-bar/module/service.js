const service = (()=>{
    const getPopularCompany = async (callback)=>{
        const response = await fetch("/api/main/popular")
        const CompanyDTO = await response.json();
        if(callback){
            callback(CompanyDTO);
        }
    }
    const getExperience = async (callback,  keyword = '') => {
        const response = await fetch(`/api/main/experience?keyword=${keyword}`);
        const ExperienceNoticeDTO = await response.json();
        if(callback){
            callback(ExperienceNoticeDTO);
        }

        if(response.ok) {
            // console.log("체험 게시글 존재")
        }else if(response.status === 404){
            // console.log("체험 게시글 없음")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return ExperienceNoticeDTO;
    }
    const getIntern = async (callback,  keyword = '') => {
        const response = await fetch(`/api/main/intern?keyword=${keyword}`);
        const InternNoticeDTO = await response.json();
        if(callback){
            callback(InternNoticeDTO);
        }

        if(response.ok) {
            // console.log("인턴 게시글 존재")
        }else if(response.status === 404){
            // console.log("인턴 게시글 없음")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return InternNoticeDTO;
    }
    return {getPopularCompany:getPopularCompany,getExperience:getExperience,getIntern:getIntern}
})();