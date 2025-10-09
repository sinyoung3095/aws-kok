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
        const response = await fetch('/api/main/requestExperience');
        const requestDTO = await response.json();

        if(callback){
            return callback(requestDTO);
        }
    }

    return {logout:logout,getRequestExperience:getRequestExperience}
})();