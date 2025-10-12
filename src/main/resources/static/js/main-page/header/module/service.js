const service = (()=>{
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
        const requestDTO = await response.json();
        console.log(requestDTO);
        if(callback){
            return callback(requestDTO);
        }
        return requestDTO;
    }
    const getRequestIntern = async (callback)=>{
            console.log("체험 서비스 들어옴");
            const response = await fetch('/api/main/requestIntern?internId=${internId}');
            const requestDTO = await response.json();
            console.log(requestDTO);
            if(callback){
                return callback(requestDTO);
            }
            return requestDTO;
        }


    return {logout:logout,getRequestExperience:getRequestExperience,getRequestIntern:getRequestIntern}
})();