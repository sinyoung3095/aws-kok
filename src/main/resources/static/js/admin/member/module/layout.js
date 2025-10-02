const layout = (() => {
    const showList = (adminNoticeCriteriaDTO) => {
        const noticeListContainer = document.querySelector(".table-notice tbody");
        let text = ``;
        adminNoticeCriteriaDTO.noticeList.forEach((notice) => {
            text += `

                    <tr>
                        <td class="td-name">
                            <div class="member-name">${member.memberName}
                                <span class="badge-label badge text-danger ml-2">일반회원</span>
                            </div>
                            <div class="member-id">test01@gmail.com</div>
                        </td>
                        <td class="td-amount pr-4 font-weight-bold">정희준
                            <span class="amount-unit"> 님</span>
                        </td>
                        <td class="td-email">
                            <p>test01@gmail.com</p>
                        </td>
                        <td class="td-phone">
                            <p>010-1234-5678</p>
                        </td>
                        <td class="td-profile">
                            <p>1234abcd</p>
                        </td>
                        <td class="td-job">
                            <p>SW 개발</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn">
                                <i class="mdi mdi-chevron-right"></i>
                            </div>
                        </td>
                    </tr> 
           `;
        });
        noticeListContainer.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminNoticeCriteriaDTO.noticeCriteria;
        let textNumber = ``;

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
                    <a class="page-item-num" href="/admin/support/${i}" data-page="${i}" class="page-item-num">${i}</a>
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

    return {showList: showList};
})();