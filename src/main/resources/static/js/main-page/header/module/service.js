const headerService = (()=>{
    const logout = async () => {
        const response = await fetch('/api/auth/logout', {
            method: 'POST',
        });
        console.log("서비스 들어옴");
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Fetch error");
        }
    };
    const getRequestExperience = async (callback,experienceId='')=>{
        console.log(experienceId);
        const response = await fetch(`/api/main/requestExperience?experienceId=${experienceId}`);
        const requestListDTO = await response.json();
        console.log(requestListDTO);
        if(callback){
            return callback(requestListDTO);
        }
        return requestListDTO;
    }
    const getRequestIntern = async (callback,internId='')=>{
            console.log("체험 서비스 들어옴");
            const response = await fetch(`/api/main/requestIntern?internId=${internId}`);
            const requestListDTO = await response.json();
            console.log(requestListDTO);
            if(requestListDTO){
                return callback(requestListDTO);
            }
            return requestListDTO;
        }


    return {logout:logout,getRequestExperience:getRequestExperience,getRequestIntern:getRequestIntern}
})();