const layout = (() => {
    const showList = (adminNoticesCriteriaDTO) => {
        const noticeListContainer = document.querySelector(".table-notice tbody");
        let text = ``;
        adminNoticesCriteriaDTO.noticeList.forEach((notice) => {
            text += `
                <tr class="support-notice-list">
                    <td class="td-date text-grey">${notice.relativeDate}</td>
                    <td class="td-title">
                        <span class="notice-title">${notice.adminNoticeTitle}</span>
                    </td>
                    <td class="td-writer">주) 콕(kok)</td>
                </tr>
           `;
        });
        noticeListContainer.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminNoticesCriteriaDTO.criteria;
        let textNumber = ``;

        if(criteria.hasPreviousPage){
            textNumber += `<a href="/admin/support/${criteria.startPage - 1}" data-page="${criteria.startPage - 1}">이전</a>`
        }

        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            textNumber += `
                <li class="page-item page-num">
                    <a href="/admin/support/${i}" class="page-item-link page-item-num" data-page="${i}">${i}</a>
                </li>
           `;
        }

        if(criteria.hasNextPage){
            textNumber += `<a href="/admin/support/${criteria.endPage + 1}" data-page="${criteria.endPage + 1}">다음</a>`
        }

        pagination.innerHTML = textNumber;

        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }

    }

    return {showList: showList};
})();