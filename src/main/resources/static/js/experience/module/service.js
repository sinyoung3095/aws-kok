const experienceService = (() => {
    const getExperienceNotice = async (page = 1, keyword = "") => {
        const url = `/api/experiences/${page}?keyword=${encodeURIComponent(keyword)}`;
        const response = await fetch(url);
        if (!response.ok) {
            if (response.status === 404) return { experiences: [], criteria: {} };
            throw new Error(`서버 오류: ${response.status}`);
        }
        return await response.json();
    };
    return { getExperienceNotice };
})();
