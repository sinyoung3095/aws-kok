const followService = (() => {

    const follow = async (companyId) => {
        const response = await fetch(`/api/follow/${companyId}`, {
            method: "POST",
        });
        return response.ok;
    };

    const unfollow = async (companyId) => {
        const response = await fetch(`/api/follow/${companyId}`, {
            method: "DELETE",
        });
        return response.ok;
    };

    const isFollowing = async (companyId) => {
        const response = await fetch(`/api/follow/${companyId}`);
        if (!response.ok) return false;
        return await response.json();
    };

    const getFollowerCount = async (companyId) => {
        const response = await fetch(`/api/follow/count/${companyId}`);
        if (!response.ok) return 0;
        return await response.json();
    };

    return { follow : follow, unfollow : unfollow, isFollowing : isFollowing, getFollowerCount : getFollowerCount};
})();
