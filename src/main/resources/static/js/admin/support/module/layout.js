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
    }
    return {showList: showList};
})();