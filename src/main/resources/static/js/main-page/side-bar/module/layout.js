const layout = (()=>{
    const showPopularCompany = (CompanyDTO) => {
        const popularWarp = document.getElementById("company-list-wrap");
        let text = '';
        CompanyDTO.forEach((CompanyDTO)=>{
            text+=`<div class="search-modal-company-list">
                        <img alt="image" width="36" height="36" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 5.4px; cursor: default; max-height: 36px; max-width: 36px; min-height: 36px; min-width: 36px; object-fit: contain;">
                            <div class="search-modal-company-section">
                                <span class="search-modal-company-name">${CompanyDTO.companyName}</span>
                                <p class="search-modal-company-experience">${CompanyDTO.followerCount}</p>
                            </div>
                    </div>`
        })
        popularWarp.innerHTML=text;
    }
    const showExperience = (ExperienceNoticeDTO)=>{
        const experienceWarp = document.getElementById("experienceWarp");
        let text ='';
        ExperienceNoticeDTO.forEach((ExperienceNoticeDTO,i)=>{
            text+=
                `<div class="search-modal-list-section" data-id="${ExperienceNoticeDTO.id}">
            <span class="search-modal-list-number">${i+1}</span>
            <img alt="image" width="40" height="40" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                <div class="search-modal-list-member">
                    <span class="search-modal-list-name">${ExperienceNoticeDTO.companyName}</span>
                    <p class="search-modal-list-job">${ExperienceNoticeDTO.experienceNoticeTitle}</p>
                </div>
        </div>`
        });
        experienceWarp.innerHTML=text;

    }
    const showIntern = (InternNoticeDTO)=>{
        const internWarp = document.getElementById("internWarp");
        let text ='';
        InternNoticeDTO.forEach((InternNoticeDTO,i)=>{
            text+=
                `<div class="search-modal-list-section" data-id="${InternNoticeDTO.id}">
            <span class="search-modal-list-number">${i+1}</span>
            <img alt="image" width="40" height="40" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                <div class="search-modal-list-member">
                    <span class="search-modal-list-name">${InternNoticeDTO.companyName}</span>
                    <p class="search-modal-list-job">${InternNoticeDTO.internNoticeTitle}</p>
                </div>
        </div>`
        });
        internWarp.innerHTML=text;

    }
    return{showPopularCompany:showPopularCompany,showExperience:showExperience,showIntern:showIntern}
})();