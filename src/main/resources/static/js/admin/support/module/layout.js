const layout = (() => {
    const showList = (adminNoticesCriteriaDTO) => {
        const noticeListContainer = document.querySelector(".table-notice tbody");
        let text = ``;
        adminNoticesCriteriaDTO.noticeList.forEach((notice) => {
            text += `
                <tr class="support-notice-list">
                    <td class="td-date text-grey">${notice.updatedDateTime}</td>
                    <td class="td-title">
                        <span class="notice-title">${notice.adminNoticeTitle}</span>
                    </td>
                    <td class="td-writer">주) 콕(kok)</td>
                </tr>
           `;
        });
        noticeListContainer.innerHTML += text;

        text = ``;

        // const pageContainer = document.getElementById("page-container");
        // text = ``;
        //
        // if(criteria.hasPreviousPage){
        //    text = `<a href="/post/list/${criteria.startPage - 1}">이전</a>`
        // }
        //
        // for(let i = criteria.startPage; i <= criteria.endPage; i++){
        //    text += `
        //       <a href="/post/list/${i}">${i}</a>
        //    `;
        // }
        //
        // if(criteria.hasNextPage){
        //    text += `<a href="/post/list/${criteria.endPage + 1}">다음</a>`
        // }
        // pageContainer.innerHTML = text;




    }
    return {showList: showList};
})();