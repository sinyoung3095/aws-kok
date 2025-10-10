const layout = (() => {
    const showExperienceList = (requestDTO) => {
        const experienceWarp = document.getElementById("experienceWarp");
        let text = '';
        requestDTO.forEach((request) => {
            if (request.RequestStatus === 'await') {
                text += `<div className="history-modal-main-section" data-id="${request.id}">
                            <div className="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div className="history-modal-main-section-info">
                                    <span className="history-modal-main-company-name">${request.companyName}</span>
                                    <span className="history-modal-main-announce-title">${request.experienceNoticeTitle}</span>
                                    <p className="history-modal-main-apply-date">지원일: </p><p className="history-modal-main-apply-date">${request.createdDateTime}</p>
                                </div>
                                <div className="history-modal-main-apply-status-wrap">
                                    <div className="history-modal-main-apply-status">
                                        <span className="history-modal-main-apply-status-text">서류 접수</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else if (request.RequestStatus === 'accept') {
                text += `<div className="history-modal-main-section data-id="${request.id}"">
                            <div className="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div className="history-modal-main-section-info">
                                    <span className="history-modal-main-company-name">${request.companyName}</span>
                                    <span className="history-modal-main-announce-title">${request.experienceNoticeTitle}</span>
                                    <p className="history-modal-main-apply-date">지원일: </p><p className="history-modal-main-apply-date">${request.createdDateTime}</p>
                                </div>
                                <div className="history-modal-main-apply-status-wrap">
                                    <div className="history-modal-main-apply-status">
                                        <span className="history-modal-main-apply-status-text">합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else {
                text += `<div className="history-modal-main-section fail">
                            <div className="history-modal-main-company-wrap-fail">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" srcSet="" src="/images/main-page/image.png"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div className="history-modal-main-section-info">
                                    <span className="history-modal-main-company-name">${request.companyName}</span>
                                    <span className="history-modal-main-announce-title">${request.experienceNoticeTitle}</span>
                                    <p className="history-modal-main-apply-date">지원일: </p><p className="history-modal-main-apply-date">${request.createdDateTime}</p>
                                </div>
                                <div className="history-modal-main-apply-status-wrap">
                                    <div className="history-modal-main-apply-status">
                                        <span className="history-modal-main-apply-status-text">불합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            }
        })
        experienceWarp.innerText = text;
    }

return {showExperienceList: showExperienceList}
})
();