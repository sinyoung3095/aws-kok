const companyLayout = (() => {
    const showList = (companiesCriteria) => {
        const listContainer = document.querySelector('.list-container');
        let text = ``;

        companiesCriteria.companies.forEach((company) => {
            text += `
            <div class="list-item" data-company-id="${company.userId}">
                <button class="list-item-btn">
                    <div class="list-item-background">
                        <img src="${company.companyBackgroundFile || '/images/mypage/banner.jpg'}">
                    </div>
                    <div class="list-item-header">
                        <div class="list-item-thumb">
                            <img src="${company.companyProfileFile || `/images/mypage/logo_1757380047672.webp`}">
                        </div>
                        <div class="list-item-content">
                            <p class="list-item-category">${company.scaleName || '직군'}</p>
                            <p class="list-item-name">${company.companyName}</p>
                            <p class="list-item-description">${company.companyInfo || '기업소개'}</p>
                        </div>
                    </div>
                </button>
            </div>`;
        });

        listContainer.innerHTML += text;
    };

    return { showList : showList };
})();
