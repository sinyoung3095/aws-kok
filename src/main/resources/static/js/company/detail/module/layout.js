const companyNoticeLayout = (() => {
    const listContainer = document.querySelector(".card-list");
    const paginationContainer = document.querySelector(".page-list");
    const prevButton = document.querySelector(".pagenation .prev");
    const nextButton = document.querySelector(".pagenation .next");

    const showExperienceNotices = (experienceNotice) => {
        let html = "";
        if (!experienceNotice.experiences || experienceNotice.experiences.length === 0) {
            html = `
                <div class="no-data">
                    <p class="title">체험 공고가 없습니다.</p>
                    <p class="description">팔로우하면 새 공고 알림을 받을 수 있어요.</p>
                </div>
            `;
        } else {
            experienceNotice.experiences.forEach((experience) => {
                html += `
                    <div class="card-item">
                        <a href="" class="card-link">
                            <div class="card-content">
                                <p class="card-title">${experience.experienceNoticeTitle}</p>
                                <p class="card-desc">${experience.experienceNoticeSubtitle}</p>
                            </div>
                        </a>
                    </div>
                `;
            });
        }

        listContainer.innerHTML = html;
        showPagination(experienceNotice.criteria);
    };

    const showInternNotices = (internNotice) => {
        let html = "";
        if (!internNotice.interns || internNotice.interns.length === 0) {
            html = `
                <div class="no-data">
                    <p class="title">인턴 공고가 없습니다.</p>
                    <p class="description">팔로우하면 새 공고 알림을 받을 수 있어요.</p>
                </div>
            `;
        } else {
            internNotice.interns.forEach((intern) => {
                html += `
                    <div class="card-item">
                        <a href="" class="card-link">
                            <div class="card-content">
                                <p class="card-title">${intern.internNoticeTitle}</p>
                                <p class="card-desc">${intern.internNoticeSubTitle}</p>
                            </div>
                        </a>
                    </div>
                `;
            });
        }

        listContainer.innerHTML = html;
        showPagination(internNotice.criteria);
    };

    const showPagination = (criteria) => {
        let pageHtml = "";

        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            pageHtml += `
                <button class="page-item ${i === criteria.page ? "active" : ""}" data-page="${i}">
                    ${i}
                </button>
            `;
        }
        paginationContainer.innerHTML = pageHtml;

        if (criteria.hasPreviousPage) {
            prevButton.dataset.page = criteria.page - 1;
        } else {
            delete prevButton.dataset.page;
        }

        if (criteria.hasNextPage) {
            nextButton.dataset.page = criteria.page + 1;
        } else {
            delete nextButton.dataset.page;
        }
    };

    return { showExperienceNotices : showExperienceNotices, showInternNotices : showInternNotices, showPagination : showPagination };
})();
