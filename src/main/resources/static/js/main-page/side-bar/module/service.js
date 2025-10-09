const service = (()=>{
    const getPopularCompany = async (callback)=>{
        const response = await fetch("/api/main/popular")
        const CompanyDTO = await response.json();
        if(callback){
            callback(CompanyDTO);
        }

    }
    return {getPopularCompany:getPopularCompany}
})();