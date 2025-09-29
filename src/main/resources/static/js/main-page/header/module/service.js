const service = (()=>{
    const logout = async ()=>{
        const response = await fetch("api/auth/logout",{
            method: 'POST',
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "logout error");
        }

    };

    return {logout:logout}
})();