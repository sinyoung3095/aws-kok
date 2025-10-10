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
    const getRequestExperience = async (callback)=>{
        console.log("체험 서비스 들어옴");
        const response = await fetch('/api/main/requestExperience');
        const requestDTO = await response.json();
        console.log(requestDTO);
        if(callback){
            return callback(requestDTO);
        }
        return requestDTO;
    }
    const getRequestIntern = async (callback)=>{
            console.log("체험 서비스 들어옴");
            const response = await fetch('/api/main/requestIntern');
            const requestDTO = await response.json();
            console.log(requestDTO);
            if(callback){
                return callback(requestDTO);
            }
            return requestDTO;
        }

<<<<<<< HEAD

    return {logout:logout,getRequestExperience:getRequestExperience}
=======
    return {logout:logout,getRequestExperience:getRequestExperience,getRequestIntern:getRequestIntern}
>>>>>>> main-page
})();