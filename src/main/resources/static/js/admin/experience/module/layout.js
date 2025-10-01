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

        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminExperienceDetailDTO.listCriteria;
        let textNumber = ``;

        console.log(criteria);

        if(criteria.hasPreviousPage){
            textNumber = `
                <li class="page-item page-num">
                    <a class="page-item-link page-item-num" data-page="${criteria.page - 1}">이전</a>
                </li>
            `;
        }

        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            textNumber += `
                <li class="page-item page-num">
                    <a class="page-item-num" href="/admin/support/${i}" data-page="${i}">${i}</a>
                </li>
           `;
        }

        if(criteria.hasNextPage){
            textNumber += `
                <li class="page-item page-num">
                    <a class="page-item-link page-item-num" data-page="${criteria.page + 1}">다음</a>
                </li>
            `;
        }
        pagination.innerHTML = textNumber;

        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }
    }

    return {showList: showList}
})();