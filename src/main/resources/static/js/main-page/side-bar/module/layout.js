const layout = (()=>{
    const showPopularCompany = (CompanyDTO) => {
        const popularWarp = document.getElementById("company-list-wrap");
        let text = '';
        CompanyDTO.forEach((CompanyDTO)=>{
            text+=`<div class="search-modal-company-list">
                        <img alt="image" width="36" height="36" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 5.4px; cursor: default; max-height: 36px; max-width: 36px; min-height: 36px; min-width: 36px; object-fit: contain;">
                            <div class="search-modal-company-section">
                                <span class="search-modal-company-name">${CompanyDTO.companyName}</span>
                                <p class="search-modal-company-experience">${CompanyDTO.followerCount}</p>
                            </div>
                    </div>`
        })
        popularWarp.innerHTML=text;
    }
    const showExperience = (ExperienceNoticeDTO)=>{
        const experienceWarp = document.getElementById("experienceWarp");
        let text ='';
        ExperienceNoticeDTO.forEach((ExperienceNoticeDTO,i)=>{
            text+=
                `<div class="search-modal-list-section" data-id="${ExperienceNoticeDTO.id}">
            <span class="search-modal-list-number">${i+1}</span>
            <img alt="image" width="40" height="40" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                <div class="search-modal-list-member">
                    <span class="search-modal-list-name">${ExperienceNoticeDTO.companyName}</span>
                    <p class="search-modal-list-job">${ExperienceNoticeDTO.experienceNoticeTitle}</p>
                </div>
        </div>`
        });
        experienceWarp.innerHTML=text;

    }
    const showIntern = (InternNoticeDTO)=>{
        const internWarp = document.getElementById("internWarp");
        let text ='';
        InternNoticeDTO.forEach((InternNoticeDTO,i)=>{
            text+=
                `<div class="search-modal-list-section" data-id="${InternNoticeDTO.id}">
            <span class="search-modal-list-number">${i+1}</span>
            <img alt="image" width="40" height="40" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                <div class="search-modal-list-member">
                    <span class="search-modal-list-name">${InternNoticeDTO.companyName}</span>
                    <p class="search-modal-list-job">${InternNoticeDTO.internNoticeTitle}</p>
                </div>
        </div>`
        });
        internWarp.innerHTML=text;

    }

    const showSupport = (adminNoticeCriteriaDTO) => {
        const supportWrap = document.getElementById("support-wrap");
        let text = ``;

        adminNoticeCriteriaDTO.noticeList.forEach((notice) => {
            text += `
                <li class="customer-support-list-section" data-id="${notice.id}">
                    <div class="customer-support-list-content">
                        <span class="customer-support-list-title-wrap">
                            <span class="customer-support-list-title-icon"></span>${notice.adminNoticeTitle}
                        </span>
                        <span class="customer-support-list-text">
                            ${notice.adminNoticeContent}
                        </span>
                    </div>
                </li>
            `;
        });
        supportWrap.innerHTML += text;
    }

    const showSupportDetail = (adminNoticeDTO) => {
        const supportDetailWrap = document.getElementById("member-support-detail");
        let text = ``;

        text += `
            <div class="detail-sub-top">
                <span class="detail-back-wrap" tabindex="0" role="button">
                    <span class="detail-back-icon"></span>
                </span>
                <span class="detail-title-wrap">
                    <span class="detail-title">
<!--                        <span class="detail-title-section">계정 및 프로필</span>-->
<!--                        <span class="detail-title-section-icon">/</span>-->
                        <span class="detail-title-text">콕(KOK) 고객지원</span>
                    </span>
                </span>
            </div>
            <div class="detail-main-body-wrap">
                <div class="detail-main-body" id="detail-main-body">
                    <div class="detail-main-content-wrap1">
                        <div class="detail-main-content-wrap2">
                            <div class="detail-main-content-wrap3" role="main">
                                <article class="detail-main-article-wrap">
                                    <div class="detail-main-article" role="article">
                                        <h3 onclick="" id="detail-main-title" class="detail-main-title">
                                            <span class="detail-main-title-text">${adminNoticeDTO.adminNoticeTitle}</span>
                                        </h3>
                                        <span class="detail-main-content-text">
                                            <br>
                                            ${adminNoticeDTO.adminNoticeContent}
                                        </span>
                                        <span class="detail-main-content-space"></span>
                                        <p class="detail-main-update-date">업데이트 날짜: ${adminNoticeDTO.updatedDateTime}</p>
                                    </div>
                                </article>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
        supportDetailWrap.innerHTML = text;
    }
    return{showPopularCompany:showPopularCompany,showExperience:showExperience,showIntern:showIntern,showSupport:showSupport,showSupportDetail:showSupportDetail}
})();