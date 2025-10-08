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

const companyNoticeService = (() => {

    const getExperienceNotices = async (companyId, page = 1, search = {}, callback) => {
        let searchContext = "";
        const contexts = [];

        if (search.keyword && search.keyword.trim() !== "") {
            contexts.push(`keyword=${encodeURIComponent(search.keyword)}`);
        }

        if (search.category && search.category.trim() !== "") {
            contexts.push(`category=${encodeURIComponent(search.category)}`);
        }

        if (contexts.length > 0) {
            searchContext = "?" + contexts.join("&");
        }

        const experienceNotice = `/api/company/${companyId}/experiences/${page}${searchContext}`;

        const response = await fetch(experienceNotice);

        const result = await response.json();
        if (callback) callback(result);
        return result;
    };

    return { getExperienceNotices : getExperienceNotices };
})();
