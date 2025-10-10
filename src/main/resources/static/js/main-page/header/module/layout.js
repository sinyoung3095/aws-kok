const layout = (() => {
    const showExperienceList = (requestDTO) => {
        console.log(requestDTO)
        const experienceWarp = document.getElementById("experienceWarp");
        let text = '';
        requestDTO.forEach((request) => {
            if (request.requestExperienceStatus === 'await') {
                text += `<div class="history-modal-main-section" data-id="${request.id}">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${request.companyName}</span>
                                    <span class="history-modal-main-announce-title">${request.experienceNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${request.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">서류 접수</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else if (request.requestExperienceStatus === 'accept') {
                text += `<div class="history-modal-main-section data-id="${request.id}"">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${request.companyName}</span>
                                    <span class="history-modal-main-announce-title">${request.experienceNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${request.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else {
                text += `<div class="history-modal-main-section fail">
                            <div class="history-modal-main-company-wrap-fail">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${request.companyName}</span>
                                    <span class="history-modal-main-announce-title">${request.experienceNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${request.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">불합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            }
        })
        experienceWarp.innerHTML = text;
    }
    const showInternList = (requestDTO) => {
        const InternWarp = document.getElementById("InternWarp");
        let text = '';
        requestDTO.forEach((request) => {
            if (request.requestInternStatus === 'await') {
                text += `<div class="history-modal-main-section" data-id="${request.id}">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${request.companyName}</span>
                                    <span class="history-modal-main-announce-title">${request.internNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${request.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">서류 접수</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else if (request.requestInternStatus === 'accept') {
                text += `<div class="history-modal-main-section data-id="${request.id}"">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${request.companyName}</span>
                                    <span class="history-modal-main-announce-title">${request.internNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${request.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else {
                text += `<div class="history-modal-main-section fail">
                            <div class="history-modal-main-company-wrap-fail">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${request.companyName}</span>
                                    <span class="history-modal-main-announce-title">${request.internNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${request.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">불합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            }
        })
        InternWarp.innerHTML = text;
    }

return {showExperienceList: showExperienceList,showInternList:showInternList}
})
();