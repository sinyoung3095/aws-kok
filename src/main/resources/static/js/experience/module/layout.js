const experienceLayout = (() => {
    const showList = async (experiences) => {   // 👈 async 붙임
        const container = document.querySelector('.list-container');
        if (!container) return;

        if (!Array.isArray(experiences) || experiences.length === 0) {
            container.innerHTML = '<p class="no-results">검색 결과가 없습니다.</p>';
            return;
        }

        let html = '';

        for (const exp of experiences) {
            const fileUrl = await fetch(`/api/experiences/profile?companyId=${exp.companyId}`)
                                  .then(res => res.text());

            html += `
                <div class="list-item">
                    <button class="list-item-btn companyId-${exp.companyId} experienceId-${exp.id}">
                        <div class="list-item-header">
                            <div class="list-item-thumb"><img src="${fileUrl}" alt=""></div>
                            <div class="list-item-content">
                                <p class="list-item-title">${exp.companyName || ''}</p>
                                <p class="list-item-subtitle">${exp.experienceNoticeTitle || ''}</p>
                                <p class="list-item-description">${exp.experienceNoticeSubtitle || ''}</p>
                            </div>
                        </div>
                        <div class="list-item-meta">
                            <div class="list-item-meta-field">
                                <p class="list-item-label">직군</p>
                                <div class="list-item-value"><p>${exp.jobName || ''}</p></div>
                            </div>
                            <div class="list-item-meta-field">
                                <p class="list-item-label">규모</p>
                                <div class="list-item-value"><p>${exp.companyScaleName || ''}</p></div>
                            </div>
                        </div>
                    </button>
                </div>
            `;
        }

        container.innerHTML = html;

        applyFilters();
    };

    return { showList };
})();

// 채용 상세
document.addEventListener("DOMContentLoaded", () => {
    const container = document.querySelector(".list-container");
    const contentDetail = document.querySelector(".content-detail");
    const contentSide = document.querySelector(".content-side");

    if (!container || !contentDetail) return;

    container.addEventListener("click", async (e) => {
        contentDetail.innerHTML=``;
        const btn = e.target.closest(".list-item-btn");
        if (!btn) return;

        contentDetail.classList.remove('active');

        const companyClass = Array.from(btn.classList)
                              .find(c => c.startsWith("companyId-"));
        if (!companyClass) return;

        const companyId = companyClass.split("-")[1];

        const experienceClass = Array.from(btn.classList)
                             .find(c => c.startsWith("experienceId-"));
        if (!experienceClass) return;

        const experienceId = experienceClass.split("-")[1];

        // fetch로 상세 데이터 가져오기
        const response = await fetch(`/api/experiences/detail?companyId=${companyId}&experienceId=${experienceId}`);
        const data = await response.json();
        const detailData= data;

        const fileUrl = await fetch(`/api/experiences/profile?companyId=${companyId}`)
                                  .then(res => res.text());

        const endDate = `${detailData.notice.experienceEndDate}`;
        const [year, month, day] = endDate.split('-');
        const formatted = `${year}년 ${month}월 ${day}일`;

        // console.log(formatted);


        // console.log(detailData.notice);
        // console.log(detailData.company);

        contentDetail.innerHTML=`<div class="content-detail-inner">
                            <div class="content-detail-header">
                                <button class="detail-arrow-btn">
                                    <svg fill="currentColor" height="20" role="img" width="20">
                                        <path clip-rule="evenodd" d="M11.566 5.435a.8.8 0 0 0-1.132 0l-6 6a.8.8 0 0 0 0 1.13l6 6a.8.8 0 1 0 1.132-1.13L6.93 12.8H19a.8.8 0 1 0 0-1.6H6.931l4.635-4.634a.8.8 0 0 0 0-1.131" fill-rule="evenodd"></path>
                                    </svg>
                                    <p>목록</p>
                                </button>
                            </div>
                            <div class="content-detail-body">
                                <button class="list-item-header">
                                    <div class="list-item-thumb">
                                        <img src="${fileUrl}" alt="">
                                    </div>
                                    <div class="list-item-content">
                                        <p class="list-item-title">${detailData.company.companyName}</p>
                                        <ul class="profile-stats">
                                            <li class="profile-stat-item">팔로워 <i class="num">${detailData.company.followerCount}</i></li>
                                            <li class="profile-stat-item">체험공고 <i class="num">${detailData.company.experienceCount}</i></li>
                                            <li class="profile-stat-item">인턴공고 <i class="num">${detailData.company.internCount}</i></li>
                                        </ul>
                                    </div>
                                </button>
                                
                                <div class="detail-content">
                                    <div class="detail-header">
                                        <strong class="detail-title">${detailData.notice.experienceNoticeTitle}</strong>
                                        <p class="detail-subtitle">${detailData.notice.experienceNoticeSubtitle}</p>
                                    </div>

                                    <div class="detail-actions">
                                        <!-- popup-trigger 클래스가 있으면 열림 -->
                                        <button class="detail-action-btn detail-apply-btn popup-trigger" data-target="#quick-apply-popup">간편 지원하기</button>
                                        <button class="detail-action-btn detail-save-btn">저장함</button>
                                        <button class="detail-action-btn detail-share-btn">공유하기</button>
                                    </div>

                                    <ul class="detail-meta">
                                        <li class="detail-meta-item">
                                            <p class="meta-label">직군</p>
                                            <p class="meta-value">${detailData.notice.jobName}</p>
                                        </li>
                                        <li class="detail-meta-item">
                                            <p class="meta-label">회사 규모</p>
                                            <p class="meta-value">${detailData.company.scaleName}</p>
                                        </li>
                                    </ul>

                                    <div class="deadline-info">
                                        <p class="deadline-remain">지원 마감까지 ${detailData.notice.remainingDays}일 남음</p>
                                        <p class="deadline-description">${formatted}까지 지원할 수 있습니다.</p>
                                    </div>

                                    <div class="detail-description">
                                        <div class="detail-item">
                                            <p class="detail-item-title">직무소개</p>
                                            <p class="detail-item-content">${detailData.notice.experienceNoticeIntroduceJob}</p>
                                        </div>
                                        <div class="detail-item">
                                            <p class="detail-item-title">참고사항</p>
                                            <p class="detail-item-content">${detailData.notice.experienceNoticeEtc}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>`;
        void contentDetail.offsetHeight;
        // contentDetail.innerHTML=detailText;
        contentDetail.classList.add("active");
        if (contentSide) contentSide.style.display = "none";

        // transition 끝나면 inner 활성화
        const inner = contentDetail.querySelector(".content-detail-inner");
        if (inner) {
            contentDetail.addEventListener(
                "transitionend",
                () => {
                    if (contentDetail.classList.contains("active")) {
                        inner.classList.add("active");
                    }
                },
                { once: true }
            );
        }
    });

    document.addEventListener("click", (e) => {
        const btn = e.target.closest(".detail-arrow-btn");
        if (!btn) return;

        const contentDetail = document.querySelector(".content-detail");
        const contentSide = document.querySelector(".content-side");

        const inner = contentDetail.querySelector(".content-detail-inner");
        if (inner) inner.classList.remove("active");
        contentDetail.classList.remove("active");

        if (contentSide) contentSide.style.display = "flex";
    });

});
