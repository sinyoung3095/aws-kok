const layout = (() => {
    const showList = (adminExperienceDetailDTO) => {
        const experienceListContainer = document.querySelector(".table.member-table tbody");
        let text = ``;
        adminExperienceDetailDTO.experienceList.forEach((experience) => {
            text += `
                <tr>
                    <td class="td-name">
                        <p>${experience.companyName}</p>
                    </td>
                    <td class="td-main-title">
                        <p>${experience.experienceNoticeTitle}</p>
                    </td>
                    <td class="td-sub-title">
                        <p>${experience.experienceNoticeSubtitle}</p>
                    </td>
                    <td class="td-status">
                        <p>`;
            text += experience.experienceNoticeStatus === 'acitve' ? '모집 중' : '모집 완료';
            text += `</p>
                    </td>
                    <td class="td-date">
                        <p>${experience.experienceEndDate}</p>
                    </td>  
                    <td class="td-job">
                        <p>${experience.jobName}</p>
                    </td>
                    <td class="td-action text-center">
                        <div class="action-btn">
                            <i class="mdi mdi-chevron-right"></i>
                        </div>
                    </td>
                </tr>
            `;
        });
        experienceListContainer.innerHTML = text;

    }

    return {showList: showList}
})();