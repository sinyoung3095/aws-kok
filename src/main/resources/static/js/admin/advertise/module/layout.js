const layout = (() => {
    const showList = (adminAdvertisementCriteriaDTO) => {
        const amountSection = document.querySelector(".amount-box");
        let amountText = ``;

        amountText = `
            <div class="amount-box form-info-box">
                <div class="revenue-box">
                    <div class="row">
                        <div class="col-auto title-col">
                            <span class="badge-label text-primary icon-label">
                                <i class="mdi mdi-check"></i>
                            </span>
                            <span class="badge-label">승인</span>
                        </div>
                        <div class="col text-right amount-col">
                            <span class="span-amount">${}</span>
                            <span class="amount-unit">건</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="amount-box form-info-box">
                <div class="revenue-box">
                    <div class="row">
                        <div class="col-auto title-col">
                            <span class="badge-label text-wait icon-label">
                                <i class="mdi mdi-timer-sand"></i>
                            </span>
                            <span class="badge-label">대기</span>
                        </div>
                        <div class="col text-right amount-col">
                            <span class="span-amount">1</span>
                            <span class="amount-unit">건</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="amount-box amount-value-box">
                <div class="cancel-box">
                    <div class="row">
                        <div class="col-auto title-col">
                            <span class="badge-label text-danger icon-label">
                                <i class="mdi mdi-close"></i>
                            </span>
                            <span class="badge-label">거절</span>
                        </div>
                        <div class="col text-right amount-col">
                            <span class="span-amount">1</span>
                            <span class="amount-unit">건</span>
                        </div>
                    </div>
                </div>
            </div>
        `;

        const advertisementList = document.getElementById("advertisement-list");
        let text = ``;

        adminAdvertisementCriteriaDTO.advertisements.forEach((advertisement) => {
            text += `
                <tr>
                    <td class="td-name">
                        <p>${advertisement.companyName}</p>
                    </td>
                    <td class="td-main-title">
                        <p>${advertisement.advertisementMainText}</p>
                    </td>
                    <td class="td-sub-title">
                        <p>${advertisement.advertisementSubText}</p>
                    </td>
                    <td class="td-start">
                        <p>${advertisement.advertiseStartDatetime}</p>
                    </td>
                    <td class="td-end">
                        <p>${advertisement.advertiseEndDatetime}</p>
                    </td>  
                    <td class="td-status">
                        <p>`;
            if(advertisement.advertisementRequestStatus === 'await') {
                text += `대기`;
            } else if(advertisement.advertisementRequestStatus === 'accept') {
                text += `승인`;
            } else if(advertisement.advertisementRequestStatus === 'reject'){
                text += `거절`;
            }
            text += `</p>
                    </td>
                    <td class="td-action text-center">
                        <div class="action-btn">
                            <i class="mdi mdi-chevron-right"></i>
                        </div>
                    </td>
                </tr>
            `;
        });
        advertisementList.innerHTML = text;

        // 페이지 번호
        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminAdvertisementCriteriaDTO.criteria;
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
    }
});