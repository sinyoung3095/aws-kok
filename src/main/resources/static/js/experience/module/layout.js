const experienceLayout = (() => {
    const showList = (experienceNoticeCriteria) => {
        const experienceContainer = document.querySelector(".list-container");
        let text = ``;
        experienceNoticeCriteria.experiences.forEach((experience) => {
           text += `
                <div class="list-item">
                                <button class="list-item-btn">
                                    <div class="list-item-header">
                                        <div class="list-item-thumb">
                                            <img src="/images/member/kok_logo.png" alt="">
                                        </div>
                                        <div class="list-item-content">
                                            <p class="list-item-title">${experience.experienceNoticeTitle}</p>
                                            <p class="list-item-subtitle">${experience.experienceNoticeSubtitle}</p>
                                            <p class="list-item-description">${experience.experienceNoticeEtc}</p>
                                        </div>
                                    </div>
                                    <div class="list-item-meta">
                                        <div class="list-item-meta-field">
                                            <p class="list-item-label">직군</p>
                                            <div class="list-item-value">
                                                <p>${experience.companyScaleName}</p>
                                            </div>
                                        </div>
                                        <div class="list-item-meta-field">
                                            <p class="list-item-label">규모</p>
                                            <div class="list-item-value">
                                                <p>${experience.companyScaleName}</p>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                            </div>
           `;
        });
        experienceContainer.innerHTML += text;
    }
    return {showList: showList};
})();