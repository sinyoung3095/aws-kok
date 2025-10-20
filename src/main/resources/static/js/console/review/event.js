const submitBtn=document.querySelector(".submit-btn");

submitBtn.addEventListener("click", async () => {
    const radioBtns = document.querySelectorAll(".radio-label");
    let checked = 0;
    let score = 0;
    radioBtns.forEach((btn) => {
        if (btn.checked) {
            checked++;
            score += btn.value;
        }
    });
    if (checked < 16) {
        alert("모든 항목을 선택해주세요.")
    } else {
        const text = document.querySelector(".text-area");
        if (text.textContent.length < 1) {
            alert("총평을 반드시 입력해주세요.")
        } else {
            const avg=score/16;
            const evaluation = {
                requestExperienceId: 12,
                memberId: memberId,
                evaluationContent: text.textContent,
                evaluationAvgScore: avg
            };
            const review = await fetch(`/enterprise-console/experience/go-review`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(evaluation)
            })
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    } else {
                        throw new Error("평가 요청 실패");
                    }
                })
                .catch(err => console.error(err));
        }
    }
});