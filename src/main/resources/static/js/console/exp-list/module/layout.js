const experienceLayout = (() => {
    const contentLayout = () => {
        const contentArea = document.querySelector("#experience-list-table");
        contentArea.innerHTML = `
            <div class="list-list-head">
            </div>
            <div class="list-list-wrap">
                <div class="list-list-main">
                    <table class="list-list-table">
                        <thead class="list-list-thead">
                            <tr class="list-tr">
                                <th class="list-head-main">공고 제목</th>
                                <th class="list-head-sub">직군</th>
                                <th class="list-head-sub">지원자 수</th>
                                <th class="list-head-sub">저장 수</th>
                                <th class="list-head-sub">시작일</th>
                                <th class="list-head-sub">마감일</th>
                                <th class="list-head-sub">상태</th>
                                <th class="list-head-sub">모집 중</th>
                            </tr>
                        </thead>
                        <tbody class="list-list-tbody">
        <!--                   여기에 데이터 들어감 - rowTemplate -->
                        </tbody>
                    </table>
                    <div class="page-div">
                        <nav class="page-nav">
                            <ul class="page-ul">
                                <!-- 페이지네이션-->
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        `
    }

    // 리스트
    const rowTemplate = (lists) => {
        const tbody = document.querySelector("#experience-list-table .list-list-tbody");
        if (!tbody) return;

        tbody.innerHTML = "";
        if (!lists || lists.length === 0) {
            tbody.innerHTML = `
                <tr class="body-tr-none">
                    <td class="body-td" colspan="9">
                        <div class="text">조건에 맞는 공고가 없습니다.</div>
                    </td>
                </tr>`;
            return;
        }

        lists.forEach(list => {
            tbody.innerHTML += `
                <tr class="body-tr" data-id="${list.id}">
                    <td class="body-td">
                        <div>
                            <div class="td-title">${list.experienceNoticeTitle}</div>
                            <div class="td-sub">${list.experienceNoticeSubtitle ?? ""}</div>
                        </div>
                    </td>
                    <td class="body-td">${list.jobCategoryName ?? "-"}</td>
                    <td class="body-td"><span class="span-number">${list.applicantCount}</span> 명</td>
                    <td class="body-td"><span class="span-number">${list.saveCount}</span> 명</td>
                    <td class="body-td">${list.experienceStartDate}</td>
                    <td class="body-td">${list.experienceEndDate}</td>
                    <td class="body-td">
                        <span class="exp-status ${list.experienceNoticeStatus === "active" ? "active" : "gray"}"">${list.experienceNoticeStatus == "inactive" ? "모집 완료" : "모집 중"}</span>
                    </td>
                    <td class="body-td">
                        <div class="appli-active">
                            <button class="appli-active-btn ${list.experienceNoticeStatus === "active" ? "active" : "gray"}"">
                                <span class="circle"></span>
                            </button>
                        </div>
                    </td>
                    <td class="body-td">
                        <button class="hambuger">⋮</button>
                    </td>
                </tr>
            `;
        });
    }

    // 페이지네이션 - layout
    const renderPagination = (criteria) => {
        const paginationArea  = document.querySelector("#experience-list-table .page-ul");
        if (!paginationArea) return;

        let html = ``;

        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            html += `
                <li class="page-li page-num">
                    <a href="#" data-page="${i}" class="page-a ${i === criteria.page ? "active" : ""}">${i}</a>
                </li>
            `;
        }

        paginationArea.innerHTML = html;
    };

    // 페이지네이션 - 총개수
    const listTotalCount = (data) => {
        const countArea = document.querySelector("#experience-list-table .list-list-head");
        if (!countArea) return;

        countArea.innerHTML = `
            <div class="list-list-head-text">전체 공고 <span class="count">${data.totalCount}</span>개</div>
        `
    }

    // 모집 중인 체험 공고 개수, 활성화된 공고의 지원자 개수, 누적 지원자 개수
    const totalCount = (data) => {
        const countArea = document.querySelector("#experience-total-wrap");
        if (!countArea) return;

        countArea.innerHTML = `
            <div class="cards">
                <div class="card-top">
                    <div class="card-top-text">
                    모집 중인 체험 공고
                    </div>
                </div>
                <div class="card-body">
                    <div class="active-total">
                        <div class="card-body-title"><span class="count">${data.activeTotalCount}</span>개</div>
                            <p class="card-body-sub">전체 체험 공고: <span class="count">${data.totalCount}</span>개</p>
                        </div>
                        <a href="/enterprise-console/experience/create" class="card-btn">새 체험 공고 등록</a>
                    </div>
                </div>
                <div class="cards">
                    <div class="card-top">
                        <div class="card-top-text">
                        활성화된 공고의 지원자
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="request-total">
                            <div class="card-body-title"><span class="count">${data.activeRequestCount}</span>명</div>
                            <p class="card-body-sub">활성화 상태인 체험 공고의 지원자</p>
                        </div>
                        <button class="card-btn">진행 중인 지원자 보기</button>
                    </div>
                </div>
                <div class="cards">
                    <div class="card-top">
                        <div class="card-top-text">
                        누적 지원자
                        </div>
                    </div>
                    <div class="card-body">
                        <div>
                            <div class="card-body-title"><span class="count">${data.totalRequestCount}</span>명</div>
                            <p class="card-body-sub">전체 공고 누적 지원자 수</p>
                        </div>
                        <button class="card-btn">지원자 전체 보기</button>
                    </div>
                </div>
            </div>
        `
    }

    return {contentLayout:contentLayout, rowTemplate:rowTemplate, renderPagination:renderPagination, listTotalCount:listTotalCount, totalCount:totalCount }
})();