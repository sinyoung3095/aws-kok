const service = (()=>{
    const logout = (async (user)=>{
        const response = await fetch("api/auth/logout",{
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'Content-Type': 'application/json'
            }

        })
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "logout error");
        }

        return await response.json();

    })
    return{logout:logout()}
})();