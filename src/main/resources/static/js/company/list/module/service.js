const companyService = (() => {
    const getList = async (page = 1, callback) => {
        const response = await fetch(`/api/company/${page}`);
        const companiesCriteria = await response.json();
        if (callback) callback(companiesCriteria);
        return companiesCriteria;
    };

    return { getList : getList };
})();
