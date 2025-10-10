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
    }
    return{showPopularCompany:showPopularCompany}
})();