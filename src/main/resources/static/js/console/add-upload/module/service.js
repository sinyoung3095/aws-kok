const adService = (() => {
    const register = async (data) => {
        const response = await fetch(`/api/enterprise-console/ad/create`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });

        if (response.ok) {
            console.error("광고 등록 성공: " + response.status);
            return;
        } else {
            console.error("광고 등록 실패: " + response.status);
            return;
        }
        return await response.json();
    };

    // 체험 공고 수정
    const update = async (id, data) => {
        console.log("확인이 안되나?",id)
        const response = await fetch(`/api/enterprise-console/ad/edit/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error("광고 수정 실패: " + response.status);
        }
        return await response.json();
    };


    return {register:register, update:update}
})();


