const adService = (() => {
    // 광고 등록
    const register = async (data) => {
        const formData = new FormData();

        // 일반 텍스트 데이터 추가
        formData.append("advertisementMainText", data.advertisementMainText);
        formData.append("advertisementSubText", data.advertisementSubText);
        formData.append("companyId", data.companyId);
        formData.append("advertiseStartDatetime", data.advertiseStartDatetime);
        formData.append("advertiseEndDatetime", data.advertiseEndDatetime);
        formData.append("paymentPrice", data.paymentPrice);

        // 파일 추가 (있을 경우만)
        if (data.files && data.files.length > 0) {
            for (let file of data.files) {
                formData.append("files", file);
            }
        }

        const response = await fetch(`/api/enterprise-console/ad/create`, {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            console.log("광고 등록 성공:", response.status);
            return await response.text();
        } else {
            console.error("광고 등록 실패:", response.status);
            throw new Error("광고 등록 실패");
        }
    };

    // 광고 수정
    const update = async (id, data) => {
        const formData = new FormData();

        // 수정 시 텍스트 데이터
        formData.append("advertisementMainText", data.advertisementMainText);
        formData.append("advertisementSubText", data.advertisementSubText);
        formData.append("companyId", data.companyId);
        formData.append("advertiseStartDatetime", data.advertiseStartDatetime);
        formData.append("advertiseEndDatetime", data.advertiseEndDatetime);
        formData.append("paymentPrice", data.paymentPrice);

        // 파일이 있다면 추가
        if (data.files && data.files.length > 0) {
            for (let file of data.files) {
                formData.append("files", file);
            }
        }

        const response = await fetch(`/api/enterprise-console/ad/edit/${id}`, {
            method: "PUT",
            body: formData,
        });

        if (!response.ok) {
            console.error("광고 수정 실패:", response.status);
            throw new Error("광고 수정 실패");
        }

        console.log("광고 수정 성공:", response.status);
        return await response.text();
    };

    return { register:register, update:update };
})();
