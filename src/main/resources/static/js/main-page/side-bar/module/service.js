const sideService = (()=>{
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
    const getAlarm = async ()=>{
        const response = await fetch("/api/main/alarm");
        const alarmDTO = await response.json();
        const setting = document.getElementsByClassName("setting-modal-alarm-button");
        const check = document.getElementsByClassName("setting-modal-alarm-button-check");
        console.log(setting);
console.log(alarmDTO);
        if(alarmDTO.companyExperienceNoticeAlarm !=='active'){
            setting[0].classList.add("off");
            check[0].classList.add("off");
        }
        if(alarmDTO.companyInternNoticeAlarm !=='active'){
            setting[1].classList.add("off");
            check[1].classList.add("off");
        }
    }
    const setActive = async (keyword)=>{
        const response = await fetch("/api/main/active",{
            method:'PATCH',
            body: JSON.stringify(keyword),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Fetch error");
        }
    }
    const setInactive = async (keyword)=>{
        console.log(keyword);
        const response = await fetch("/api/main/inactive",{
            method:'PATCH',
            body: JSON.stringify(keyword),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Fetch error");
        }
    }
    const getLink= async (callback) =>{
        const response = await fetch("/api/main/link");
        const memberDTO = await response.json();
        console.log(memberDTO);
        if(callback){
            return callback(memberDTO);
        }
        return memberDTO;
    }
    return {getPopularCompany:getPopularCompany,getExperience:getExperience,getIntern:getIntern,getAlarm:getAlarm,setActive:setActive,setInactive:setInactive,getLink:getLink}
})();