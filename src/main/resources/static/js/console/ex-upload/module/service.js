const experienceRegisterService = (() => {
    // const detail = async (id) => {
    //     const response = await fetch(`/api/enterprise-console/experience/edit/${id}`);
    //     // console.log("응답 상태코드:", response.status);
    //
    //     if (response.ok) {
    //         const data = await response.json();
    //         console.log("상세 데이터 있음", data);
    //         return data;
    //     } else {
    //         console.error("상세 데이터 없음");
    //         return null;
    //     }
    // };

    const register = async (data) => {
        const response = await fetch(`/api/enterprise-console/experience/create`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });

        if (response.ok) {
            const result = await response.json();
            window.location.href = result.redirectUrl;
            console.error("등록 성공: " + response.status);
        } else {
            console.error("등록 실패: " + response.status);
            return;
        }
        return await response.json();
    };

    // 체험 공고 수정
    const update = async (id, data) => {
        const response = await fetch(`/api/enterprise-console/experience/edit/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error("수정 실패: " + response.status);
        }
        return await response.json();
    };

    return {register:register, update:update};
})();
