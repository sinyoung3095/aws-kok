const myPageService = (() => {
    const getExperienceList = async (page = 1, keyword = "") => {
        const request = await fetch (`/api/mypage/request-list`);
        const response = await request.json();

        return response;
    };
    return { getExperienceList };
})();
